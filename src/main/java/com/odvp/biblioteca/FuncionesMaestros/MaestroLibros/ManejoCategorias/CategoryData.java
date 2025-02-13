package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias;

//representa los datos que se mostraran de cada categoria

public class CategoryData {
    private String nombre;
    private String descripcion;

    public CategoryData(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
