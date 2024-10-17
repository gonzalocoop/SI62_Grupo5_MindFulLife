package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.SuscripcionesDTO;
import com.zentech.si62_g5.entities.Suscripciones;
import com.zentech.si62_g5.serviceinterfaces.ISuscripcionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suscripciones")
public class SuscripcionesController {

    @Autowired
    private ISuscripcionesService tS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void registrar(@RequestBody SuscripcionesDTO dto){
        ModelMapper m = new ModelMapper();
        Suscripciones t = m.map(dto, Suscripciones.class);
        tS.insert(t);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<SuscripcionesDTO> listar()
    {
        return tS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, SuscripcionesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){

        tS.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public SuscripcionesDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        SuscripcionesDTO dto=m.map(tS.listId(id),SuscripcionesDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody SuscripcionesDTO dto){
        ModelMapper m = new ModelMapper();
        Suscripciones t = m.map(dto, Suscripciones.class);
        tS.update(t);
    }
}
