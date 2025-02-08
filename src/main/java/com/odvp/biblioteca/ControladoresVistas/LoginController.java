package com.odvp.biblioteca.ControladoresVistas;

import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
    Controlador de la vista de login
 */

public class LoginController  implements IVista{
    @FXML
    public VBox loginContainer;
    @FXML
    private Label titleLabel;
    @FXML
    protected void onAceptarButtonClick() {
        cambiarAMainScene();
    }

    @Override
    public Parent getContainer() {
        return loginContainer;
    }

    public void cambiarAMainScene(){
        try{
            Stage stage = (Stage) loginContainer.getScene().getWindow();
            if(stage == null) return;
            Parent root = FXMLLoader.load(LibraryApplication.class.getResource("Vistas/main-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
            System.out.println("No se pudo cargar de escena");
        }
    }
}