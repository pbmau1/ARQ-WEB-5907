package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.AhorroDTO;
import pe.edu.upc.moneyproject.entities.Ahorro;
import pe.edu.upc.moneyproject.servicesinterfaces.IAhorroService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ahorros")
public class AhorroController {
    @Autowired
    private IAhorroService aS;

    @GetMapping
    public List<AhorroDTO> findAll(){
        return aS.findAll().stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x,AhorroDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insert(@RequestBody AhorroDTO ahorroDTO){
        ModelMapper m = new ModelMapper();
        Ahorro ahorro = m.map(ahorroDTO,Ahorro.class);
        aS.insert(ahorro);
    }
}
