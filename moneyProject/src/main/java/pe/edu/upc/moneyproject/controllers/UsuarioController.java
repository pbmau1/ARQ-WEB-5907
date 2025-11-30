package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.moneyproject.dtos.*;
import pe.edu.upc.moneyproject.entities.Role;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private IUsuarioService US;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar")
    public List<UsuarioDTO> listar() {
        return US.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);

        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar/users")
    public ResponseEntity<?> MostrarUsuarios() {

        List<UsuariosDTO> list = new ArrayList<>();
        List<String[]> fila = US.findUsuarios();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen usuarios registrados");
        }
        for (String[] x : fila) {
            UsuariosDTO dto = new UsuariosDTO();
            dto.setIdUsuario(Integer.parseInt(x[0]));
            dto.setNombre(x[1]);
            dto.setCorreo(x[2]);
            list.add(dto);
        }

        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertar(@RequestBody UsuarioDTO dto) {

        // Validar si el correo ya existe
        if (US.existsByCorreo(dto.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo ya está registrado");
        }

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        // Creamos el rol por defecto
        Role r = new Role();
        r.setRol("CLIENT");  // siempre CLIENT
        r.setUsuario(u);     // vinculamos con el usuario

        // Asignamos la lista de roles al usuario
        u.setRoles(List.of(r));

        // Insertamos usuario con roles en cascada
        US.insert(u);

        // Respuesta correcta al frontend
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO dto) {

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        Usuario ex = US.listId(u.getIdUsuario());
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + u.getIdUsuario());
        }

        u.setRoles(ex.getRoles());
        if (dto.getContrasenia() == null || dto.getContrasenia().isBlank()) {
            u.setContrasenia(ex.getContrasenia());
        }

        US.update(u);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario usuario = US.listId(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + id);
        }
        US.delete(id);
        return ResponseEntity.ok("Usuario con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/buscar")
    public List<UsuarioDTO> buscar(@RequestParam String filtro) {
        return US.list().stream()
                .filter(u ->
                        u.getNombre().toLowerCase().contains(filtro.toLowerCase()) ||
                                u.getCorreo().toLowerCase().contains(filtro.toLowerCase())
                )
                .map(u -> {
                    ModelMapper m = new ModelMapper();
                    return m.map(u, UsuarioDTO.class);
                })
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) {
        Usuario us = US.listId(id);

        if (us == null) {
            return ResponseEntity.notFound().build();
        }

        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(us, UsuarioDTO.class);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName(); // correo del JWT

        Usuario usuario = US.findByCorreo(correo);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(usuario, UsuarioDTO.class);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PutMapping("/me")
    public ResponseEntity<String> actualizarUsuarioActual(@RequestBody UsuarioDTO dto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correoActual = auth.getName();

        Usuario usuario = US.findByCorreo(correoActual);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Actualizar nombre si cambió
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            usuario.setNombre(dto.getNombre());
        }

        // Actualizar correo, pero validando duplicado
        if (dto.getCorreo() != null && !dto.getCorreo().isBlank()) {

            String nuevoCorreo = dto.getCorreo();

            if (!nuevoCorreo.equals(correoActual) && US.existsByCorreo(nuevoCorreo)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado");
            }

            usuario.setCorreo(nuevoCorreo);
        }

        // Si más adelante deseas permitir cambio de contraseña,
        // debe hacerse en un endpoint separado por seguridad.

        US.update(usuario);

        return ResponseEntity.ok("Datos actualizados correctamente");
    }



}
