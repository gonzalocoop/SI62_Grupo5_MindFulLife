package com.zentech.si62_g5.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Sesiones")
public class Sesiones {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="titulo",nullable=false,length=25)
    private String titulo;

    @Column(name="descripcion",nullable=false, columnDefinition="text")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name= "idCursos") //esto es el nombre con el que saldra en esta tabla
    private Cursos cur;

    public Sesiones() {
    }

    public Sesiones(int id, String titulo, String descripcion, Cursos cur) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cur = cur;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cursos getCur() {
        return cur;
    }

    public void setCur(Cursos cur) {
        this.cur = cur;
    }
}
