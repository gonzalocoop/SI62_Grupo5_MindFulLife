package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICursosRepository extends JpaRepository<Cursos,Integer> {


    @Query("Select c from Cursos c where c.titulo like %:titulo%")
    public List<Cursos> buscar(@Param("titulo") String titulo);
}
