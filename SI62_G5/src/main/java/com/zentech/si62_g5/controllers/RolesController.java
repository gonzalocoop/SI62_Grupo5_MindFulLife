package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.CursosDTO;
import com.zentech.si62_g5.dtos.RolesDTO;
import com.zentech.si62_g5.serviceinterfaces.IRolesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private IRolesService rS;

    @GetMapping
    public List<RolesDTO> listar()
    {
        return rS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, RolesDTO.class);
        }).collect(Collectors.toList());
    }
}
