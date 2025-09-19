package pe.edu.upc.moneyproject.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.moneyproject.dtos.ImpuestoDTO;
import pe.edu.upc.moneyproject.entities.Impuesto;
import pe.edu.upc.moneyproject.servicesinterfaces.IImpuestoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/impuestos")
public class ImpuestoController {
    @Autowired
    private IImpuestoService iS;

    @GetMapping
    public List<ImpuestoDTO> findAll() {
        return iS.findAll().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ImpuestoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insert(@RequestBody ImpuestoDTO impuestoDTO) {
        ModelMapper m = new ModelMapper();
        Impuesto impuesto = m.map(impuestoDTO, Impuesto.class);
        iS.insert(impuesto);
    }
}

