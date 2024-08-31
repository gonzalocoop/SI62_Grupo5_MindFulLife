package com.zentech.si62_g5.repositories;

import com.zentech.si62_g5.entities.Videos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVideosRepository extends JpaRepository<Videos,Integer> {
}