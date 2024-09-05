package com.zentech.si62_g5.serviceinterfaces;

import com.zentech.si62_g5.entities.Sesiones;


import java.util.List;

public interface ISesionesService {
    public void insert(Sesiones ses);
    public List<Sesiones> list();
    public void delete(int id);
    public void update(Sesiones ses);



    public List<String[]> avgDuracionVideo();
}
