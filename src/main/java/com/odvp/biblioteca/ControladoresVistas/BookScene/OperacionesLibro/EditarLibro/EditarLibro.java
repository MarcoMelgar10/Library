package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EditarLibro;

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
    crea la ventana para editar un libro
 */

public class EditarLibro implements IOperacion {
    private Libro libro;
    private final LibroDAO libroDAO = new LibroDAO();
    public EditarLibro(Integer libroID){
        this.libro = libroDAO.visualizar(libroID);
        buildWindow();
    }


    @Override
    public void buildWindow() {
        try{
            new EditarLibroVentana(libro);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
