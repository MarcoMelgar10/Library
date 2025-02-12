package com.odvp.biblioteca;
import com.odvp.biblioteca.FuncionesBarraOpciones.OpcionServicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

/*
    realiza el arranque de la app y carga la ventana con el loggin.
 */

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        OpcionServicio.init();
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Biblioteca");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}