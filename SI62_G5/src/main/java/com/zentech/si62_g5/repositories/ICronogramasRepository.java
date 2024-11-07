package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Cronogramas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ICronogramasRepository extends JpaRepository<Cronogramas,Integer> {
    @Query("SELECT c FROM Cronogramas c WHERE c.curUsu.usua.username = :username")
    List<Cronogramas> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cronogramas (detalle, estado, fecha_limite, id_cursos_usuarios, id_sesiones) " +
            " SELECT 'Completa tu sesión!', 'no completado', cu.fecha_fin, cu.id, s.id " +
            " FROM cursos_usuarios cu " +
            " JOIN cursos c ON c.id = cu.id_curso " +
            " JOIN sesiones s ON s.id_cursos = c.id " +
            " WHERE cu.id = :idCursoUsuario",
            nativeQuery = true)
    public void crearCronogramasParaCursoUsuario(@Param("idCursoUsuario") int idCursoUsuario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cronogramas " +
            " SET estado = 'completado' " +
            " WHERE id_sesiones = :idSesion AND id_cursos_usuarios = :idCursoUsuario",
            nativeQuery = true)
    public void actualizarEstadoCronogramas(@Param("idSesion") int idSesion, @Param("idCursoUsuario") int idCursoUsuario);

}
