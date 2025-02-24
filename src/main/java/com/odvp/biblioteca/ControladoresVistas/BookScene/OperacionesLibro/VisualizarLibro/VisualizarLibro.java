package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.VisualizarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookOperationController;
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

public class VisualizarLibro implements IOperacion {
    public Libro libro;
    private final LibroDAO libroDAO = new LibroDAO();
    public VisualizarLibro(Integer idLibro){
        this.libro = libroDAO.visualizar(idLibro);
        buildWindow();
    }
    @Override
    public void buildWindow() {
        try{
            new VisualizarLibroVentana(libro);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
