package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios,Integer> {


    public Usuarios findOneByUsername(String username);





    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuarios SET password = :nuevaContra WHERE username = :usuario", nativeQuery = true)
    public void cambiarContrasena(@Param("usuario") String usuario, @Param("nuevaContra") String nuevaContra);

    @Query(value = "SELECT \n" +
            "    Usuarios.username,\n" +
            "    Comentarios.comentario\n" +
            "FROM \n" +
            "    Comentarios\n" +
            "JOIN \n" +
            "    Usuarios ON Comentarios.id_usuarios = Usuarios.id\n" +
            "WHERE \n" +
            "    Usuarios.username = :usuario;", nativeQuery = true)
    public List<String[]> comentarioUsuario(@Param("usuario") String usuario);


    @Query(value = "SELECT * FROM Usuarios WHERE Usuarios.username = :username", nativeQuery = true)
    public Usuarios findUsuarioByUsername(@Param("username") String username);
}
