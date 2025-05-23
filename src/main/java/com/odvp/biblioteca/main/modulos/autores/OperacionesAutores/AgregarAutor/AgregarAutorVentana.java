package com.odvp.biblioteca.main.modulos.autores.OperacionesAutores.AgregarAutor;

import com.odvp.biblioteca.objetos.Autor;
import com.odvp.biblioteca.database.daos.AutorDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class AgregarAutorVentana extends Stage {

    private TextField nombreField;
    private Label idField;
    private TextArea resenaArea;
    private Button cancelarButton, aceptarButton;
    private AutorDAO DAO;

    private boolean hubieronCambios = false;

    public AgregarAutorVentana(AutorDAO autorDAO) {
        this.DAO = autorDAO;
        setTitle("Agregar Autor");
        Scene scene = buildScene();
        setScene(scene);
        centerOnScreen();
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }

    public Scene buildScene(){
        VBox root = new VBox(8);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setPrefWidth(330);
       // root.setSpacing(40);

        // Título
        Label titleWindow = new Label("Agregar Autor");
        titleWindow.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 22));
        StackPane titleContainer = new StackPane(titleWindow);
        titleContainer.setPrefHeight(40);

        Separator separator = new Separator();

        // GridPane para los campos del formulario
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        Label idLabel = new Label("ID:");
        idField = new Label(DAO.getNextId() +"");

        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField();

        Label resenaLabel = new Label("Resena:");
        resenaArea = new TextArea();
        resenaArea.setPromptText("Escriba alguna observacion...");
        resenaArea.setWrapText(true);
        resenaArea.setFont(Font.font("System", javafx.scene.text.FontPosture.ITALIC, 12));
        ScrollPane scrollPane = new ScrollPane(resenaArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(160);

        // Agregar elementos al GridPane
        formGrid.addRow(0, idLabel, idField);
        formGrid.addRow(1, nombreLabel, nombreField);
        formGrid.addRow(2, resenaLabel);

        // Contenedor de botones
        cancelarButton = new Button("Cancelar");
        cancelarButton.setOnAction( e -> close());
        aceptarButton = new Button("Aceptar");
        aceptarButton.setOnAction( e-> {
            if(validar()){
                ejecutar();
            }
            else JOptionPane.showMessageDialog(null,"Hay datos invalidos");
        });
        HBox buttonsContainer = new HBox(8, cancelarButton, aceptarButton);
        buttonsContainer.setAlignment(Pos.CENTER);

        // Agregar elementos al VBox principal
        root.getChildren().addAll(titleContainer, separator, formGrid,scrollPane, buttonsContainer);

        return new Scene(root);
    }

    public boolean validar() {
        boolean nombreVacio = nombreField.getText().trim().isEmpty();
        return !nombreVacio;
    }

    public void ejecutar(){
        int id = Integer.parseInt(idField.getText().trim());
        String nombre = nombreField.getText();
        String resena = resenaArea.getText();
        Autor autor = new Autor(id,nombre,resena);
        DAO.insertar(autor);
        hubieronCambios = true;
        close();
    }

    public boolean isHubieronCambios() {
        return hubieronCambios;
    }
}

