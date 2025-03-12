package com.odvp.biblioteca.objetos;

public class Administrador {
    private int id;
    private String nombre;
    private String contrasena;
    public Administrador(int id,String nombre, String contrasena){
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
