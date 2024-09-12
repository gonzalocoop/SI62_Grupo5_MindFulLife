package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.dtos.ComentariosDTO;
import com.zentech.si62_g5.entities.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentariosRepository extends JpaRepository<Comentarios, Integer> {
    @Query("SELECT c FROM Comentarios c JOIN c.ses s WHERE s.titulo = :tituloSesion")
    public List<Comentarios> buscarComentariosPorTituloSesion(@Param("tituloSesion") String tituloSesion);

    @Query(value ="SELECT \n" +
            "    c.titulo AS Curso,\n" +
            "    COUNT(cm.id) AS CantidadComentarios\n" +
            "  FROM \n" +
            "    Cursos c\n" +
            "  JOIN \n" +
            "    Sesiones s ON c.id = s.id_cursos\n" +
            "  JOIN \n" +
            "    Comentarios cm ON s.id = cm.id_sesiones\n" +
            "  GROUP BY \n" +
            "    c.titulo\n" +
            "  ORDER BY \n" +
            "    CantidadComentarios DESC\n" +
            "  LIMIT 3;",nativeQuery = true)
    public List<String[]> mejoresCursos();
}