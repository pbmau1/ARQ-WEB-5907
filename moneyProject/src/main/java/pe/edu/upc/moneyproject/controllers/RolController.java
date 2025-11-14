package pe.edu.upc.moneyproject.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.BalanceDTO;
import pe.edu.upc.moneyproject.dtos.RolDTO;
import pe.edu.upc.moneyproject.dtos.RoleDTO;
import pe.edu.upc.moneyproject.dtos.UsuarioDTO;
import pe.edu.upc.moneyproject.entities.Balance;
import pe.edu.upc.moneyproject.entities.Role;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Rol")
public class RolController {
    @Autowired
    private IRolService RS;

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/register")
    public void insert(@RequestBody RolDTO dto){
        ModelMapper m = new ModelMapper();
        Role r = m.map(dto, Role.class);
        RS.insert(r);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/update")
    public ResponseEntity<String> modificar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Role r = m.map(dto, Role.class);

        Role existente = RS.listId(Math.toIntExact(r.getId()));
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un Rol con el ID: " + r.getId());
        }

        RS.update(r);
        return ResponseEntity.ok("Rol con ID " + r.getId() + " modificado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Role r = RS.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un Rol con el ID: " + id);
        }
        RS.delete(id);
        return ResponseEntity.ok("Rol con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/listar")
    public List<RolDTO> list(){
        return RS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,RolDTO.class);
        }).collect(Collectors.toList());
    }




}
