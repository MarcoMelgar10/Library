package com.odvp.biblioteca.ControladoresVistas.LoginScene;

import com.odvp.biblioteca.ControladoresVistas.MainEscena;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
    Controlador de la vista de login
 */

public class LoginController{
    @FXML
    public VBox loginContainer;
    @FXML
    private Label titleLabel;
    @FXML
    protected void onAceptarButtonClick() {
        cambiarAMainScene();
    }

    public Parent getContainer() {
        return loginContainer;
    }

    public void cambiarAMainScene(){
        try{
            Stage stage = (Stage) loginContainer.getScene().getWindow();
            if(stage == null) return;
            Scene scene = new Scene(new MainEscena());
            stage.setScene(scene);
            stage.setTitle("Main");
            stage.sizeToScene();
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
            System.out.println("No se pudo cargar de escena");
            e.printStackTrace();
        }
    }
}