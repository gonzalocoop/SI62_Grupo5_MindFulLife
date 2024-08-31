package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.VideosDTO;

import com.zentech.si62_g5.entities.Videos;

import com.zentech.si62_g5.serviceinterfaces.IVideosService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videos")
public class VideosController {

    @Autowired
    private IVideosService vS;

    @PostMapping
    public void registrar(@RequestBody VideosDTO dto){
        ModelMapper m = new ModelMapper();
        Videos v = m.map(dto, Videos.class);
        vS.insert(v);
    }

    @GetMapping
    public List<VideosDTO> listar()
    {
        return vS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, VideosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        vS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody VideosDTO dto){
        ModelMapper m = new ModelMapper();
        Videos v = m.map(dto, Videos.class);
        vS.update(v);
    }


}
