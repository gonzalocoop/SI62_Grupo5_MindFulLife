package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISesionesRepository extends JpaRepository<Sesiones,Integer> {
    @Query(value = " SELECT s.titulo AS SesionTitulo, v.titulo AS VideoTitulo, v.duracion AS VideoDuracion\n" +
            " FROM Videos v\n" +
            " JOIN Sesiones s\n" +
            " ON v.id_sesiones = v.id_sesiones\n" +
            " GROUP BY s.titulo, v.titulo, v.duracion;\n",nativeQuery = true)
    public List<String[]> SesionCantidadVideo();

    @Query(value = " SELECT \n" +
            "    s.id AS sesion_id,\n" +
            "    s.titulo AS sesion_titulo,\n" +
            "    AVG(v.duracion) AS promedio_duracion_videos\n" +
            " FROM \n" +
            "    Sesiones s\n" +
            " JOIN \n" +
            "    Videos v ON s.id = v.id_sesiones\n" +
            " GROUP BY \n" +
            "    s.id, s.titulo;",nativeQuery = true)
    public List<String[]> promedioDuracionVideos();


}
