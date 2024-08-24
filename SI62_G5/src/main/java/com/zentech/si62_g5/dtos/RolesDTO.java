package com.zentech.si62_g5.dtos;

import jakarta.persistence.Column;

public class RolesDTO {
    private int Id;
    private String Nombre;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
