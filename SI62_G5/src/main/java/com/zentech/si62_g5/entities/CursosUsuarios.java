package com.zentech.si62_g5.entities;

import com.fasterxml.jackson.databind.util.ClassUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "CursosUsuarios")
public class CursosUsuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "progreso", nullable = false, precision = 5, scale = 2)
    private BigDecimal progreso;
    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;
    @Column(name = "fechaFin", nullable = false)
    private LocalDate fechaFin;
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @Column(name="url", nullable=false, columnDefinition="text")
    private String url;
    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Cursos cur;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuarios usua;

    public CursosUsuarios() {
    }

    public CursosUsuarios(int id, Usuarios usua, Cursos cur, String url, String estado, LocalDate fechaFin, LocalDate fechaInicio, BigDecimal progreso) {
        this.id = id;
        this.usua = usua;
        this.cur = cur;
        this.url = url;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.progreso = progreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getProgreso() {
        return progreso;
    }

    public void setProgreso(BigDecimal progreso) {
        this.progreso = progreso;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}