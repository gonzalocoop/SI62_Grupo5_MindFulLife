package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.Roles;

import java.util.List;

public interface IRolesService {

    public List<Roles> list();
    public void delete(int id);
    public void update(Roles rol);
}
