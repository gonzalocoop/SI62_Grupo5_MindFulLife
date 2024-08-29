package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Cursos;

import java.util.List;

public interface ICursosService {

    public void insert(Cursos cur);
    public List<Cursos> list();
    public void delete(int id);
    public void update(Cursos cur);
    public List<Cursos> buscar(String titulo);
}
