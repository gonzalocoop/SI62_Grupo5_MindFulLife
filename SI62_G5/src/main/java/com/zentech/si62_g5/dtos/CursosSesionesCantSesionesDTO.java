package com.zentech.si62_g5.dtos;

public class CursosSesionesCantSesionesDTO {

    private String curso;
    private int cantidadSesiones;
    private int duracionCurso;
    private String categoria;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public int getDuracionCurso() {
        return duracionCurso;
    }

    public void setDuracionCurso(int duracionCurso) {
        this.duracionCurso = duracionCurso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
