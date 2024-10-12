package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.dtos.VideosDTO;
import com.zentech.si62_g5.entities.Videos;
import com.zentech.si62_g5.entities.VideosFavoritos;
import com.zentech.si62_g5.repositories.IVideosRepository;
import com.zentech.si62_g5.serviceinterfaces.IVideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideosServiceImplement implements IVideosService {
    @Autowired
    private IVideosRepository vR;


    @Override
    public void insert(Videos vid) {
        vR.save(vid);
    }

    @Override
    public List<Videos> list() {
        return vR.findAll();
    }

    @Override
    public void delete(int id) {
        vR.deleteById(id);
    }

    @Override
    public Videos listId(int id) {
        return vR.findById(id).orElse(new Videos());
    }

    @Override
    public void update(Videos vid) {
        vR.save(vid);
    }

    @Override
    public List<Videos> videostitulosesion(String titulo) {
        return vR.videostitulosesion(titulo);
    }


}
