package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.Sesiones;
import com.zentech.si62_g5.entities.Usuarios;
import jakarta.persistence.*;

import java.time.LocalDate;

public class CronogramasDTO {


    private int id;

    private String detalle;

    private LocalDate fechaLimite;

    private String estado;

    private Cursos cur;

    private Usuarios usua;

    private Sesiones ses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cursos getCur() {
        return cur;
    }

    public void setCur(Cursos cur) {
        this.cur = cur;
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
