package com.zentech.si62_g5.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class TipoSuscripcionesDTO {


    private int Id;
    private String Tipo;
    private BigDecimal Precio;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
