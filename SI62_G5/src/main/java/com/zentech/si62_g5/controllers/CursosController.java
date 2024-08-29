package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.CursosDTO;
import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.serviceinterfaces.ICursosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    @Autowired
    private ICursosService cS;

    @GetMapping
    public List<CursosDTO> listar(){
        return cS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, CursosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        cS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody CursosDTO dto){
        ModelMapper m = new ModelMapper();
        Cursos c = m.map(dto, Cursos.class);
        cS.update(c);
    }

    @GetMapping ("/busquedas")
    public List<CursosDTO> BuscarTitulo(@RequestParam String tit) {
        return cS.buscar(tit).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CursosDTO.class);
        }).collect(Collectors.toList());
    }
}
