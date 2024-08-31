package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Videos;

import java.util.List;

public interface IVideosService {
    public void insert(Videos vid);
    public List<Videos> list();
    public void delete(int id);
    public void update(Videos vid);
}
