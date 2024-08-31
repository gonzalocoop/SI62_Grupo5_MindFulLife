package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.entities.Usuarios;

import java.time.LocalDate;

public class ComentariosDTO {
    
    private int id;

    private String comentario;

    private LocalDate fecha;

    private Usuarios usua;

    private Sesiones ses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuarios getUsua() {
        return usua;
    }

    public void setUsua(Usuarios usua) {
        this.usua = usua;
    }

    public Sesiones getSes() {
        return ses;
    }

    public void setSes(Sesiones ses) {
        this.ses = ses;
    }


}