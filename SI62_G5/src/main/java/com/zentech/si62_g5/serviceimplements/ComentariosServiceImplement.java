package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Comentarios;
import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.repositories.IComentariosRepository;
import com.zentech.si62_g5.serviceinterfaces.IComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentariosServiceImplement implements IComentariosService {
    @Autowired
    private IComentariosRepository oR;

    @Override
    public void insert(Comentarios com) {
        oR.save(com);
    }

    @Override
    public List<Comentarios> list() {
        return oR.findAll();
    }

    @Override
    public void delete(int id) {
        oR.deleteById(id);
    }

    @Override
    public void update(Comentarios com) {
        oR.save(com);
    }

    @Override
    public List<Comentarios> buscarComentariosPorTituloSesion(String tituloSesion) {
        return oR.buscarComentariosPorTituloSesion(tituloSesion);
    }

    @Override
    public List<String[]> topComentariosCursos() {
        return oR.topComentariosCursos();
    }

    @Override
    public List<Comentarios> listBadComents(String titulovid) {
        return oR.listaComentariosMalos(titulovid);
    }

    @Override
    public Comentarios listId(int id) {
        return oR.findById(id).orElse(new Comentarios());
    }

    @Override
    public List<Comentarios> findByUsername(String username) {
        return oR.findByUsername(username);
    }


}


