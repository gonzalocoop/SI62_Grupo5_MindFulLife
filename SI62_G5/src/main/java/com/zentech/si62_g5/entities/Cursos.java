package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

@Entity
@Table (name="Cursos")
public class Cursos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
    @Column(name="Titulo",nullable=false,length=35)
    private String Titulo;

    @Column(name="Descripcion",nullable=false, columnDefinition="text") //Para que sea texto, osea muy largo
    private String Descripcion;
    @Column(name="Duracion",nullable=false)
    private int Duracion;


    public Cursos() {
    }

    public Cursos(int id, int duracion, String descripcion, String titulo) {
        Id = id;
        Duracion = duracion;
        Descripcion = descripcion;
        Titulo = titulo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getDuracion() {
        return Duracion;
    }

    public void setDuracion(int duracion) {
        Duracion = duracion;
    }
}
