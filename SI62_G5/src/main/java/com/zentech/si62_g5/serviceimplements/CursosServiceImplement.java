package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.repositories.ICursosRepository;
import com.zentech.si62_g5.serviceinterfaces.ICursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursosServiceImplement implements ICursosService {
    @Autowired
    private ICursosRepository cR;

    @Override
    public void insert(Cursos cur) {
        cR.save(cur);
    }

    @Override
    public List<Cursos> list() {
        return cR.findAll();
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public void update(Cursos cur) {
        cR.save(cur);
    }

    @Override
    public List<Cursos> buscar(String titulo) {

        return cR.buscar(titulo);
    }

    @Override
    public List<String[]> cantSesionesCurso() {
        return cR.cantidadServicioCurso();
    }

}
