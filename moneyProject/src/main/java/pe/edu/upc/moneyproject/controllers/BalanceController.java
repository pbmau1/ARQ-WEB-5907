package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.dtos.BalanceDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.servicesinterfaces.IBalanceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private IBalanceService BS;

    @GetMapping
    public List<BalanceDTO> findAll(){
        return BS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,BalanceDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insert(@RequestBody BalanceDTO balanceDTO){
        ModelMapper m = new ModelMapper();
        Balance balance = m.map(balanceDTO, Balance.class);
        BS.insert(balance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Balance balance = BS.listId(id);
        if (balance == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        BS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody BalanceDTO dto) {
        ModelMapper m = new ModelMapper();
        Balance br = m.map(dto, Balance.class);

        Balance existente = BS.listId(br.getIdBalance());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + br.getIdBalance());
        }

        BS.update(br);
        return ResponseEntity.ok("Registro con ID " + br.getIdBalance() + " modificado correctamente.");
    }

}
