package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.repositories.ICursosRepository;
import com.zentech.si62_g5.repositories.ISesionesRepository;
import com.zentech.si62_g5.serviceinterfaces.ISesionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionesServiceImplement implements ISesionesService {

    @Autowired
    private ISesionesRepository sR;

    @Override
    public void insert(Sesiones ses) {
        sR.save(ses);
    }

    @Override
    public List<Sesiones> list() {
        return sR.findAll();
    }

    @Override
    public void delete(int id) {
        sR.deleteById(id);
    }

    @Override
    public void update(Sesiones ses) {
        sR.save(ses);
    }

    @Override
    public List<String[]> avgDuracionVideo() {
        return sR.promedioDuracionVideos();
    }
}
