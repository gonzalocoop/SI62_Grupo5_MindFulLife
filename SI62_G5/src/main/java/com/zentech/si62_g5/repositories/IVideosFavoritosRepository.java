package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.entities.VideosFavoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideosFavoritosRepository extends JpaRepository<VideosFavoritos,Integer> {

    @Query("select vf from VideosFavoritos vf where vf.usu.username=:username")
    public List<VideosFavoritos> listaUsuarioCursoFav(@Param("username")String username);



}
