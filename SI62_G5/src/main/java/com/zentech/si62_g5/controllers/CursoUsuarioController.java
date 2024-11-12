package com.zentech.si62_g5.controllers;

import com.zentech.si62_g5.dtos.*;
import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.serviceinterfaces.ICursoUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void registrar(@RequestBody CursoUsuarioDTO dto){
        ModelMapper m = new ModelMapper();
        CursosUsuarios cu= m.map(dto, CursosUsuarios.class);
        cuS.insert(cu);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
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
    @GetMapping("/obtenerurlparacompartirla")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<ObtenerUrlDTO>obtenerConUsuarioUrl(@RequestParam String nombreUsuario,@RequestParam String nombreCurso){
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
    @GetMapping("/cantidaddecursoscompletadosynocompletados")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<CantCursCompleNoCompleDTO>cantidadCursosCompletadosNoCompletados(){
        List<String[]> lista= cuS.cantidadDeCursosCompletadosYNoCompletados();
        List<CantCursCompleNoCompleDTO>listaDTO=new ArrayList<>();
        for (String[] columna : lista) {
            CantCursCompleNoCompleDTO dto = new CantCursCompleNoCompleDTO();
            dto.setUsername(columna[0]);
            dto.setCursosCompletados(Integer.parseInt(columna[1]));
            dto.setCursosNoCompletados(Integer.parseInt(columna[2]));

            listaDTO.add(dto);
        }
        return listaDTO;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public CursoUsuarioDTO listarId(@PathVariable("id") Integer id){
        ModelMapper m=new ModelMapper();
        CursoUsuarioDTO dto=m.map(cuS.listId(id),CursoUsuarioDTO.class);
        return dto;
    }

    @PostMapping("/registrarcurso")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public ResponseEntity<MensajeDTO> registrarUsuarioEnCurso(@RequestParam int idCurso, @RequestParam int idUsuario) {
        try {
            cuS.registrarUsuarioEnCurso(idCurso, idUsuario);
            MensajeDTO dto = new MensajeDTO();
            dto.setMensaje("Curso registrado. Felicidades!");
            return ResponseEntity.ok(dto); // Respuesta de éxito
        } catch (IllegalArgumentException e) {
            // Maneja el caso donde el usuario ya está registrado
            MensajeDTO dto = new MensajeDTO();
            dto.setMensaje(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(dto); // 409 Conflict
        }
    }


    @GetMapping("listarseguncursousuario")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public CursoUsuarioDTO findByCursoAndUsuario(@RequestParam int idCurso, @RequestParam int idUsuario){
        ModelMapper m=new ModelMapper();
        CursoUsuarioDTO dto=m.map(cuS.findByCursoAndUsuario(idCurso,idUsuario),CursoUsuarioDTO.class);
        return dto;
    }

    @PutMapping("/actualizarprogreso")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public void actualizarProgresoYEstado(@RequestParam int idCursoUsuario) {
        cuS.actualizarProgresoYEstado(idCursoUsuario);
    }

    @GetMapping("buscarcursosusuariosporusuario")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR','ESTUDIANTE')")
    public List<CursoUsuarioDTO> buscarPorUsuario(@RequestParam String username) {
        return cuS.findByUsername(username).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CursoUsuarioDTO.class);
        }).collect(Collectors.toList());
    }
}
