package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Suscripciones;
import com.zentech.si62_g5.entities.Usuarios;

import java.time.LocalDate;

public class UsuariosSuscripcionesDTO {

    private int id;

    private Suscripciones sus;

    private Usuarios usu;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Suscripciones getSus() {
        return sus;
    }

    public void setSus(Suscripciones sus) {
        this.sus = sus;
    }

    public Usuarios getUsu() {
        return usu;
    }

    public void setUsu(Usuarios usu) {
        this.usu = usu;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
