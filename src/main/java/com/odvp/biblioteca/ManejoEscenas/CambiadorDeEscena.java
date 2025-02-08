package com.odvp.biblioteca.ManejoEscenas;

import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CambiadorDeEscena {
    private static Stage stage;
    public static void setStage(Stage newStage){
        stage = newStage;
    }
    public static void cambiarEscena(String sceneFXML){
        try{
            if(stage == null) return;
            Parent root = FXMLLoader.load(LibraryApplication.class.getResource(sceneFXML));
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
