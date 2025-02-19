package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.Servicios.ServicioIconos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class DefaultSearcher extends HBox {

    private TextField buscador;
    private StackPane iconoContenedor;
    private ImageView icono;


    private DefaultSearcher(){
        getStylesheets().add(LibraryApplication.class.getResource("Styles/Styles.css").toExternalForm());
        buscador = new TextField();
        buscador.setPromptText("Buscar");
        buscador.scaleYProperty().setValue(1.05);
        buscador.getStyleClass().add("text-field-search");
        setAlignment(Pos.CENTER);

        getChildren().addAll(buscador);
    }
    private DefaultSearcher(String imagenInicial){
        buscador = new TextField();
        buscador.setPromptText("Buscar");
        buscador.scaleYProperty().setValue(1.05);
        buscador.getStyleClass().add("text-field-search");
        icono = new ImageView(new Image(imagenInicial));
        icono.setFitWidth(15);
        icono.setFitHeight(15);
        iconoContenedor = new StackPane();
        iconoContenedor.setAlignment(Pos.CENTER);
        iconoContenedor.getStyleClass().add("button-search-shape");
        iconoContenedor.setPadding(new Insets(0,8,0,8));
        iconoContenedor.getChildren().add(icono);
        setAlignment(Pos.CENTER);

        getChildren().addAll(buscador, iconoContenedor);
    }
    public static DefaultSearcher getSimpleSearcher(){
        return new DefaultSearcher();
    }
    public static DefaultSearcher getDynamicSearcher(String imagenInicial){
        return new DefaultSearcher(imagenInicial);
    }
    public void setIcon(Image image){
        icono.setImage(image);
    }
    public StackPane getButtonSearcher(){
        return iconoContenedor;
    }

    public TextField getBuscador() {
        return buscador;
    }
}
