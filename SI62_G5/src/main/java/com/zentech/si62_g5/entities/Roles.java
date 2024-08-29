package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

@Entity
@Table
public class Roles {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="nombre",nullable=false,length=20)
    private String nombre;


    public Roles() {
    }

    public Roles(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
