package com.zentech.si62_g5.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username", nullable=false, length=20)
    private String username;
    @Column(name="password", nullable=false, length=25)
    private String password;
    @Column(name="email", nullable=false, length=40)
    private String email;
    @ManyToOne
    @JoinColumn(name= "idRoles") //esto es el nombre con el que saldra en esta tabla
    private Roles rol;


    public Usuarios() {
    }

    public Usuarios(int id, String username, String password, String email, Roles rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
}
