package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;
import com.zentech.si62_g5.repositories.IUsuariosSuscripcionesRepository;
import com.zentech.si62_g5.serviceinterfaces.IUsuariosSuscripcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosSuscripcionesServiceImplement implements IUsuariosSuscripcionesService {

    @Autowired
    private IUsuariosSuscripcionesRepository bR;

    @Override
    public void insert(UsuariosSuscripciones usus) {
        bR.save(usus);
    }

    @Override
    public List<UsuariosSuscripciones> list() {
        return bR.findAll();
    }

    @Override
    public void delete(int id) {
        bR.deleteById(id);
    }

    @Override
    public void update(UsuariosSuscripciones usus) {
        bR.save(usus);
    }

    @Override
    public List<String[]> usuariosSuscripcion(String nombresuscripcion) {
        return bR.usuariosSuscripcion(nombresuscripcion);
    }
}
