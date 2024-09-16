package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.CantSesionesCursoDTO;
import com.zentech.si62_g5.dtos.CursosDTO;
import com.zentech.si62_g5.dtos.MaxMinUsuarioCursosDTO;
import com.zentech.si62_g5.dtos.PromedioVideosDTO;
import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.serviceinterfaces.ICursosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    @Autowired
    private ICursosService cS;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void registrar(@RequestBody CursosDTO dto){
        ModelMapper m = new ModelMapper();
        Cursos c= m .map(dto, Cursos.class);
        cS.insert(c);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<CursosDTO> listar(){
        return cS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, CursosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){

        cS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody CursosDTO dto){
        ModelMapper m = new ModelMapper();
        Cursos c = m.map(dto, Cursos.class);
        cS.update(c);
    }

    @GetMapping ("/busquedas")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<CursosDTO> BuscarTitulo(@RequestParam String tit) {
        return cS.buscar(tit).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CursosDTO.class);
        }).collect(Collectors.toList());
    }
    @GetMapping("/cantidadsesionescurso")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<CantSesionesCursoDTO>cantidadSesionesCurso(){
        List<String[]> lista=cS.cantSesionesCurso();
        List<CantSesionesCursoDTO>listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            CantSesionesCursoDTO dto=new CantSesionesCursoDTO();
            dto.setTitulo(columna[0]);
            dto.setQuatitySesion(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @GetMapping ("/maxyminwsuariocursos")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<MaxMinUsuarioCursosDTO> MaxyMinUsuarioCursos()
    {

        List<String[]> lista= cS.MaxyMinUsuarioCursos();
        List<MaxMinUsuarioCursosDTO> listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            MaxMinUsuarioCursosDTO dto=new MaxMinUsuarioCursosDTO();
            dto.setIdCurso(columna[0]);
            dto.setNombreCurso(columna[1]);
            dto.setNumUsuarios(Integer.parseInt(columna[2]));
            dto.setCategoria(columna[3]);
            listaDTO.add(dto);
        }
        return listaDTO;
    };
}
