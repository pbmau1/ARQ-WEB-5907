package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.BalanceDTO;
import pe.edu.upc.moneyproject.dtos.ImpuestoDTO;
import pe.edu.upc.moneyproject.dtos.ImpuestoResumenDTO;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.entities.Impuesto;
import pe.edu.upc.moneyproject.servicesinterfaces.IImpuestoService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/impuestos")
public class ImpuestoController {
    @Autowired
    private IImpuestoService iS;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/listar")
    public List<ImpuestoDTO> findAll() {
        return iS.findAll().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ImpuestoDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public void insert(@RequestBody ImpuestoDTO impuestoDTO) {
        ModelMapper m = new ModelMapper();
        Impuesto impuesto = m.map(impuestoDTO, Impuesto.class);
        iS.insert(impuesto);
    }

    //  PUT - modificar un impuesto
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
        public ResponseEntity<String> modificar(@RequestBody ImpuestoDTO impuestoDTO){
            ModelMapper m = new ModelMapper();
            Impuesto impuesto = m.map(impuestoDTO, Impuesto.class);

            Impuesto impuestoExiste = iS.listId(impuesto.getIdImpuesto());
            if(impuestoExiste == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se puede modificar. No existe un impuesto con el ID: " + impuesto.getIdImpuesto());
            }

            iS.update(impuesto);
            return ResponseEntity.ok("Impuesto con ID " + impuesto.getIdImpuesto() + " modificado correctamente.");
        }

        //  DELETE - eliminar un impuesto
        @PreAuthorize("hasAuthority('ADMIN')")
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
            Impuesto impuesto = iS.listId(id);
            if(impuesto == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe un impuesto con el ID: " + id);
            }
            iS.delete(id);
            return ResponseEntity.ok("Impuesto con ID " + id + " eliminado correctamente.");}

    @GetMapping("/resumen-tipo")
    public ResponseEntity<?> obtenerTotalesPorTipo() {
        List<ImpuestoResumenDTO> lista = iS.obtenerTotalesPorTipo();

        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existen registros de impuestos agrupados por tipo.");
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/promedio-tipo")
    public ResponseEntity<?> obtenerPromediosPorTipo() {
        List<Object[]> resultados = iS.obtenerPromediosPorTipo();
        if (resultados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron impuestos para calcular el promedio.");
        }
        return ResponseEntity.ok(resultados);
    }


}
