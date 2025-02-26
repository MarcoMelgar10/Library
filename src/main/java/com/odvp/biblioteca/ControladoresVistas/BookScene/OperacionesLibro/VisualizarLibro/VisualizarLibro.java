package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.VisualizarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookOperationController;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.IOperacion;
import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
    crea la vista para visualizar libro
 */

public class VisualizarLibro {
    private final LibroDAO libroDAO = new LibroDAO();
    public VisualizarLibro(ModeloLibros modelo){
        Libro libro = libroDAO.visualizar(modelo.getLibroSeleccionado().getID());
        VisualizarLibroVentana visualizarLibro = new VisualizarLibroVentana(libro);
    }
}
