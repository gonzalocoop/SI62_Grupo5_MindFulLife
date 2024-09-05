package com.zentech.si62_g5.dtos;

import java.math.BigDecimal;

public class PromedioVideosDTO {
    private int idSesion;
    private String tituloSesion;
    private BigDecimal duracionPromedio;

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public String getTituloSesion() {
        return tituloSesion;
    }

    public void setTituloSesion(String tituloSesion) {
        this.tituloSesion = tituloSesion;
    }

    public BigDecimal getDuracionPromedio() {
        return duracionPromedio;
    }

    public void setDuracionPromedio(BigDecimal duracionPromedio) {
        this.duracionPromedio = duracionPromedio;
    }
}
