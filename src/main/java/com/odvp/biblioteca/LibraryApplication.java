package com.odvp.biblioteca;
import com.odvp.biblioteca.login.LoginScene;
import com.odvp.biblioteca.database.ConexionDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    realiza el arranque de la app y carga la ventana con el loggin.
 */

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Thread(ConexionDB::getOrCreate).start();
        Scene scene = new Scene(new LoginScene());
        stage.setTitle("Biblioteca");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}