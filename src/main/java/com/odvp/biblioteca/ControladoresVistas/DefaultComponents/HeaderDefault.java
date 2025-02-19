package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.LibraryApplication;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class HeaderDefault extends HBox {

    private String titulo;

    private Label titleLabel;
    private Pane spacer;
    private HBox buttonContainer;
    private HBox searcherContainer = new HBox();
    private List<ButtonDefault> botones;

    public HeaderDefault(String titulo) {
        getStylesheets().add(LibraryApplication.class.getResource("Styles/Styles.css").toExternalForm());
        this.titulo = titulo;
        initialize();
        botones = new ArrayList<>();
    }

    private void initialize() {
        // Configuración de la caja principal
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setMinHeight(70.0);
        this.setPrefHeight(70.0);
        this.setPadding(new Insets(20, 20, 20, 20));

        // Label principal
        titleLabel = new Label(titulo);

        // Espaciador flexible
        spacer = new Pane();
        spacer.setPrefHeight(200.0);
        spacer.setPrefWidth(200.0);
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Contenedor de botones
        buttonContainer = new HBox();
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        buttonContainer.setSpacing(8.0);
        HBox.setHgrow(buttonContainer, javafx.scene.layout.Priority.NEVER);
        HBox.setMargin(buttonContainer, new Insets(0, 20, 0, 0));
        // Agregar elementos a la caja principal
        this.getChildren().addAll(titleLabel, spacer, buttonContainer, searcherContainer);
    }


    public void addButton(ButtonDefault button){
        buttonContainer.getChildren().add(button);
        botones.add(button);
    }

    public void addButtons(ButtonDefault... buttons){
        for(ButtonDefault button : buttons){
            buttonContainer.getChildren().add(button);
            botones.add(button);
        }
    }

    public void setSearcherContainer(HBox searcher){
        searcherContainer = searcher;
    }

    public HBox getButtonContainer() {
        return buttonContainer;
    }

    public void deshabilitarBotones(boolean deshabilitar){
        for(ButtonDefault button : botones){
            button.desactivar(deshabilitar);
        }
    }
}
