package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Cursos;
import jakarta.persistence.*;

public class SesionesDTO {

    private int id;

    private String titulo;


    private String descripcion;

    private Cursos cur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Cursos getCur() {
        return cur;
    }

    public void setCur(Cursos cur) {
        this.cur = cur;
    }
}
