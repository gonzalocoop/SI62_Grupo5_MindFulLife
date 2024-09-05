package com.zentech.si62_g5.controllers;



import com.zentech.si62_g5.dtos.PromedioVideosDTO;
import com.zentech.si62_g5.dtos.SesionesDTO;

import com.zentech.si62_g5.entities.Sesiones;

import com.zentech.si62_g5.serviceinterfaces.ISesionesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void registrar(@RequestBody SesionesDTO dto){
        ModelMapper m = new ModelMapper();
        Sesiones s= m.map(dto, Sesiones.class);
        sS.insert(s);
    }

    @GetMapping
    public List<SesionesDTO> listar()
    {
        return sS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, SesionesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        sS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody SesionesDTO dto){
        ModelMapper m = new ModelMapper();
        Sesiones s = m.map(dto, Sesiones.class);
        sS.update(s);
    }
















    @GetMapping ("/promediovideos")
    public List<PromedioVideosDTO> montoTotalDispositivoMantenimiento()
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
}
