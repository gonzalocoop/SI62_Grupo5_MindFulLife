package com.zentech.si62_g5.entities;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Videos")
public class Videos{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="titulo",nullable=false,length=35)
    private String titulo;
    @Column(name="fechaAgregado", nullable=false)
    private LocalDate fechaAgregado;
    @Column(name="duracion", nullable=false, precision=5, scale=2)
    private BigDecimal duracion;
    @Column(name="url",nullable=false)
    private String url;
    @ManyToOne
    @JoinColumn(name= "idSesiones")
    private Sesiones ses;

    public Videos() {

    }

    public Videos(int id, String titulo, LocalDate fechaAgregado, BigDecimal duracion, String url, Sesiones ses) {
        this.id = id;
        this.titulo = titulo;
        this.fechaAgregado = fechaAgregado;
        this.duracion = duracion;
        this.url = url;
        this.ses = ses;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Sesiones getSes() {
        return ses;
    }

    public void setSes(Sesiones ses) {
        this.ses = ses;
    }
}