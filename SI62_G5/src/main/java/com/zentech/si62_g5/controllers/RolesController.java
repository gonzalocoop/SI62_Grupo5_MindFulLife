package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.CursosDTO;
import com.zentech.si62_g5.dtos.RolesDTO;
import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.Roles;
import com.zentech.si62_g5.serviceinterfaces.IRolesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private IRolesService rS;

   @PostMapping
    public void registrar(@RequestBody RolesDTO dto){
        ModelMapper m = new ModelMapper();
        Roles r= m.map(dto, Roles.class);
        rS.insert(r);
    }
    
    @GetMapping
    public List<RolesDTO> listar()
    {
        return rS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, RolesDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        rS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody RolesDTO dto){
        ModelMapper m = new ModelMapper();
        Roles r = m.map(dto, Roles.class);
        rS.update(r);
    }
}
