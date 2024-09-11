package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.dtos.VideosDTO;
import com.zentech.si62_g5.entities.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideosRepository extends JpaRepository<Videos,Integer> {

    @Query("SELECT v from Videos v join Sesiones se on v.ses.id=se.id where se.titulo like %:titulo%")
    public List<Videos> videostitulosesion(@Param("titulo") String titulo);
}