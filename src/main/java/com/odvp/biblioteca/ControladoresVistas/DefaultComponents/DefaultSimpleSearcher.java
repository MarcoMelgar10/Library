package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.LibraryApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DefaultSimpleSearcher extends HBox {

    private TextField buscador;

    public DefaultSimpleSearcher(){
        getStylesheets().add(LibraryApplication.class.getResource("Styles/Styles.css").toExternalForm());
        buscador = new TextField();
        buscador.setPromptText("Buscar");
        buscador.scaleYProperty().setValue(1.05);
        buscador.getStyleClass().add("text-field-search");
        setAlignment(Pos.CENTER);

        getChildren().addAll(buscador);
    }
    public TextField getBuscador() {
        return buscador;
    }
}
