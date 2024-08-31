package com.zentech.si62_g5.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="Comentarios")
public class Comentarios{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="comentario",nullable=false, columnDefinition="text") 
    private String comentario;
    @Column(name="fecha", nullable=false)
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name= "idUsuarios")
    private Usuarios usua;
    @ManyToOne
    @JoinColumn(name= "idSesiones")
    private Sesiones ses;

    public Comentarios() {

    }

    public Comentarios(int id, String comentario, LocalDate fecha, Sesiones ses, Usuarios usua) {
        this.id = id;
        this.comentario = comentario;
        this.fecha = fecha;
        this.ses = ses;
        this.usua = usua;
    }

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