package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ahorros")
public class AhorroController {
    @Autowired
    private IAhorroService aS;

    @GetMapping
    public List<AhorroDTO> findAll(){
        return aS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,AhorroDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody AhorroDTO ahorroDTO){
        ModelMapper m = new ModelMapper();
        Ahorro ahorro = m.map(ahorroDTO,Ahorro.class);
        aS.insert(ahorro);

        return ResponseEntity.ok("Se registro correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody AhorroDTO ahorroDTO){
        ModelMapper m = new ModelMapper();
        Ahorro ah = m.map(ahorroDTO,Ahorro.class);

        Ahorro ahorroexiste = aS.listId(ah.getIdAhorro());
        if(ahorroexiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un ahorro con el ID: " + ah.getIdAhorro());
        }

        aS.update(ah);
        return ResponseEntity.ok("Ahorro con ID " + ah.getIdAhorro() + " modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Ahorro ahorro = aS.listId(id);
        if(ahorro==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un ahorro con el ID: " + id);
        }
        aS.delete(id);
        return ResponseEntity.ok("Ahorro con ID " + id + " eliminado correctamente.");
    }
}
