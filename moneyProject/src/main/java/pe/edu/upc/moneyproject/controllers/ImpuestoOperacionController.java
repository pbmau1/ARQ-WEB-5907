package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.ImpuestoOperacionDTO;
import pe.edu.upc.moneyproject.entities.ImpuestoOperacion;
import pe.edu.upc.moneyproject.servicesinterfaces.IImpuestoOperacionService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/impuesto-operacion")
public class ImpuestoOperacionController {
    @Autowired
    private IImpuestoOperacionService ioS;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<ImpuestoOperacionDTO> findAll(){
        return ioS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,ImpuestoOperacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void insert(@RequestBody ImpuestoOperacionDTO impuestoOperacionDTO){
        ModelMapper m = new ModelMapper();
        ImpuestoOperacion impuestoOperacion = m.map(impuestoOperacionDTO, ImpuestoOperacion.class);
        ioS.insert(impuestoOperacion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ImpuestoOperacionDTO impuestoOperacionDTO){
        ModelMapper m = new ModelMapper();
        ImpuestoOperacion io = m.map(impuestoOperacionDTO,ImpuestoOperacion.class);

        ImpuestoOperacion impuestoOperacionexiste = ioS.listId(io.getIdImpuestoOperacion());
        if(impuestoOperacionexiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + io.getIdImpuestoOperacion());
        }

        ioS.update(io);
        return ResponseEntity.ok("Registro con ID " + io.getIdImpuestoOperacion() + " modificado correctamente.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        ImpuestoOperacion impuestoOperacionexiste = ioS.listId(id);
        if(impuestoOperacionexiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        ioS.delete(id);
        return ResponseEntity.ok("Operación con ID " + id + " eliminado correctamente.");
    }

}
