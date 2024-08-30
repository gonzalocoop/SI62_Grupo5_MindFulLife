package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuariosRepository extends JpaRepository<Usuarios,Integer> {
}
