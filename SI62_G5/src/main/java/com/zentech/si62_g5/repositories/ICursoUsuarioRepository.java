package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Comentarios;
import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.entities.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


        // Verificar si ya existe el registro con consulta nativa
        @Query(value = "SELECT COUNT(*) > 0 FROM cursos_usuarios cu WHERE cu.id_usuario = :idUsuario AND cu.id_curso = :idCurso",
                nativeQuery = true)
        public boolean existeRegistro(@Param("idUsuario") int idUsuario, @Param("idCurso") int idCurso);


        // Método para insertar el registro
        @Modifying
        @Transactional
        @Query(value = "INSERT INTO cursos_usuarios (estado, id_curso, id_usuario, fecha_inicio, fecha_fin, progreso, url) " +
                " VALUES ('no completado', :idCurso, :idUsuario, CURRENT_DATE, " +
                " CURRENT_DATE + (SELECT duracion FROM cursos WHERE id = :idCurso) * INTERVAL '1 day', 0, " +
                " CASE " +
                "   WHEN (CURRENT_DATE + (SELECT duracion FROM cursos WHERE id = :idCurso) * INTERVAL '1 day' - CURRENT_DATE) < INTERVAL '14 days' THEN 'https://ibb.co/YDSYmqT' " +
                "   WHEN (CURRENT_DATE + (SELECT duracion FROM cursos WHERE id = :idCurso) * INTERVAL '1 day' - CURRENT_DATE) BETWEEN INTERVAL '14 days' AND INTERVAL '21 days' THEN 'https://ibb.co/ykQQbR4' " +
                "   ELSE 'https://ibb.co/5v5CzCY' " +
                " END)",
                nativeQuery = true)
        public void registrarCurso(@Param("idCurso") int idCurso, @Param("idUsuario") int idUsuario);


        //NUEVO
        @Query(value = "SELECT * FROM cursos_usuarios cu WHERE cu.id_curso = :idCurso AND cu.id_usuario = :idUsuario",
                nativeQuery = true)
        public CursosUsuarios findByCursoAndUsuario(@Param("idCurso") int idCurso, @Param("idUsuario") int idUsuario);

        @Modifying
        @Transactional
        @Query(value = "UPDATE cursos_usuarios " +
                " SET progreso = ( " +
                "    SELECT COUNT(*) * 100 / (SELECT COUNT(*) FROM cronogramas WHERE id_cursos_usuarios = :idCursoUsuario) " +
                "    FROM cronogramas " +
                "    WHERE id_cursos_usuarios = :idCursoUsuario AND estado = 'completado' " +
                "), " +
                " estado = CASE " +
                "    WHEN ( " +
                "        SELECT COUNT(*) * 100 / (SELECT COUNT(*) FROM cronogramas WHERE id_cursos_usuarios = :idCursoUsuario) " +
                "        FROM cronogramas " +
                "        WHERE id_cursos_usuarios = :idCursoUsuario AND estado = 'completado' " +
                "    ) = 100 THEN 'completado' " +
                "    ELSE estado " +
                " END " +
                " WHERE id = :idCursoUsuario",
                nativeQuery = true)
        public void actualizarProgresoYEstado(@Param("idCursoUsuario") int idCursoUsuario);


        @Query("SELECT c FROM CursosUsuarios c WHERE c.usua.username = :username")
        List<CursosUsuarios> findByUsername(String username);


        @Query(value = "SELECT \n" +
                "    estado,\n" +
                "    COUNT(*) AS cantidad\n" +
                " FROM \n" +
                "    cursos_usuarios\n" +
                " WHERE \n" +
                "    estado IN ('completado', 'no completado')\n" +
                " GROUP BY \n" +
                "    estado;",
                nativeQuery = true)
        public List<String[]> CantidadCursoCompletadosNoCompletadosGeneral();
}
