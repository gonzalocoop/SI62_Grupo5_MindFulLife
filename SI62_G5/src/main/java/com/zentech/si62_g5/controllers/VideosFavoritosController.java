package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.VideosFavoritosDTO;

import com.zentech.si62_g5.entities.VideosFavoritos;

import com.zentech.si62_g5.serviceinterfaces.IVideosFavoritosService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videosfavoritos")
public class VideosFavoritosController {
    @Autowired
    private IVideosFavoritosService fS;

    @PostMapping
    public void registrar(@RequestBody VideosFavoritosDTO dto){
        ModelMapper m = new ModelMapper();
        VideosFavoritos f = m.map(dto, VideosFavoritos.class);
        fS.insert(f);
    }

    @GetMapping
    public List<VideosFavoritosDTO> listar()
    {
        return fS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, VideosFavoritosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        fS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody VideosFavoritosDTO dto){
        ModelMapper m = new ModelMapper();
        VideosFavoritos f = m.map(dto, VideosFavoritos.class);
        fS.update(f);
    }
}
