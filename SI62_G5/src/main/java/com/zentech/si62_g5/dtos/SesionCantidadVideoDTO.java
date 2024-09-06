package com.zentech.si62_g5.dtos;

import java.math.BigDecimal;

public class SesionCantidadVideoDTO {
    private String tituloSesion;
    private String tituloVideo;
    private BigDecimal duracionVideo;

    public String getTituloSesion() {
        return tituloSesion;
    }

    public void setTituloSesion(String tituloSesion) {
        this.tituloSesion = tituloSesion;
    }

    public String getTituloVideo() {
        return tituloVideo;
    }

    public void setTituloVideo(String tituloVideo) {
        this.tituloVideo = tituloVideo;
    }

    public BigDecimal getDuracionVideo() {
        return duracionVideo;
    }

    public void setDuracionVideo(BigDecimal duracionVideo) {
        this.duracionVideo = duracionVideo;
    }
}
