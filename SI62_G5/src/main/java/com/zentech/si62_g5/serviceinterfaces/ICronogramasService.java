package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Comentarios;
import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.Roles;

import java.util.List;

public interface ICronogramasService {
    public void insert(Cronogramas cron);
    public List<Cronogramas> list();
    public void delete(int id);
    public void update(Cronogramas cron);

    List<Cronogramas> findByUsername(String username);
    public Cronogramas listId(int id);

    public void crearCronogramasParaCursoUsuario(int idCursoUsuario);

    public void actualizarEstadoCronogramas( int idSesion,  int idCursoUsuario);
}
