package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Comentarios;

import java.util.List;

public interface IComentariosService {
    public void insert(Comentarios com);
    public List<Comentarios> list();
    public void delete(int id);
    public void update(Comentarios com);
    List<Comentarios> buscarComentariosPorTituloSesion(String tituloSesion);
    public List<String[]> topComentariosCursos();
    public List<Comentarios> listBadComents(String titulovid);

}


