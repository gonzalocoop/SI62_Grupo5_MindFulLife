package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.SesionesDTO;
import com.zentech.si62_g5.dtos.VideosFavoritosDTO;

import com.zentech.si62_g5.entities.VideosFavoritos;

import com.zentech.si62_g5.serviceinterfaces.IVideosFavoritosService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videosfavoritos")
public class VideosFavoritosController {
    @Autowired
    private IVideosFavoritosService fS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void registrar(@RequestBody VideosFavoritosDTO dto){
        ModelMapper m = new ModelMapper();
        VideosFavoritos f = m.map(dto, VideosFavoritos.class);
        fS.insert(f);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<VideosFavoritosDTO> listar()
    {
        return fS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, VideosFavoritosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void eliminar(@PathVariable("id") Integer id){

        fS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void modificar(@RequestBody VideosFavoritosDTO dto){
        ModelMapper m = new ModelMapper();
        VideosFavoritos f = m.map(dto, VideosFavoritos.class);
        fS.update(f);
    }

    @GetMapping("/buscarusuariovideofav")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<VideosFavoritosDTO>listarPorUsuarioVideoFav(@RequestParam String u){
        return fS.findAllVideoFavByUsuario(u).stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, VideosFavoritosDTO.class);
        }).collect(Collectors.toList());
    }
}
