package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.CronogramasDTO;

import com.zentech.si62_g5.entities.Cronogramas;

import com.zentech.si62_g5.serviceinterfaces.ICronogramasService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cronogramas")
public class CronogramasController {

    @Autowired
    private ICronogramasService cS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void registrar(@RequestBody CronogramasDTO dto){
        ModelMapper m = new ModelMapper();
        Cronogramas c= m.map(dto, Cronogramas.class);
        cS.insert(c);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<CronogramasDTO> listar()
    {
        return cS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, CronogramasDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){

        cS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody CronogramasDTO dto){
        ModelMapper m = new ModelMapper();
        Cronogramas c = m.map(dto, Cronogramas.class);
        cS.update(c);
    }


}
