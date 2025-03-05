package com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.AgregarUsuario;

import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.*;
import com.odvp.biblioteca.postgresql.CRUD.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class AgregarUsuarioVentana extends Stage {/*

    private TextField nombreField, apellidoPaternoField, apellidoMaternoField, telefonoField, direccionField;
    private Label idContenido, bloqueadoContenido;
    private Button cancelarButton, aceptarButton;
    private Libro libro;
    private UsuarioDAO usuarioDAO;

    private boolean hubieronCambios = false;

    public AgregarUsuarioVentana(UsuarioDAO usuarioDAO, Usuario usuario) {
        this.usuarioDAO = usuarioDAO;
        setTitle("Agregar Usuario");
        Scene scene = buildScene();
        initValues();
        setScene(scene);
        centerOnScreen();
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }

    public Scene buildScene(){
        VBox root = new VBox(8);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        // Título
        Label titleWindow = new Label("Agregar libro");
        titleWindow.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 22));
        StackPane titleContainer = new StackPane(titleWindow);
        titleContainer.setPrefHeight(40);

        Separator separator = new Separator();

        // GridPane para los campos del formulario
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        Label idLabel = new Label("ID:");
        idContenido = new Label("");
        idContenido.setPrefWidth(50);

        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField();

        Label apellidoPaternoLabel = new Label("Apellido Pat.:");
        apellidoPaternoField = new TextField();


        Label apellidoMaternoLabel = new Label("Apellido Mat.:");
        apellidoMaternoField = new TextField();

        Label telefonoLabel = new Label("Telefono:");
        telefonoField = new TextField();

        Label direccion = new Label("Dirección:");
        direccionField = new TextField();

        Label bloqueadoLabel = new Label("Estado bloqueo:");
        bloqueadoContenido = new Label();

        // Agregar elementos al GridPane
        formGrid.addRow(0, idLabel, idContenido);
        formGrid.addRow(1, titleLabel, titleField);
        formGrid.addRow(2, autorLabel, autorComboBox);
        formGrid.addRow(3, categoriaLabel, categoriaComboBox);
        formGrid.addRow(4, subCategoriaLabel, subCategoriaComboBox);
        formGrid.addRow(5, fechaLabel, publicacionDatePicker);
        formGrid.addRow(6, stockLabel, stockSpinner);
        formGrid.addRow(7, observacionLabel);

        // Contenedor de botones
        cancelarButton = new Button("Cancelar");
        cancelarButton.setOnAction( e -> close());
        aceptarButton = new Button("Aceptar");
        aceptarButton.setOnAction( e-> {
            if(validar()) ejecutar();
            else System.out.println("Hay datos invalidos");
        });
        HBox buttonsContainer = new HBox(8, cancelarButton, aceptarButton);
        buttonsContainer.setAlignment(Pos.CENTER);

        // Agregar elementos al VBox principal
        root.getChildren().addAll(titleContainer, separator, formGrid, scrollPane, buttonsContainer);

        return new Scene(root, 330, 550);
    }

    public void initValues(){
        new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int nextID = libroDAO.getNextId();
                Platform.runLater(() -> idField.setText( nextID+""));

                for (Autor autor : autorDAO.obtenerAutoresAlfabeticamente()) {
                    String nombre = autor.getNombre();
                    int id = autor.getID();

                    Platform.runLater(() -> {
                        autorComboBox.getItems().add(nombre);
                        autores.put(nombre, id);
                    });
                }
                for (CategoryData categoryData : categoriaDAO.listaCategoriasAlfabeticamente()) {
                    String nombre = categoryData.getNombre();
                    int id = categoryData.getId();

                    Platform.runLater(() -> {
                        categoriaComboBox.getItems().add(nombre);
                        categorias.put(nombre, id);
                    });
                }
                categoriaComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    int categoriaId = categorias.get(newValue);
                    subCategorias.clear();
                    subCategoriaComboBox.getItems().clear();
                    List<SubCategoryData> subCategoriasData = subCategoriaDAO.obtenerSubCategoriasPorCategoriaAlfabeticamente(categoriaId);
                    Platform.runLater(() -> {
                        for(SubCategoryData subCategoryData : subCategoriasData){
                            int id = subCategoryData.getId();
                            String nombre = subCategoryData.getNombre();
                            subCategorias.put(nombre, id);
                            subCategoriaComboBox.getItems().add(nombre);
                        }
                    });
                });
                return null;
            }
        }).start();
    }

    public boolean validar() {
        boolean tituloVacio = titleField.getText().trim().isEmpty();
        boolean autorValido = autores.get(autorComboBox.getValue()) != null;
        boolean categoriaValida = categorias.get(categoriaComboBox.getValue()) != null;
        boolean subCategoriaValida = subCategorias.get(subCategoriaComboBox.getValue()) != null;
        boolean stockNoNulo = stockSpinner.getValue() != null;
        return !tituloVacio && autorValido && categoriaValida && stockNoNulo && subCategoriaValida;
    }

    public void ejecutar(){
        String titulo = titleField.getText();
        int idAutor = autores.get(autorComboBox.getValue());
        int idCategoria = categorias.get(categoriaComboBox.getValue());
        int idSubCategoria = subCategorias.get(subCategoriaComboBox.getValue());
        Date date = null;
        if(publicacionDatePicker.getValue() != null){
            date = Date.valueOf(publicacionDatePicker.getValue());
        }
        String observacion = observacionTextArea.getText();
        int stock = stockSpinner.getValue();
        Libro libro = new Libro.Builder()
                .titulo(titulo)
                .idCategoria(idCategoria)
                .idAutor(idAutor)
                .stock(stock)
                .stockDisponible(stock)
                .observacion(observacion)
                .idSubCategoria(idSubCategoria)
                .publicacion(date)
                .build();
        libroDAO.insertar(libro);
        hubieronCambios = true;
        close();
    }

    public boolean isHubieronCambios() {
        return hubieronCambios;
    }*/
}

