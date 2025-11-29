package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.RecursoDTO;
import pe.edu.upc.moneyproject.dtos.RecursoUsuarioDTO;
import pe.edu.upc.moneyproject.entities.Recurso;
import pe.edu.upc.moneyproject.entities.RecursoUsuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IRecursoUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/recurso-usuario")
public class RecursoUsuarioController {
    @Autowired
    private IRecursoUsuarioService ruS;

    @PreAuthorize("hasAuthority('ADMIN') ")
    @GetMapping("/listar")
    public List<RecursoUsuarioDTO> findAll(){
        return ruS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,RecursoUsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('ADMIN') ")
    @PostMapping("/register")
    public void insert(@RequestBody RecursoUsuarioDTO recursoUsuarioDTO){
        ModelMapper m = new ModelMapper();
        RecursoUsuario recursoUsuario = m.map(recursoUsuarioDTO, RecursoUsuario.class);
        ruS.insert(recursoUsuario);
    }

    @PreAuthorize("hasAuthority('ADMIN') ")
    @PutMapping("/update")
    public ResponseEntity<String> modificar(@RequestBody RecursoUsuarioDTO recursoUsuarioDTO){
        ModelMapper m = new ModelMapper();
        RecursoUsuario ru = m.map(recursoUsuarioDTO,RecursoUsuario.class);

        RecursoUsuario recursoUsuarioExiste = ruS.listId(ru.getIdRecursoUsuario());
        if(recursoUsuarioExiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + ru.getIdRecursoUsuario());
        }

        ruS.update(ru);
        return ResponseEntity.ok("Registro con ID " + ru.getIdRecursoUsuario() + " modificado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        RecursoUsuario recursoUsuarioExiste = ruS.listId(id);
        if(recursoUsuarioExiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        ruS.delete(id);
        return ResponseEntity.ok("Operación con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') ")
    @GetMapping("/listar/{id}")
    public ResponseEntity<RecursoUsuarioDTO> findById(@PathVariable("id") Integer id) {
        RecursoUsuario recursoUsuario = ruS.listId(id);

        if (recursoUsuario == null) {
            return ResponseEntity.notFound().build();
        }

        ModelMapper m = new ModelMapper();
        RecursoUsuarioDTO dto = m.map(recursoUsuario, RecursoUsuarioDTO.class);

        return ResponseEntity.ok(dto);
    }

}
