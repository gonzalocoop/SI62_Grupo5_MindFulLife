package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.TipoSuscripciones;

import java.util.List;

public interface ITipoSuscripcionesService {

    public List<TipoSuscripciones> list();
    public void delete(int id);
    public void update(TipoSuscripciones suscr);
}
