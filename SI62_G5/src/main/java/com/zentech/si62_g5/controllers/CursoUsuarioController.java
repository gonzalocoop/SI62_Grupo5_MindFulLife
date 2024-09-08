package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.CronogramasDTO;
import com.zentech.si62_g5.dtos.CursoUsuarioDTO;
import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.serviceinterfaces.ICursoUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursoscsuarios")
public class CursoUsuarioController {

    @Autowired
    private ICursoUsuarioService cuS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void registrar(@RequestBody CursoUsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        CursosUsuarios cu= m.map(dto, CursosUsuarios.class);
        cuS.insert(cu);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<CursoUsuarioDTO> listar()
    {
        return cuS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, CursoUsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void eliminar(@PathVariable("id") Integer id){
        cuS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public void modificar(@RequestBody CursoUsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        CursosUsuarios cu = m.map(dto, CursosUsuarios.class);
        cuS.update(cu);
    }
}
