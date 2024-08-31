package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name="Suscripciones")
public class Suscripciones {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="nombre",nullable=false,length=20)
    private String nombre;
    @Column(name="precio", nullable=false, precision=5, scale=2)
    private BigDecimal precio;

    public Suscripciones() {
    }

    public Suscripciones(int id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
