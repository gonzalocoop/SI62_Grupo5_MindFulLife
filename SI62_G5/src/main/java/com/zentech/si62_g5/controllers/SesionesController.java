package com.zentech.si62_g5.controllers;



import com.zentech.si62_g5.dtos.PromedioVideosDTO;
import com.zentech.si62_g5.dtos.RolesDTO;
import com.zentech.si62_g5.dtos.SesionCantidadVideoDTO;
import com.zentech.si62_g5.dtos.SesionesDTO;

import com.zentech.si62_g5.entities.Sesiones;

import com.zentech.si62_g5.serviceinterfaces.ISesionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sesiones")
public class SesionesController {

    @Autowired
    private ISesionesService sS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void registrar(@RequestBody SesionesDTO dto){
        ModelMapper m = new ModelMapper();
        Sesiones s= m.map(dto, Sesiones.class);
        sS.insert(s);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<SesionesDTO> listar()
    {
        return sS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, SesionesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){

        sS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody SesionesDTO dto){
        ModelMapper m = new ModelMapper();
        Sesiones s = m.map(dto, Sesiones.class);
        sS.update(s);
    }
    @GetMapping ("/sesionvideoduracion")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<SesionCantidadVideoDTO> sesionVideoDuracion()
    {

        List<String[]> lista= sS.SesionCantidadVideo();
        List<SesionCantidadVideoDTO> listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            SesionCantidadVideoDTO dto=new SesionCantidadVideoDTO();
            dto.setTituloSesion(columna[0]);
            dto.setTituloVideo(columna[1]);
            dto.setDuracionVideo(new BigDecimal(columna[2]));
            listaDTO.add(dto);
        }
        return listaDTO;
    };

    @GetMapping ("/promediovideos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<PromedioVideosDTO> promedioDeVideos()
    {

        List<String[]> lista= sS.avgDuracionVideo();
        List<PromedioVideosDTO> listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            PromedioVideosDTO dto=new PromedioVideosDTO();
            dto.setIdSesion(Integer.parseInt(columna[0]));
            dto.setTituloSesion(columna[1]);
            dto.setDuracionPromedio(new BigDecimal(columna[2]));
            listaDTO.add(dto);
        }
        return listaDTO;
    };

    @GetMapping("/buscarsesionesporcurso")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<SesionesDTO>listarPorCursoSesion(@RequestParam String c){
        return sS.findAllSesionByCurso(c).stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, SesionesDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public SesionesDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        SesionesDTO dto=m.map(sS.listId(id),SesionesDTO.class);
        return dto;
    }


}
