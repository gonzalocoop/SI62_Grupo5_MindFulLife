package com.zentech.si62_g5.entities;
import jakarta.persistence.Id;

import jakarta.persistence.*;
@Entity
@Table(name = "VideosFavoritos")
public class VideosFavoritos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUsuarios")
    private Usuarios usu;

    @ManyToOne
    @JoinColumn(name = "idVideos")
    private  Videos vid;

    public VideosFavoritos() {
    }

    public VideosFavoritos(int id, Usuarios usu, Videos vid) {
        this.id = id;
        this.usu = usu;
        this.vid = vid;
    }

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
