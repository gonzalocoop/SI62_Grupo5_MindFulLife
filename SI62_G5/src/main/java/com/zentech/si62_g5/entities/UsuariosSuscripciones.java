package com.zentech.si62_g5.entities;

import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "UsuariosSuscripciones")
public class UsuariosSuscripciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idSuscripciones")
    private Suscripciones sus;

    @ManyToOne
    @JoinColumn(name = "idUsuarios")
    private Usuarios usu;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFin", nullable = false)
    private LocalDate fechaFin;

    public UsuariosSuscripciones() {
    }

    public UsuariosSuscripciones(int id, Suscripciones sus, Usuarios usu, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.sus = sus;
        this.usu = usu;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

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
