package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.OperacionDTO;
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
}
