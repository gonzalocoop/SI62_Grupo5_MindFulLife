package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Cronogramas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICronogramasRepository extends JpaRepository<Cronogramas,Integer> {
    @Query("SELECT c FROM Cronogramas c WHERE c.curUsu.usua.username = :username")
    List<Cronogramas> findByUsername(String username);
}
