package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.UsuariosTipoSuscripcionDTO;
import com.zentech.si62_g5.dtos.VideosDTO;

import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.entities.Videos;

import com.zentech.si62_g5.serviceinterfaces.IVideosService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private IVideosService vS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void registrar(@RequestBody VideosDTO dto){
        ModelMapper m = new ModelMapper();
        Videos v = m.map(dto, Videos.class);
        vS.insert(v);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<VideosDTO> listar()
    {
        return vS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, VideosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){

        vS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody VideosDTO dto){
        ModelMapper m = new ModelMapper();
        Videos v = m.map(dto, Videos.class);
        vS.update(v);
    }

    @GetMapping("/videostitulosesion")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<VideosDTO> videosTituloSesion(@RequestParam String titulo) {
        return vS.videostitulosesion(titulo).stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,VideosDTO.class);
        }).collect(Collectors.toList());
    }
}
