package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.CursosDTO;
import com.zentech.si62_g5.serviceinterfaces.ICursosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meditaciones")
public class CursosController {
    @Autowired
    private ICursosService dS;

    @GetMapping
    public List<CursosDTO> listar(){
        return dS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, CursosDTO.class);
        }).collect(Collectors.toList());
    }
}
