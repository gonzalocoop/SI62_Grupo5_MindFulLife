package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.*;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;

import com.zentech.si62_g5.serviceinterfaces.IUsuariosSuscripcionesService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuariossuscripciones")
public class UsuariosSuscripcionesController {

    @Autowired
    private IUsuariosSuscripcionesService bS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void registrar(@RequestBody UsuariosSuscripcionesDTO dto){
        ModelMapper m=new ModelMapper();
        UsuariosSuscripciones b=m.map(dto, UsuariosSuscripciones.class);
        bS.insert(b);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UsuariosSuscripcionesDTO> listar()
    {
        return bS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, UsuariosSuscripcionesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){

        bS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody UsuariosSuscripcionesDTO dto){
        ModelMapper m = new ModelMapper();
        UsuariosSuscripciones b = m.map(dto, UsuariosSuscripciones.class);
        bS.update(b);
    }

    @GetMapping("/usuariostiposuscripcion")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UsuariosTipoSuscripcionDTO> usuariostiposuscripcion(@RequestParam String s){
        List<String[]> lista= bS.usuariosSuscripcion(s);
        List<UsuariosTipoSuscripcionDTO> listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            UsuariosTipoSuscripcionDTO dto=new UsuariosTipoSuscripcionDTO();
            dto.setNombre(columna[0]);
            dto.setUsername(columna[1]);
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
