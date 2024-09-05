package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUsuariosRepository extends JpaRepository<Usuarios,Integer> {





    @Query(value = "SELECT " +
            "username, " +
            "password, " +
            "CASE " +
            "    WHEN username IS NOT NULL THEN 'valido' " +
            "    ELSE 'invalido' " +
            "END AS status " +
            "FROM ( " +
            "    SELECT username, password " +
            "    FROM Usuarios " +
            "    WHERE username = :usuario AND password = :contra " +
            "    LIMIT 1 " +
            ") AS resultado " +
            "RIGHT JOIN (SELECT 1) AS dummy ON 1 = 1;",
            nativeQuery = true)
    public List<String[]> inicioSesion(@Param("usuario") String usuario, @Param("contra") String contra);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuarios SET password = :nuevaContra WHERE username = :usuario", nativeQuery = true)
    public void cambiarContrasena(@Param("usuario") String usuario, @Param("nuevaContra") String nuevaContra);
}
