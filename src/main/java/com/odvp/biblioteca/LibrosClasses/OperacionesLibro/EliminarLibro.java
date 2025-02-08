package com.odvp.biblioteca.LibrosClasses.OperacionesLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookDeleteController;
import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.LibrosClasses.Libro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EliminarLibro implements IOperacionLibro{
    int libroId;
    public EliminarLibro(int libroId){
        this.libroId = libroId;
        buildWindow();
    }

    @Override
    public void buildWindow() {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("BookScene/book-delete.fxml"));
            Parent root = loader.load();
            BookDeleteController controller = loader.getController();
            controller.initComponents(libroId);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Libro getLibroData() {
        return null;
    }
}
