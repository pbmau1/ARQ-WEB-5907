package pe.edu.upc.moneyproject.controllers;

import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private HttpServletRequest request;


    private Integer getUserIdFromToken() {
        return (Integer) request.getAttribute("userId");
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));
    }

    // ===============================================================

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar")
    public List<UsuarioDTO> listar() {
        return US.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

        if (US.existsByCorreo(dto.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo ya está registrado");
        }

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        Role r = new Role();
        r.setRol("CLIENT");
        r.setUsuario(u);
        u.setRoles(List.of(r));

        US.insert(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente.");
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {

        Integer idToken = getUserIdFromToken();

        if (!isAdmin() && !idToken.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permiso para modificar este usuario.");
        }

        Usuario ex = US.listId(id);
        if (ex == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con ese ID.");

        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        u.setIdUsuario(id);
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

        Integer idToken = getUserIdFromToken();

        if (!isAdmin() && !idToken.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permiso para eliminar este usuario.");
        }

        Usuario usuario = US.listId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + id);
        }

        US.delete(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }


    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) {

        Integer idToken = getUserIdFromToken();

        if (!isAdmin() && !idToken.equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Usuario us = US.listId(id);

        if (us == null) {
            return ResponseEntity.notFound().build();
        }

        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(us, UsuarioDTO.class);

        return ResponseEntity.ok(dto);
    }

    // Obtener usuario logueado
    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        Usuario usuario = US.findByCorreo(correo);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(usuario, UsuarioDTO.class);
        return ResponseEntity.ok(dto);
    }

    // Actualizar usuario logueado
    @PutMapping("/me")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    public ResponseEntity<String> actualizarUsuarioActual(@RequestBody UsuarioDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correoActual = auth.getName();
        Usuario usuario = US.findByCorreo(correoActual);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            usuario.setNombre(dto.getNombre());
        }
        if (dto.getCorreo() != null && !dto.getCorreo().isBlank()) {
            String nuevoCorreo = dto.getCorreo();
            if (!nuevoCorreo.equals(correoActual) && US.existsByCorreo(nuevoCorreo)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está registrado");
            }
            usuario.setCorreo(nuevoCorreo);
        }
        US.update(usuario);
        return ResponseEntity.ok("Datos actualizados correctamente");
    }
}
