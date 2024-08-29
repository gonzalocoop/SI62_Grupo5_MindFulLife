package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

@Entity
@Table (name="Cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="titulo",nullable=false,length=35)
    private String titulo;

    @Column(name="descripcion",nullable=false, columnDefinition="text") //Para que sea texto, osea muy largo
    private String descripcion;
    @Column(name="duracion",nullable=false)
    private int duracion;


    public Cursos() {
    }

    public Cursos(int id, int duracion, String descripcion, String titulo) {
        this.id = id;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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
}
