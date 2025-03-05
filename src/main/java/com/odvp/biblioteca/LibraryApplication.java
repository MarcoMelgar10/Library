package com.odvp.biblioteca;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

/*
    realiza el arranque de la app y carga la ventana con el loggin.
 */

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Thread(ConexionDB::getOrCreate).start();
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Biblioteca");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch();}
}