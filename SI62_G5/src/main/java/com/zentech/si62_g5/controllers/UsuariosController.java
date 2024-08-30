package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.SesionesDTO;
import com.zentech.si62_g5.dtos.UsuariosDTO;
import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.entities.Usuarios;
import com.zentech.si62_g5.serviceinterfaces.ISesionesService;
import com.zentech.si62_g5.serviceinterfaces.IUsuariosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService uS;

    @PostMapping
    public void registrar(@RequestBody UsuariosDTO dto){
        ModelMapper m = new ModelMapper();
        Usuarios s= m.map(dto, Usuarios.class);
        uS.insert(s);
    }

    @GetMapping
    public List<UsuariosDTO> listar()
    {
        return uS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, UsuariosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id){

        uS.delete(id);
    }

    @PutMapping
    public void modificar(@RequestBody UsuariosDTO dto){
        ModelMapper m = new ModelMapper();
        Usuarios s = m.map(dto, Usuarios.class);
        uS.update(s);
    }

}
