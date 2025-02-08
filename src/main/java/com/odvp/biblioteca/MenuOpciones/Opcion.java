package com.odvp.biblioteca.MenuOpciones;

public class Opcion {
    private String imagePath;  // Ruta de la imagen
    private String title;      // Título de la opción (puedes agregar otros atributos)
    private String viewPath;

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    // Constructor
    public Opcion(String imagePath, String title, String viewPath) {
        this.imagePath = imagePath;
        this.title = title;
        this.viewPath = viewPath;
    }

    // Getters y Setters
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

