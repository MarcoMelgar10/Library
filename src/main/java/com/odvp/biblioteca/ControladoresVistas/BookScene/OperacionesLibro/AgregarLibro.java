package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookOperationController;
import com.odvp.biblioteca.ControladoresVistas.IOperacion;
import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.Objetos.Libro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
    crea la ventana para agregar un libro
 */

public class AgregarLibro implements IOperacion {
    private Libro libro;

    public AgregarLibro(){
        buildWindow();
    }

    @Override
    public void buildWindow() {
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/BookScene/book-operation.fxml"));
            Parent root =fxmlLoader.load();
            BookOperationController controller =fxmlLoader.getController();
            controller.initComponents(libro, BookOperationController.TYPE_ADD);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
