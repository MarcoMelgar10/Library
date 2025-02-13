package com.odvp.biblioteca.FuncionesMaestros.MaestroAdministrador;

import com.odvp.biblioteca.postgresql.CRUD.AdministradorDAO;

public class Administrador {
    private String nombre;
    private String contrasena;
    public Administrador(String nombre, String contrasena){
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
