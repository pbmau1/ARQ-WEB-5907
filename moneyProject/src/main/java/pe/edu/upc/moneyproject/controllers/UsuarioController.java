package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AreaDTO;
import pe.edu.upc.moneyproject.dtos.UsuarioDTO;
import pe.edu.upc.moneyproject.entities.Usuario;
import pe.edu.upc.moneyproject.servicesinterfaces.IAreaService;
import pe.edu.upc.moneyproject.servicesinterfaces.IUsuarioService;

import java.awt.geom.Area;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private IUsuarioService US;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return US.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);

        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        US.insert(u);
    }
}

@RestController
@RequestMapping("/areas")
public class AreaController {
    @Autowired //inyeccion de dependencias
    private IAreaService aS;

    @GetMapping
    public List<AreaDTO> listar(){
        return aS.list().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,AreaDTO.class);

        }).collect(Collectors.toList());
    }
    @PostMapping
    public void insertar(@RequestBody AreaDTO dto){
        ModelMapper m = new ModelMapper();
        Area a = m.map(dto, Area.class);
        aS.insert(a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Area area = aS.listId(id);
        if (area == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        aS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Area area = aS.listId(id);
        if (area == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        AreaDTO dto = m.map(area, AreaDTO.class);
        return ResponseEntity.ok(dto);
    }
}