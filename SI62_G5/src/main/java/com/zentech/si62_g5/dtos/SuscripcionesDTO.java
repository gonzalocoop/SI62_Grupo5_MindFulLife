package com.zentech.si62_g5.dtos;

import java.math.BigDecimal;

public class SuscripcionesDTO {


    private int id;
    private String nombre;
    private BigDecimal precio;

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
