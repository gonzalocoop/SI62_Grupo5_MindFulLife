package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.CursosUsuarios;
import com.zentech.si62_g5.repositories.ICursoUsuarioRepository;
import com.zentech.si62_g5.serviceinterfaces.ICursoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoUsuarioServiceImplement implements ICursoUsuarioService {

    @Autowired
    private ICursoUsuarioRepository cuR;

    @Override
    public void insert(CursosUsuarios cus) {
        cuR.save(cus);

    }

    @Override
    public List<CursosUsuarios> list() {
        return cuR.findAll();
    }

    @Override
    public void delete(int id) {
        cuR.deleteById(id);
    }

    @Override
    public void update(CursosUsuarios cus) {
        cuR.save(cus);
    }
}
