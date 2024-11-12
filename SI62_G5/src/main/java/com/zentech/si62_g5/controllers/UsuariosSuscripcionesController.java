package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.*;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;

import com.zentech.si62_g5.serviceinterfaces.IUsuariosSuscripcionesService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuariossuscripciones")
public class UsuariosSuscripcionesController {

    @Autowired
    private IUsuariosSuscripcionesService bS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void registrar(@RequestBody UsuariosSuscripcionesDTO dto){
        ModelMapper m=new ModelMapper();
        UsuariosSuscripciones b=m.map(dto, UsuariosSuscripciones.class);
        bS.insert(b);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<UsuariosSuscripcionesDTO> listar()
    {
        return bS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, UsuariosSuscripcionesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void eliminar(@PathVariable("id") Integer id){

        bS.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public UsuariosSuscripcionesDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        UsuariosSuscripcionesDTO dto=m.map(bS.listId(id),UsuariosSuscripcionesDTO.class);
        return dto;
    }


    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void modificar(@RequestBody UsuariosSuscripcionesDTO dto){
        ModelMapper m = new ModelMapper();
        UsuariosSuscripciones b = m.map(dto, UsuariosSuscripciones.class);
        bS.update(b);
    }

    @GetMapping("/usuariostiposuscripcion")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<UsuariosTipoSuscripcionDTO> usuariosTipoSuscripcion(@RequestParam String s){
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

    @GetMapping("/recaudacion")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<RecaudacionSuscripcionDTO> obtenerRecaudacionPorTipoSuscripcion(
            @RequestParam String nombreSuscripcion,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return bS.obtenerRecaudacionPorSuscripcion(nombreSuscripcion, fechaInicio, fechaFin);
    }


    @GetMapping("/buscarusuariosuscripcion")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<UsuariosSuscripcionesDTO>listarPorUsuarioSuscUsuario(@RequestParam String username){
        return bS.listaUsuarioSuscripcionUsuario(username).stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, UsuariosSuscripcionesDTO.class);
        }).collect(Collectors.toList());
    }
}
