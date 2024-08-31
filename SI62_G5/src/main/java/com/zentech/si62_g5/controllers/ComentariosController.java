package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.ComentariosDTO;

import com.zentech.si62_g5.entities.Comentarios;

import com.zentech.si62_g5.serviceinterfaces.IComentariosService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios")
public class ComentariosController {

    @Autowired
    private IComentariosService oS;

    @PostMapping
    public void registrar(@RequestBody ComentariosDTO dto){
        ModelMapper m = new ModelMapper();
        Comentarios o = m.map(dto, Comentarios.class);
        oS.insert(o);
    }

    @GetMapping
    public List<ComentariosDTO> listar()
    {
        return oS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, ComentariosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        oS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody ComentariosDTO dto){
        ModelMapper m = new ModelMapper();
        Comentarios o = m.map(dto, Comentarios.class);
        oS.update(o);
    }


}
