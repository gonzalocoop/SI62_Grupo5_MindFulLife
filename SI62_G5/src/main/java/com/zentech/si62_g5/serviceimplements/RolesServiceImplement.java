package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.Roles;
import com.zentech.si62_g5.repositories.IRolesRepository;
import com.zentech.si62_g5.serviceinterfaces.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImplement implements IRolesService {

    @Autowired
    private IRolesRepository rR;

    @Override
    public void insert(Roles rol) {
        rR.save(rol);
    }

    @Override
    public List<Roles> list() {
        return rR.findAll();
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public void update(Roles rol) {
        rR.save(rol);
    }

    @Override
    public Roles listId(int id) {
        return rR.findById(id).orElse(new Roles());
    }

}
