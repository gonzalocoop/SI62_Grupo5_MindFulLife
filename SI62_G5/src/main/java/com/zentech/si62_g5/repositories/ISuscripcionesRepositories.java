package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Suscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISuscripcionesRepositories extends JpaRepository<Suscripciones,Integer> {
}
