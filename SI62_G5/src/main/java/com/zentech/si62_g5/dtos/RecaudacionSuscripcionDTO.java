package com.zentech.si62_g5.dtos;

import java.math.BigDecimal;

public class RecaudacionSuscripcionDTO {
    private String nombreSuscripcion;
    private BigDecimal recaudacionTotal;

    public String getNombreSuscripcion() {
        return nombreSuscripcion;
    }

    public void setNombreSuscripcion(String nombreSuscripcion) {
        this.nombreSuscripcion = nombreSuscripcion;
    }

    public BigDecimal getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(BigDecimal recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }
}
