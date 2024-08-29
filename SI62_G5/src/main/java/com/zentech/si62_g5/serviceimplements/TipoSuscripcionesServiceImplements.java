package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.TipoSuscripciones;
import com.zentech.si62_g5.repositories.ITipoSuscripcionesRepositories;
import com.zentech.si62_g5.serviceinterfaces.ITipoSuscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoSuscripcionesServiceImplements implements ITipoSuscripcionesService {

    @Autowired
    private ITipoSuscripcionesRepositories tR;

    @Override
    public void insert(TipoSuscripciones suscr) {
        tR.save(suscr);
    }

    @Override
    public List<TipoSuscripciones> list() {
        return tR.findAll();
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public void update(TipoSuscripciones suscr) {
        tR.save(suscr);
    }
}
