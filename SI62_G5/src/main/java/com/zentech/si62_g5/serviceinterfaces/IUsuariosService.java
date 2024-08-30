package com.zentech.si62_g5.serviceinterfaces;


import com.zentech.si62_g5.entities.Usuarios;

import java.util.List;

public interface IUsuariosService {
    public void insert(Usuarios usuar);
    public List<Usuarios> list();
    public void delete(int id);
    public void update(Usuarios usuar);
}
