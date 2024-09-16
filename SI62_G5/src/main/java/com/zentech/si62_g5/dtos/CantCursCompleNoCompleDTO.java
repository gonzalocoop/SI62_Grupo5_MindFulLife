package com.zentech.si62_g5.dtos;

import com.zentech.si62_g5.entities.Usuarios;

public class CantCursCompleNoCompleDTO {

    private String username;
    private int cursosCompletados;
    private int cursosNoCompletados;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCursosCompletados() {
        return cursosCompletados;
    }

    public void setCursosCompletados(int cursosCompletados) {
        this.cursosCompletados = cursosCompletados;
    }

    public int getCursosNoCompletados() {
        return cursosNoCompletados;
    }

    public void setCursosNoCompletados(int cursosNoCompletados) {
        this.cursosNoCompletados = cursosNoCompletados;
    }
}
