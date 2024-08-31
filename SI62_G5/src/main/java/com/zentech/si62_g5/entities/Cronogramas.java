package com.zentech.si62_g5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Cronogramas")
public class Cronogramas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="detalle", nullable=false, columnDefinition="text")
    private String detalle;
    @Column(name="fechaLimite", nullable=false)
    private LocalDate fechaLimite;
    @Column(name="estado", nullable=false, length=20)
    private String estado;
    @ManyToOne
    @JoinColumn(name= "idCursos")
    private Cursos cur;
    @ManyToOne
    @JoinColumn(name= "idUsuarios")
    private Usuarios usua;
    @ManyToOne
    @JoinColumn(name= "idSesiones")
    private Sesiones ses;

    public Cronogramas() {
    }

    public Cronogramas(int id, String detalle, LocalDate fechaLimite, String estado, Cursos cur, Usuarios usua, Sesiones ses) {
        this.id = id;
        this.detalle = detalle;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.cur = cur;
        this.usua = usua;
        this.ses = ses;
    }

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
