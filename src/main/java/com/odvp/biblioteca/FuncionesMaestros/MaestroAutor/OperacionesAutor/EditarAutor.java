package com.odvp.biblioteca.FuncionesMaestros.MaestroAutor.OperacionesAutor;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookOperationController;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

/*
    crea la ventana para editar un libro
 */

public class EditarAutor implements IOperacionAutor {
    private Libro libro;
    public EditarAutor(Integer libroID){
        //reemplazar por metodo que obtenga el libro de la base de datos, esto es solo prueba:
        Date date;
        date = new Date(10/10/2001);
        this.libro = new Libro
                .Builder()
                .idAutor(1)
                .idCategoria(1)
                .titulo("Las aventuras de los programadores junior")
                .publicacion(LocalDate.now())
                .observacion("Esta muy bueno")
                .stock(10)
                .stockDisponible(10)
                .ID(1)
                .build();
        buildWindow();
    }


    @Override
    public void buildWindow() {
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/BookScene/book-operation.fxml"));
            Parent root =fxmlLoader.load();
            BookOperationController controller =fxmlLoader.getController();
            controller.initComponents(libro, BookOperationController.TYPE_EDIT);
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
