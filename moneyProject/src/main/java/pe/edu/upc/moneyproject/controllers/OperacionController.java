package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.OperacionDTO;
import pe.edu.upc.moneyproject.dtos.SumaOpPorUsDTO;
import pe.edu.upc.moneyproject.dtos.UsuarioDTO;
import pe.edu.upc.moneyproject.entities.Operacion;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IOperacionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/operacion")
public class OperacionController {
    @Autowired
    private IOperacionService oS;

    @GetMapping("/listar")
    public List<OperacionDTO> findAll(){
        return oS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,OperacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public void insert(@RequestBody OperacionDTO operacionDTO){
        ModelMapper m = new ModelMapper();
        Operacion operacion = m.map(operacionDTO,Operacion.class);
        oS.insert(operacion);
    }

    @PutMapping("/update")
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

    @DeleteMapping("/delete/{id}")
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

    @GetMapping("/busquedafecha")
    public ResponseEntity<?> buscar(@RequestParam LocalDate f) {
        List<Operacion> operaciones = oS.searchOp(f);

        if (operaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron operaciones con la fecha de creación: " + f);
        }

        List<OperacionDTO> listaDTO = operaciones.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, OperacionDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/suma-por-usuario")
    public ResponseEntity<?> sumaOperacionesPorUsuario() {

        List<SumaOpPorUsDTO> listaDto = new ArrayList<>();
        List<Object[]> filas = oS.sumaOperacionesPorUsuario();

        if (filas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen operaciones registradas");
        }

        for (Object[] x : filas) {
            SumaOpPorUsDTO dto = new SumaOpPorUsDTO();
            dto.setIdUsuario(((Number) x[0]).intValue());        // id_usuario
            dto.setNombre((String) x[1]);                        // nombre
            dto.setTotalIngresos(((Number) x[2]).doubleValue()); // total_ingresos
            dto.setTotalGastos(((Number) x[3]).doubleValue());   // total_gastos
            listaDto.add(dto);
        }

        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Operacion operacion = oS.listId(id);
        if (operacion == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un operacion con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        OperacionDTO dto = m.map(operacion, OperacionDTO.class);
        return ResponseEntity.ok(dto);
    }
}
