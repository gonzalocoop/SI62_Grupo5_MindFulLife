package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.CursosUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICursosRepository extends JpaRepository<Cursos,Integer> {


    @Query("Select c from Cursos c where c.titulo like %:titulo%")
    public List<Cursos> buscar(@Param("titulo") String titulo);

    @Query(value ="SELECT c.titulo , count(s.id_cursos) \n" +
            " from cursos c \n" +
            " join sesiones s\n" +
            " on c.id = s.id_cursos\n" +
            " group by c.titulo",nativeQuery = true)
    public List<String[]>cantidadServicioCurso();

    @Query(value = "WITH cursos_agrupados AS (\n" +
            "    SELECT cu.id_curso, c.titulo, COUNT(cu.id_curso) AS num_usuarios\n" +
            "    FROM cursos c\n" +
            "    JOIN cursos_usuarios cu ON cu.id_curso = c.id\n" +
            "    GROUP BY cu.id_curso, c.titulo\n" +
            "),\n" +
            "max_min AS (\n" +
            "    SELECT *,\n" +
            "           CASE \n" +
            "               WHEN num_usuarios = (SELECT MAX(num_usuarios) FROM cursos_agrupados) THEN 'Mayor'\n" +
            "               WHEN num_usuarios = (SELECT MIN(num_usuarios) FROM cursos_agrupados) THEN 'Menor'\n" +
            "           END AS categoria\n" +
            "    FROM cursos_agrupados\n" +
            "),\n" +
            "conteo_categorias AS (\n" +
            "    SELECT categoria, COUNT(*) AS cantidad\n" +
            "    FROM max_min\n" +
            "    WHERE categoria IS NOT NULL\n" +
            "    GROUP BY categoria\n" +
            "),\n" +
            "categorias_completas AS (\n" +
            "    SELECT id_curso, titulo, num_usuarios,\n" +
            "           CASE \n" +
            "               WHEN categoria = 'Mayor' AND (SELECT cantidad FROM conteo_categorias WHERE categoria = 'Mayor') > 1 THEN 'Hay más de un mayor'\n" +
            "               WHEN categoria = 'Menor' AND (SELECT cantidad FROM conteo_categorias WHERE categoria = 'Menor') > 1 THEN 'Hay más de un menor'\n" +
            "               ELSE categoria\n" +
            "           END AS categoria\n" +
            "    FROM max_min\n" +
            "    WHERE categoria IS NOT NULL\n" +
            "\n" +
            "    UNION ALL\n" +
            "\n" +
            "    SELECT 0 AS id_curso, ' ' AS titulo, 0 AS num_usuarios, 'No existe un menor' AS categoria\n" +
            "    WHERE NOT EXISTS (SELECT 1 FROM conteo_categorias WHERE categoria = 'Menor')\n" +
            ")\n" +
            "SELECT * \n" +
            "FROM categorias_completas\n" +
            "ORDER BY categoria, num_usuarios DESC;", nativeQuery = true)
    public List<String[]> MaxyMinUsuarioCursos();


}

