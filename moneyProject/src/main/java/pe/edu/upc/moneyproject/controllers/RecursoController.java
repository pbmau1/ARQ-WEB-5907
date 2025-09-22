package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.RecursoDTO;
import pe.edu.upc.moneyproject.entities.Recurso;
import pe.edu.upc.moneyproject.repositories.IRecursoRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IRecursoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recurso")

public class RecursoController {
    @Autowired
    private IRecursoService rS;

    @GetMapping
    public List<RecursoDTO> findAll() {
        return rS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,RecursoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insert(@RequestBody RecursoDTO recursoDTO) {
        ModelMapper m= new ModelMapper();
        Recurso recurso= m.map(recursoDTO, Recurso.class);
        rS.insert(recurso);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RecursoDTO dto) {
        ModelMapper m = new ModelMapper();
        Recurso re = m.map(dto, Recurso.class);

        Recurso existente = rS.listId(re.getIdRecurso());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + re.getIdRecurso());
        }

        rS.update(re);
        return ResponseEntity.ok("Registro con ID " + re.getIdRecurso() + " modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Recurso recurso = rS.listId(id);
        if (recurso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        rS.delete(id);
        return ResponseEntity.ok("Recurso con ID " + id + " eliminado correctamente.");
    }

    @GetMapping("/recursoporautor")
    public ResponseEntity<?> listarporautor(@RequestParam String autor) {
        List<Recurso> recursos = rS.findRecursoByAutor(autor);

        if (recursos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron recursos con el autor : " + autor);
        }

        List<RecursoDTO> listaDTO = recursos.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RecursoDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
}
