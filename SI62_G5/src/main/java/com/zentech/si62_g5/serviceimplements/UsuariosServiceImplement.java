package com.zentech.si62_g5.serviceimplements;

import com.zentech.si62_g5.entities.Suscripciones;
import com.zentech.si62_g5.entities.Usuarios;
import com.zentech.si62_g5.repositories.ICursosRepository;
import com.zentech.si62_g5.repositories.IUsuariosRepository;
import com.zentech.si62_g5.serviceinterfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImplement implements IUsuariosService {
    @Autowired
    private IUsuariosRepository uR;


    @Override
    public void insert(Usuarios usuar) {
        uR.save(usuar);
    }

    @Override
    public List<Usuarios> list() {
        return uR.findAll();
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

    @Override
    public void update(Usuarios usuar) {
        uR.save(usuar);
    }

    @Override
    public Usuarios listId(int id) {
        return uR.findById(id).orElse(new Usuarios());
    }

    @Override
    public void cambioPassword(String usuario, String nuevaContra) {
        uR.cambiarContrasena(usuario,nuevaContra);
    }

    @Override
    public List<String[]> comentarioUsuario(String usuario) {
        return uR.comentarioUsuario(usuario);
    }

    @Override
    public Usuarios findUsuarioByUsername(String username) {
        return uR.findUsuarioByUsername(username);
    }
}
