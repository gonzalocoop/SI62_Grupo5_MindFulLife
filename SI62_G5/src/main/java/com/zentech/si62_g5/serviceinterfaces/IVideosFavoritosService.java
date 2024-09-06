package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.VideosFavoritos;

import java.util.List;

public interface IVideosFavoritosService {
    public void insert(VideosFavoritos vfav);
    public List<VideosFavoritos> list();
    public void delete(int id);
    public void update(VideosFavoritos vfav);
}
