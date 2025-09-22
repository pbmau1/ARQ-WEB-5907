package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.RecursoUsuarioDTO;
import pe.edu.upc.moneyproject.entities.RecursoUsuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IRecursoUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recurso-usuario")
public class RecursoUsuarioController {
    @Autowired
    private IRecursoUsuarioService ruS;

    @GetMapping
    public List<RecursoUsuarioDTO> findAll(){
        return ruS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,RecursoUsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insert(@RequestBody RecursoUsuarioDTO recursoUsuarioDTO){
        ModelMapper m = new ModelMapper();
        RecursoUsuario recursoUsuario = m.map(recursoUsuarioDTO, RecursoUsuario.class);
        ruS.insert(recursoUsuario);
    }

    @PutMapping
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        RecursoUsuario recursoUsuarioExiste = ruS.listId(id);
        if(recursoUsuarioExiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        ruS.delete(id);
        return ResponseEntity.ok("Operación con ID " + id + " eliminado correctamente.");
    }

}
