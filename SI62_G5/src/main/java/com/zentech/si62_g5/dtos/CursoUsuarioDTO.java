package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Cursos;
import com.zentech.si62_g5.entities.Usuarios;


import java.math.BigDecimal;
import java.time.LocalDate;

public class CursoUsuarioDTO {
    private int id;
    private BigDecimal progreso;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private String url;
    private Cursos cur;
    private Usuarios usua;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuarios getUsua() {
        return usua;
    }

    public void setUsua(Usuarios usua) {
        this.usua = usua;
    }

    public Cursos getCur() {
        return cur;
    }

    public void setCur(Cursos cur) {
        this.cur = cur;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getProgreso() {
        return progreso;
    }

    public void setProgreso(BigDecimal progreso) {
        this.progreso = progreso;
    }
}
