package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.UsuariosSuscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuariosSuscripcionesRepository extends JpaRepository<UsuariosSuscripciones, Integer> {
}
