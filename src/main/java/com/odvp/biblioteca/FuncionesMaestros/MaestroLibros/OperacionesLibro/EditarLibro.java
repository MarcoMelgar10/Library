package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookOperationController;
import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

/*
    crea la ventana para editar un libro
 */

public class EditarLibro implements IOperacionLibro{
    private Libro libro;
    public EditarLibro(Integer libroID){
        //reemplazar por metodo que obtenga el libro de la base de datos, esto es solo prueba:
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
            FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("BookScene/book-operation.fxml"));
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

    @Override
    public Libro getLibroData() {
        return libro;
    }
}
