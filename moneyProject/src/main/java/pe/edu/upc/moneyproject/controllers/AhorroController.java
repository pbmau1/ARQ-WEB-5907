package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.dtos.AhorroTotalDTO;
import pe.edu.upc.moneyproject.dtos.UsuariosDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ahorro")
public class AhorroController {
    @Autowired
    private IAhorroService aS;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/listar")
    public List<AhorroDTO> findAll(){
        return aS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,AhorroDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<String> insert(@RequestBody AhorroDTO ahorroDTO){
        ModelMapper m = new ModelMapper();
        Ahorro ahorro = m.map(ahorroDTO,Ahorro.class);
        aS.insert(ahorro);
        return ResponseEntity.ok("Se registro correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update")
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

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id){
        Ahorro ahorro = aS.listId(id);
        if(ahorro==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un ahorro con el ID: " + id);
        }
        aS.delete(id);
        return ResponseEntity.ok("Ahorro con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/periodo")
    public ResponseEntity<?> findByPeriodo(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        List<Ahorro> ahorros = aS.findByPeriodo(start,end);

        if (ahorros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron ahorros en el rango indicado");
        }

        return ResponseEntity.ok(ahorros);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/ahorrototal/{id}")
    public ResponseEntity<?> AhorroTotal(@PathVariable("id") Integer id){

        List<AhorroTotalDTO> listDTO = new ArrayList<>();
        List<String[]> total = aS.AhorroTotal(id);

        if(total.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + id + " no tiene ahorros registrados.");
        }

        for (String[] x : total) {
            AhorroTotalDTO dto = new AhorroTotalDTO();
            dto.setIdUsuario(Integer.parseInt(x[0]));
            dto.setMonto_total(Double.parseDouble(x[2]));
            dto.setNombre(x[1]);
            listDTO.add(dto);
        }

        return ResponseEntity.ok(listDTO);
    }
}
