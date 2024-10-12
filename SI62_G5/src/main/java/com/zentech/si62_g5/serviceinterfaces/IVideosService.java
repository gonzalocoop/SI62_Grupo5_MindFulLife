package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.dtos.VideosDTO;
import com.zentech.si62_g5.entities.Videos;
import com.zentech.si62_g5.entities.VideosFavoritos;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVideosService {
    public void insert(Videos vid);
    public List<Videos> list();
    public void delete(int id);
    public Videos listId(int id);
    public void update(Videos vid);
    public List<Videos> videostitulosesion(String titulo);

}
