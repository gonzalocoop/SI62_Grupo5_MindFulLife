package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.VideosFavoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideosFavoritosRepository extends JpaRepository<VideosFavoritos,Integer> {
}
