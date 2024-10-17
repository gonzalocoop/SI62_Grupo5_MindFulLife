package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Sesiones;

import java.math.BigDecimal;

import java.time.LocalDate;

public class VideosDTO {

    private int id;

    private String titulo;

    private LocalDate fechaAgregado;

    private BigDecimal duracion;

    private String url;

    private Sesiones ses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(LocalDate fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    public BigDecimal getDuracion() {
        return duracion;
    }

    public void setDuracion(BigDecimal duracion) {
        this.duracion = duracion;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Sesiones getSes() {
        return ses;
    }

    public void setSes(Sesiones ses) {
        this.ses = ses;
    }


}