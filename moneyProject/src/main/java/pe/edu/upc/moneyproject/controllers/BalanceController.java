package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.dtos.BalanceDTO;
import pe.edu.upc.moneyproject.dtos.sumatotalingresosBalanceDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.servicesinterfaces.IBalanceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private IBalanceService BS;
    @Autowired
    private StringHttpMessageConverter stringHttpMessageConverter;

    @PreAuthorize(" hasAuthority('CLIENT')")
    @GetMapping("/listar")
    public List<BalanceDTO> findAll(){
        return BS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,BalanceDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize(" hasAuthority('CLIENT')")
    @PostMapping("/register")
    public void insert(@RequestBody BalanceDTO balanceDTO){
        ModelMapper m = new ModelMapper();
        Balance balance = m.map(balanceDTO, Balance.class);
        BS.insert(balance);
    }

    @PreAuthorize(" hasAuthority('CLIENT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Balance balance = BS.listId(id);
        if (balance == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        BS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize(" hasAuthority('CLIENT')")
    @PutMapping("/update")
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

    @PreAuthorize(" hasAuthority('CLIENT')")
    @GetMapping("/listarpormes") //siempre asignarle las rutas sin que se repitan los nombres
    public ResponseEntity<?> listarpormes(@RequestParam String mes) {
        List<Balance> balances = BS.findBalancesByMes(mes);

        if (balances.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron balances con el mes de : " + mes);
        }

        List<BalanceDTO> listaDTO = balances.stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BalanceDTO.class);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @PreAuthorize(" hasAuthority('ADMIN')" )
    @GetMapping("/sumadores") //siempre asignarle las rutas sin que se repitan los nombres
    public ResponseEntity<?> sumadetotalingr() {
        List<sumatotalingresosBalanceDTO>listaDto=new ArrayList<sumatotalingresosBalanceDTO>();
        List<String[]>fila=BS.sumadetotalingr();

        if (fila.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron registros");
        }
        for(String[] x:fila) {
            sumatotalingresosBalanceDTO dto=new sumatotalingresosBalanceDTO();
            dto.setNombre(x[0]);
            dto.setTotal_ingreso(Double.parseDouble(x[1]));
            listaDto.add(dto);
        }
        return ResponseEntity.ok(listaDto);
    }

    @PreAuthorize(" hasAuthority('CLIENT')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<BalanceDTO> findById(@PathVariable("id") Integer id) {
        Balance balance = BS.listId(id);

        if (balance == null) {
            return ResponseEntity.notFound().build();
        }

        ModelMapper m = new ModelMapper();
        BalanceDTO dto = m.map(balance, BalanceDTO.class);

        return ResponseEntity.ok(dto);
    }





}
