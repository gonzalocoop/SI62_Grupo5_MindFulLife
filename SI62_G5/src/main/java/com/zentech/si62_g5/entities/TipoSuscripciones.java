package com.zentech.si62_g5.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table
public class TipoSuscripciones {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;
    @Column(name="Tipo",nullable=false,length=20)
    private String Tipo;
    @Column(name="Precio", nullable=false, precision=5, scale=2)
    private BigDecimal Precio;

    public TipoSuscripciones() {
    }

    public TipoSuscripciones(int id, String tipo, BigDecimal precio) {
        Id = id;
        Tipo = tipo;
        Precio = precio;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }
}
