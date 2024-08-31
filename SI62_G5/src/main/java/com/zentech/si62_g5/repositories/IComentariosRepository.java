package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComentariosRepository extends JpaRepository<Comentarios,Integer> {
}