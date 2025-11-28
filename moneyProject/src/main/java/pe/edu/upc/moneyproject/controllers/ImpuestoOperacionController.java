package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.ImpuestoOperacionDTO;
import pe.edu.upc.moneyproject.dtos.obtenerImpuestosIngresosDTO;
import pe.edu.upc.moneyproject.entities.ImpuestoOperacion;
import pe.edu.upc.moneyproject.repositories.IImpuestoOperacionRepository;
import pe.edu.upc.moneyproject.servicesinterfaces.IImpuestoOperacionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/impuesto-operacion")
public class ImpuestoOperacionController {
    @Autowired
    private IImpuestoOperacionService ioS;
    @Autowired
    private IImpuestoOperacionRepository iImpuestoOperacionRepository;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar")
    public List<ImpuestoOperacionDTO> findAll(){
        return ioS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,ImpuestoOperacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PostMapping("/register")
    public void insert(@RequestBody ImpuestoOperacionDTO impuestoOperacionDTO){
        ModelMapper m = new ModelMapper();
        ImpuestoOperacion impuestoOperacion = m.map(impuestoOperacionDTO, ImpuestoOperacion.class);
        ioS.insert(impuestoOperacion);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @PutMapping("/update")
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

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        ImpuestoOperacion impuestoOperacionexiste = ioS.listId(id);
        if(impuestoOperacionexiste==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un recurso con el ID: " + id);
        }
        ioS.delete(id);
        return ResponseEntity.ok("Operación con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<ImpuestoOperacionDTO> findById(@PathVariable("id") Integer id) {
        ImpuestoOperacion io = ioS.listId(id);

        if (io == null) {
            return ResponseEntity.notFound().build();
        }

        ModelMapper m = new ModelMapper();
        ImpuestoOperacionDTO dto = m.map(io, ImpuestoOperacionDTO.class);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CLIENT')")
    @GetMapping("/obtenerimpuestos")
    public List<obtenerImpuestosIngresosDTO> obtenerImpuestos_IngresosDTO() {
        List<Object[]> datos = iImpuestoOperacionRepository.obtenerImpuestosIngresos();

        List<obtenerImpuestosIngresosDTO> lista = new ArrayList<>();

        for (Object[] fila : datos) {
            obtenerImpuestosIngresosDTO dto = new obtenerImpuestosIngresosDTO(
                    (String) fila[0],                 // categoriaDeImpuesto
                    ((Number) fila[1]).doubleValue(), // tasa
                    (String) fila[2],                 // tipoIngreso
                    (String) fila[3],                 // categoriaDeOperacion
                    ((Number) fila[4]).doubleValue(), // montoOperacion
                    ((Number) fila[5]).doubleValue()  // montoFinal
            );
            lista.add(dto);
        }

        return lista;
    }
}
