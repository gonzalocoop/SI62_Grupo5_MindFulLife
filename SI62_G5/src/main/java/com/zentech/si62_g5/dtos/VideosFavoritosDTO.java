package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Usuarios;
import com.zentech.si62_g5.entities.Videos;

public class VideosFavoritosDTO {
    private int id;

    private Usuarios usu;

    private Videos vid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getUsu() {
        return usu;
    }

    public void setUsu(Usuarios usu) {
        this.usu = usu;
    }

    public Videos getVid() {
        return vid;
    }

    public void setVid(Videos vid) {
        this.vid = vid;
    }
}
