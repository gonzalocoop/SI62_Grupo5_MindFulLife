package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.ComentariosUsuarioDTO;
import com.zentech.si62_g5.dtos.UsuariosDTO;
import com.zentech.si62_g5.entities.Usuarios;
import com.zentech.si62_g5.serviceinterfaces.IUsuariosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService uS;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void registrar(@RequestBody UsuariosDTO dto){
        ModelMapper m = new ModelMapper();
        Usuarios s= m.map(dto, Usuarios.class);
        String encodedPassword = passwordEncoder.encode(s.getPassword());
        s.setPassword(encodedPassword);
        uS.insert(s);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<UsuariosDTO> listar()
    {
        return uS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, UsuariosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void eliminar(@PathVariable("id") Integer id){

        uS.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public UsuariosDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        UsuariosDTO dto=m.map(uS.listId(id),UsuariosDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void modificar(@RequestBody UsuariosDTO dto){
        ModelMapper m = new ModelMapper();
        Usuarios s = m.map(dto, Usuarios.class);
        uS.update(s);
    }

    @PutMapping("/cambiocoontraseña")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void cambiarContrasena(@RequestParam String u, @RequestParam String p){
        String encodedPassword = passwordEncoder.encode(p);
        uS.cambioPassword(u,encodedPassword);
    }

    @GetMapping("/comentariosusuario")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<ComentariosUsuarioDTO> comentariosUsuario(@RequestParam String u){
        List<String[]> lista= uS.comentarioUsuario(u);
        List<ComentariosUsuarioDTO> listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            ComentariosUsuarioDTO dto=new ComentariosUsuarioDTO();
            dto.setNombre(columna[0]);
            dto.setComentario(columna[1]);
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @PutMapping("/encript")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void modificarEncript(@RequestBody UsuariosDTO dto){
        ModelMapper m = new ModelMapper();
        Usuarios s = m.map(dto, Usuarios.class);
        String encodedPassword = passwordEncoder.encode(s.getPassword());
        s.setPassword(encodedPassword);
        uS.update(s);
    }

    @GetMapping("/usuarioporusername")
    public UsuariosDTO listarPorUsername(@RequestParam String u)
    {
        ModelMapper m=new ModelMapper();
        UsuariosDTO dto=m.map(uS.findUsuarioByUsername(u),UsuariosDTO.class);
        return dto;
    }


}
