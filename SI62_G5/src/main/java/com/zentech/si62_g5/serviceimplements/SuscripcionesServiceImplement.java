package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Suscripciones;
import com.zentech.si62_g5.repositories.ISuscripcionesRepositories;
import com.zentech.si62_g5.serviceinterfaces.ISuscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuscripcionesServiceImplement implements ISuscripcionesService {

    @Autowired
    private ISuscripcionesRepositories tR;

    @Override
    public void insert(Suscripciones suscr) {
        tR.save(suscr);
    }

    @Override
    public List<Suscripciones> list() {
        return tR.findAll();
    }

    @Override
    public void delete(int id) {
        tR.deleteById(id);
    }

    @Override
    public void update(Suscripciones suscr) {
        tR.save(suscr);
    }
}
