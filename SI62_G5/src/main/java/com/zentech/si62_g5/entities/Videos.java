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
    @ManyToOne
    @JoinColumn(name= "idSesiones")
    private Sesiones ses;

    public Videos() {

    }

    public Videos(int id, BigDecimal duracion, String titulo, LocalDate fechaAgregado, Sesiones ses) {
        this.id = id;
        this.duracion = duracion;
        this.titulo = titulo;
        this.fechaAgregado = fechaAgregado;
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

    public Sesiones getSes() {
        return ses;
    }

    public void setSes(Sesiones ses) {
        this.ses = ses;
    }


}