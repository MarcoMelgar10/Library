package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EliminarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookDeleteController;
import com.odvp.biblioteca.ControladoresVistas.IOperacion;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
    crea la ventana de eliminar libro
 */

public class EliminarLibro implements IOperacion {
    int libroId;
    public EliminarLibro(int libroId){
        this.libroId = libroId;
        buildWindow();
    }

    @Override
    public void buildWindow() {
        try{
            new EliminarLibroVentana(libroId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
