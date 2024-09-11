package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.repositories.ICronogramasRepository;

import com.zentech.si62_g5.serviceinterfaces.ICronogramasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronogramasServiceImplement implements ICronogramasService {
    @Autowired
    private ICronogramasRepository cR;


    @Override
    public void insert(Cronogramas cron) {
        cR.save(cron);
    }

    @Override
    public List<Cronogramas> list() {
        return cR.findAll();
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public void update(Cronogramas cron) {
        cR.save(cron);
    }

    @Override
    public List<Cronogramas> findByUsername(String username) {
        return cR.findByUsername(username);
    }

}
