package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.moneyproject.dtos.OperacionDTO;
import pe.edu.upc.moneyproject.dtos.Query1;
import pe.edu.upc.moneyproject.dtos.UsuarioDTO;
import pe.edu.upc.moneyproject.dtos.UsuariosDTO;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private IUsuarioService US;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/listar")
    public List<UsuarioDTO> listar() {
        return US.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);

        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/listar/users")
    public ResponseEntity<?> MostrarUsuarios() {

        List<UsuariosDTO>list =new ArrayList<>();
        List<String[]>fila = US.findUsuarios();

        if(fila.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen usuarios registrados" );
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
    public void insertar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        if (u.getRoles() != null) {
            u.getRoles().forEach(role -> role.setUsuario(u));
        }
        US.insert(u);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO dto) {
        ModelMapper m =  new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        Usuario ex = US.listId(u.getIdUsuario());
        if (ex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + u.getIdUsuario());
        }
        US.update(u);
        return ResponseEntity.ok("Usuario con ID " + u.getIdUsuario() + " modificado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario usuario = US.listId(id);
        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un usuario con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(usuario, UsuarioDTO.class);
        return ResponseEntity.ok(dto);
    }
}
