package com.odvp.biblioteca.FuncionesBarraOpciones;

/*
    calse Opcion, se utiliza para representar una opcion con los atributos:
    imagePath (es la ruta de la imagen de la opcion)
    title (titulo que se vera debajo de la imagen de opcion)
    viewPath (es la direccion del .fxml que se cargará al seleccionar esa opcion)
* */

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

