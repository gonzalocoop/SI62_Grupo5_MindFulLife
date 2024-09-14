package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.entities.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICursoUsuarioRepository extends JpaRepository<CursosUsuarios,Integer> {

        @Query(value = " SELECT u.username, c.titulo, cu.estado, cu.url\n" +
                " FROM Cursos_Usuarios cu\n" +
                " JOIN Usuarios u ON cu.id_usuario = u.id\n" +
                " JOIN Cursos c ON cu.id_curso = c.id\n" +
                " WHERE u.username = :nombreUsuario \n" +
                "   AND c.titulo = :nombreCurso \n" +
                "   AND cu.estado = 'completado';", nativeQuery = true)
        public List<String[]> ObtenerUrl(@Param("nombreUsuario") String nombreUsuario,
                                         @Param("nombreCurso") String nombreCurso);

        @Query(value ="SELECT u.username,\n" +
                "       SUM(CASE WHEN cu.estado = 'completado' THEN 1 ELSE 0 END) AS cursos_completados,\n" +
                "       SUM(CASE WHEN cu.estado = 'no completado' THEN 1 ELSE 0 END) AS cursos_no_completados\n" +
                "FROM usuarios u\n" +
                "JOIN cursos_usuarios cu ON u.id = cu.id_usuario\n" +
                "GROUP BY u.username;\n",nativeQuery = true)
        public List<String[]>cantidadDeCursosCompletadosYNoCompletados();
        }
