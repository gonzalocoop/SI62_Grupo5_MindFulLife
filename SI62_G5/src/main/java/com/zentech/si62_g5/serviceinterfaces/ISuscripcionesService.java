package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Suscripciones;

import java.util.List;

public interface ISuscripcionesService {

    public void insert(Suscripciones suscr);
    public List<Suscripciones> list();
    public void delete(int id);
    public void update(Suscripciones suscr);
    public Suscripciones listId(int id);
}
