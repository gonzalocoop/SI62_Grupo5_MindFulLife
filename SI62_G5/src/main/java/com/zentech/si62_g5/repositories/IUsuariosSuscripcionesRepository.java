package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IUsuariosSuscripcionesRepository extends JpaRepository<UsuariosSuscripciones, Integer> {

    @Query(value = "SELECT \n" +
            "    s.nombre AS suscripcion, \n" +
            "    u.username\n" +
            "FROM \n" +
            "    Usuarios_Suscripciones us\n" +
            "JOIN \n" +
            "    Suscripciones s ON us.id_suscripciones = s.id\n" +
            "JOIN \n" +
            "    Usuarios u ON us.id_usuarios = u.id\n" +
            "WHERE \n" +
            "    s.nombre = :nombresuscripcion\n" +
            "ORDER BY \n" +
            "    u.username;", nativeQuery = true)
    public List<String[]> usuariosSuscripcion(@Param("nombresuscripcion") String nombresuscripcion);

    @Query(value = "SELECT s.nombre AS nombreSuscripcion, SUM(s.precio) AS recaudacionTotal " +
            "FROM Usuarios_Suscripciones us " +
            "JOIN Suscripciones s ON us.id_suscripciones = s.id " +
            "WHERE us.fecha_inicio BETWEEN :fechaInicio AND :fechaFin " +
            "AND s.nombre = :nombreSuscripcion " +
            "GROUP BY s.nombre", nativeQuery = true)
    public List<Object[]> obtenerRecaudacionPorSuscripcion(
            @Param("nombreSuscripcion") String nombreSuscripcion,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

}
