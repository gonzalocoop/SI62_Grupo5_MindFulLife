package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
