package com.zentech.si62_g5.dtos;

public class CantSesionesCursoDTO {
    private String titulo;
    private int quatitySesion;

    public int getQuatitySesion() {
        return quatitySesion;
    }

    public void setQuatitySesion(int quatitySesion) {
        this.quatitySesion = quatitySesion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
