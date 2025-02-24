package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EditarLibro;

import com.odvp.biblioteca.Objetos.Libro;
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

public class EditarLibroVentana extends Stage {

    private TextField titleField;
    private ComboBox<String> autorComboBox, categoriaComboBox;
    private DatePicker publicacionDatePicker;
    private Spinner<Integer> stockSpinner;
    private TextArea observacionTextArea;

    private Button cancelarButton, aceptarButton;
    private Libro libro;

    public EditarLibroVentana(Libro libro) {
        this.libro = libro;
        setTitle("Editar Libro");

        VBox root = new VBox(8);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        // Título
        Label titleWindow = new Label("Editar Libro");
        titleWindow.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 22));
        StackPane titleContainer = new StackPane(titleWindow);
        titleContainer.setPrefHeight(40);

        Separator separator = new Separator();

        // GridPane para los campos del formulario
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        Label idLabel = new Label("ID:");
        Label idField = new Label(libro.getID() + "");
        idField.setPrefWidth(50);

        Label titleLabel = new Label("Titulo:");
        titleField = new TextField();
        titleField.setText(libro.getTitulo());

        Label autorLabel = new Label("Autor:");
        autorComboBox = new ComboBox<>();
        autorComboBox.setPrefWidth(150);

        Label categoriaLabel = new Label("Categoria:");
        categoriaComboBox = new ComboBox<>();
        categoriaComboBox.setPrefWidth(150);

        Label fechaLabel = new Label("Fecha de publicación:");
        publicacionDatePicker = new DatePicker();
        publicacionDatePicker.setPrefWidth(125);
        publicacionDatePicker.setValue(libro.getPublicacion().toLocalDate());

        Label stockLabel = new Label("Stock:");
        stockSpinner = new Spinner<>(1, 1000, libro.getStock());
        stockSpinner.setPrefWidth(70);


        Label observacionLabel = new Label("Observacion:");
        observacionTextArea = new TextArea();
        observacionTextArea.setPromptText("Escriba alguna observacion...");
        observacionTextArea.setText(libro.getObservacion());
        observacionTextArea.setWrapText(true);
        observacionTextArea.setFont(Font.font("System", javafx.scene.text.FontPosture.ITALIC, 12));
        ScrollPane scrollPane = new ScrollPane(observacionTextArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(160);

        // Agregar elementos al GridPane
        formGrid.addRow(0, idLabel, idField);
        formGrid.addRow(1, titleLabel, titleField);
        formGrid.addRow(2, autorLabel, autorComboBox);
        formGrid.addRow(3, categoriaLabel, categoriaComboBox);
        formGrid.addRow(4, fechaLabel, publicacionDatePicker);
        formGrid.addRow(5, stockLabel, stockSpinner);
        formGrid.addRow(6, observacionLabel);

        // Contenedor de botones
        cancelarButton = new Button("Cancelar");
        aceptarButton = new Button("Aceptar");
        HBox buttonsContainer = new HBox(8, cancelarButton, aceptarButton);
        buttonsContainer.setAlignment(Pos.CENTER);

        // Agregar elementos al VBox principal
        root.getChildren().addAll(titleContainer, separator, formGrid, scrollPane, buttonsContainer);

        Scene scene = new Scene(root, 330, 550);
        setScene(scene);
        centerOnScreen();
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }
}

