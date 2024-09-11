package com.zentech.si62_g5.dtos;

import java.math.BigDecimal;

public class SesionTituloComentarioDTO {
    private String tituloSesion;
    private String comentario;

    // Getters y Setters
    public String getTituloSesion() {
        return tituloSesion;
    }

    public void setTituloSesion(String tituloSesion) {this.tituloSesion = tituloSesion;}

    public String getComentario() {return comentario;}

    public void setComentario(String comentario) {this.comentario = comentario;}

}
