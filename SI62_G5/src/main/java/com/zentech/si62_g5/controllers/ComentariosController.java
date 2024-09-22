package com.zentech.si62_g5.controllers;


import com.zentech.si62_g5.dtos.*;

import com.zentech.si62_g5.entities.Comentarios;

import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.repositories.IComentariosRepository;
import com.zentech.si62_g5.serviceinterfaces.IComentariosService;

import org.hibernate.mapping.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentarios")
public class ComentariosController {


    @Autowired
    private IComentariosService cS;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void registrar(@RequestBody ComentariosDTO dto){
        ModelMapper m = new ModelMapper();
        Comentarios o = m.map(dto, Comentarios.class);
        cS.insert(o);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<ComentariosDTO> listar()
    {
        return cS.list().stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, ComentariosDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void eliminar(@PathVariable("id") Integer id){

        cS.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public void modificar(@RequestBody ComentariosDTO dto){
        ModelMapper m = new ModelMapper();
        Comentarios o = m.map(dto, Comentarios.class);
        cS.update(o);
    }

    @GetMapping("/sesiontitulocomentario")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<SesionTituloComentarioDTO> buscarComentariosPorTituloSesion(@RequestParam String tituloSesion) {
        List<Comentarios> comentarios = cS.buscarComentariosPorTituloSesion(tituloSesion);
        List<SesionTituloComentarioDTO> list = new ArrayList<>();
        for (Comentarios comentario : comentarios) {
            SesionTituloComentarioDTO dto = new SesionTituloComentarioDTO();
            dto.setComentario(comentario.getComentario());
            dto.setTituloSesion(comentario.getSes().getTitulo());
            list.add(dto);
        }
        return list;
    }


    @GetMapping("/top3cursosmascomentarios")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public List<CursoCantComentariosDTO> top3Cursos(){
        List<String[]> lista= cS.topComentariosCursos();
        List<CursoCantComentariosDTO> listaDTO=new ArrayList<>();
        for(String[] columna:lista){
            CursoCantComentariosDTO dto=new CursoCantComentariosDTO();
            dto.setTitulo(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
    @GetMapping("/listarmaloscomentarios")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<ComentariosDTO>listarMalosComentarios(@RequestParam String titulo){
        return cS.listBadComents(titulo).stream().map(x->{
            ModelMapper m= new ModelMapper();
            return m.map(x, ComentariosDTO.class);
        }).collect(Collectors.toList());
    }

}