package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

@Entity
@Table
public class Roles {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;
    @Column(name="Nombre",nullable=false,length=20)
    private String Nombre;


    public Roles() {
    }

    public Roles(int id, String nombre) {
        Id = id;
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
