package com.zentech.si62_g5.serviceimplements;

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
    public List<String[]> inicioDeSesision(String usuario, String contra) {
        return uR.inicioSesion(usuario,contra);
    }

    @Override
    public void cambioPassword(String usuario, String nuevaContra) {
        uR.cambiarContrasena(usuario,nuevaContra);
    }
}
