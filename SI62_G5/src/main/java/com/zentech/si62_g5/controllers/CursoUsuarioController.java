package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.CantSesionesCursoDTO;
import com.zentech.si62_g5.dtos.CronogramasDTO;
import com.zentech.si62_g5.dtos.CursoUsuarioDTO;
import com.zentech.si62_g5.dtos.ObtenerUrlDTO;
import com.zentech.si62_g5.entities.Comentarios;
import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.serviceinterfaces.ICursoUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/obtenerurl")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','USUARIO')")
    public List<ObtenerUrlDTO>ObtenerConUsuarioUrl(@RequestParam String nombreUsuario,@RequestParam String nombreCurso){
        List<String[]> lista= cuS.ObtenerUrl(nombreUsuario,nombreCurso);
        List<ObtenerUrlDTO>listaDTO=new ArrayList<>();
        for (String[] columna : lista) {
            ObtenerUrlDTO dto = new ObtenerUrlDTO();
            dto.setUsername(columna[0]);  // Asignar el nombre del usuario
            dto.setTitulo(columna[1]);  // Asignar el título del curso
            dto.setEstado(columna[2]);  // Asignar el estado del curso
            dto.setUrl(columna[3]);  // Asignar la URL del curso

            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
