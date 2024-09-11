package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;

import java.util.List;

public interface IUsuariosSuscripcionesService {
    public void insert(UsuariosSuscripciones usus);
    public List<UsuariosSuscripciones> list();
    public void delete(int id);
    public void update(UsuariosSuscripciones usus);
    public List<String[]> usuariosSuscripcion( String nombresuscripcion);
}
