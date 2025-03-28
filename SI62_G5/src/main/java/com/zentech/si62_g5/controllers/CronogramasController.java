package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.ComentariosDTO;
import com.zentech.si62_g5.dtos.CronogramasDTO;

import com.zentech.si62_g5.entities.Cronogramas;

import com.zentech.si62_g5.serviceinterfaces.ICronogramasService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
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

    @GetMapping("buscarcronogramaxusernameusuario")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<CronogramasDTO> buscarPorUsuario(@RequestParam String username) {
        return cS.findByUsername(username).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CronogramasDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public CronogramasDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        CronogramasDTO dto=m.map(cS.listId(id),CronogramasDTO.class);
        return dto;
    }

    @PostMapping("generar")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void crearCronogramas(@RequestParam Integer idCursoUsuario) {
        cS.crearCronogramasParaCursoUsuario(idCursoUsuario);
    }

    @PutMapping("/actualizarestado")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void actualizarEstadoCronogramas(@RequestParam int idSesion, @RequestParam int idCursoUsuario) {
        cS.actualizarEstadoCronogramas(idSesion, idCursoUsuario);
    }
}
