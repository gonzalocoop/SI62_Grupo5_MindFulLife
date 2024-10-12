package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Cronogramas;
import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.Roles;

import javax.management.relation.Role;
import java.util.List;

public interface IRolesService {

    public void insert(Roles rol);
    public List<Roles> list();
    public void delete(int id);
    public void update(Roles rol);
    public Roles listId(int id);
}
