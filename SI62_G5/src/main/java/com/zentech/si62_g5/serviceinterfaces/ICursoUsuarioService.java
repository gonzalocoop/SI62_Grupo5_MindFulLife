package com.zentech.si62_g5.serviceinterfaces;


import com.zentech.si62_g5.entities.CursosUsuarios;

import java.util.List;

public interface ICursoUsuarioService {
    public void insert(CursosUsuarios cus);
    public List<CursosUsuarios> list();
    public void delete(int id);
    public void update(CursosUsuarios cus);
}