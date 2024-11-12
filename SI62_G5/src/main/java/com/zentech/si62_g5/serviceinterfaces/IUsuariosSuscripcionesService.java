package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.dtos.RecaudacionSuscripcionDTO;
import com.zentech.si62_g5.entities.Usuarios;
import com.zentech.si62_g5.entities.UsuariosSuscripciones;

import java.time.LocalDate;
import java.util.List;

public interface IUsuariosSuscripcionesService {
    public void insert(UsuariosSuscripciones usus);
    public List<UsuariosSuscripciones> list();
    public void delete(int id);
    public void update(UsuariosSuscripciones usus);
    public List<String[]> usuariosSuscripcion( String nombresuscripcion);
    public UsuariosSuscripciones listId(int id);

    List<RecaudacionSuscripcionDTO> obtenerRecaudacionPorSuscripcion(String nombreSuscripcion, LocalDate fechaInicio, LocalDate fechaFin);

    public List<UsuariosSuscripciones> listaUsuarioSuscripcionUsuario(String username);
}
