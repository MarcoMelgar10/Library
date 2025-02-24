package com.odvp.biblioteca.Objetos;
/*
Clase objeto del autor.
 */
public class Autor {
    private int ID;
    private String nombre;
    private String descripcion;
    public Autor(int ID, String nombre, String descripcion){
        this.ID = ID;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
