package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table
public class TipoSuscripciones {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="tipo",nullable=false,length=20)
    private String tipo;
    @Column(name="precio", nullable=false, precision=5, scale=2)
    private BigDecimal precio;

    public TipoSuscripciones() {
    }

    public TipoSuscripciones(int id, String tipo, BigDecimal precio) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
