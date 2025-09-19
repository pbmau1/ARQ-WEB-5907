package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.dtos.OperacionDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.entities.Operacion;
import pe.edu.upc.moneyproject.servicesinterfaces.IOperacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/operaciones")
public class OperacionController {
    @Autowired
    private IOperacionService oS;

    @GetMapping
    public List<OperacionDTO> findAll(){
        return oS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,OperacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insert(@RequestBody OperacionDTO operacionDTO){
        ModelMapper m = new ModelMapper();
        Operacion operacion = m.map(operacionDTO,Operacion.class);
        oS.insert(operacion);
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody OperacionDTO operacionDTO){
        ModelMapper m = new ModelMapper();
        Operacion op = m.map(operacionDTO,Operacion.class);

        Operacion operacionexiste = oS.listId(op.getIdOperacion());
        if(operacionexiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + op.getIdOperacion());
        }

        oS.update(op);
        return ResponseEntity.ok("Registro con ID " + op.getIdOperacion() + " modificado correctamente.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Operacion operacion = oS.listId(id);
        if(operacion==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        oS.delete(id);
        return ResponseEntity.ok("Operación con ID " + id + " eliminado correctamente.");
    }

    @GetMapping("/listarporcategoria") //siempre asignarle las rutas sin que se repitan los nombres
    public ResponseEntity<?> listarporcategoria(@RequestParam String categoria) {
        List<Operacion> operaciones = oS.findOperacionByCategoria(categoria);

        if (operaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron operaciones con esa categoria de : " + categoria);
        }

        List<OperacionDTO> listaDTO = operaciones.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, OperacionDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }
}
