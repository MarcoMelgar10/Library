package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.VisualizarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;

/*
    crea la vista para visualizar libro
 */

public class VisualizarLibro {
    private final LibroDAO libroDAO = new LibroDAO();
    public VisualizarLibro(ModeloLibros modelo){
        Libro libro = libroDAO.obtener(modelo.getLibroSeleccionado().getID());
        VisualizarLibroVentana visualizarLibro = new VisualizarLibroVentana(libro);
    }
}
