package com.odvp.biblioteca.main.modulos.libros.OperacionesLibro.EditarLibro;

import com.odvp.biblioteca.objetos.Autor;
import com.odvp.biblioteca.objetosVisuales.CategoryData;
import com.odvp.biblioteca.objetos.Libro;
import com.odvp.biblioteca.objetosVisuales.IDatoVisual;
import com.odvp.biblioteca.objetosVisuales.SubCategoryData;
import com.odvp.biblioteca.database.daos.AutorDAO;
import com.odvp.biblioteca.database.daos.CategoriaDAO;
import com.odvp.biblioteca.database.daos.LibroDAO;
import com.odvp.biblioteca.database.daos.SubCategoriaDAO;
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

import javax.swing.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class EditarLibroVentana extends Stage {

    private TextField titleField;
    private ComboBox<String> autorComboBox, categoriaComboBox, subCategoriaComboBox;
    private DatePicker publicacionDatePicker;
    private Spinner<Integer> stockSpinner;
    private TextArea observacionTextArea;
    private Label idField;

    private Button cancelarButton, aceptarButton;

    private final HashMap<String, Integer> autores = new LinkedHashMap<>();
    private final HashMap<String, Integer> categorias = new LinkedHashMap<>();
    private final HashMap<String, Integer> subCategorias = new LinkedHashMap<>();
    private final AutorDAO autorDAO;
    private final CategoriaDAO categoriaDAO;
    private final LibroDAO libroDAO;
    private final SubCategoriaDAO subCategoriaDAO;

    private boolean hubieronCambios = false;
    private Libro libroInicial;

    public EditarLibroVentana(Libro libroInicial, LibroDAO libroDAO, AutorDAO autorDAO, CategoriaDAO categoriaDAO, SubCategoriaDAO subCategoriaDAO) {
        this.autorDAO = autorDAO;
        this.categoriaDAO = categoriaDAO;
        this.libroDAO = libroDAO;
        this.subCategoriaDAO = subCategoriaDAO;
        this.libroInicial = libroInicial;
        setTitle("Agregar libro");
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
        Label titleWindow = new Label("Editar libro");
        titleWindow.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 22));
        StackPane titleContainer = new StackPane(titleWindow);
        titleContainer.setPrefHeight(40);

        Separator separator = new Separator();

        // GridPane para los campos del formulario
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        Label idLabel = new Label("ID:");
        idField = new Label(libroInicial.getID() +"");
        idField.setPrefWidth(50);

        Label titleLabel = new Label("Titulo:");
        titleField = new TextField();
        titleField.setText(libroInicial.getTitulo());

        Label autorLabel = new Label("Autor:");
        autorComboBox = new ComboBox<>();
        autorComboBox.setPrefWidth(150);
        autorComboBox.setValue(libroInicial.getNombreAutor());
        autorComboBox.getItems().addAll();

        Label categoriaLabel = new Label("Categoria:");
        categoriaComboBox = new ComboBox<>();
        categoriaComboBox.setPrefWidth(150);

        Label subCategoriaLabel = new Label("Sub Categoria:");
        subCategoriaComboBox = new ComboBox<>();
        subCategoriaComboBox.setPrefWidth(150);

        Label fechaLabel = new Label("Fecha de publicación:");
        publicacionDatePicker = new DatePicker();
        publicacionDatePicker.setPrefWidth(125);
        if(libroInicial.getPublicacion() != null) publicacionDatePicker.setValue(libroInicial.getPublicacion().toLocalDate());


        Label stockLabel = new Label("Stock:");
        stockSpinner = new Spinner<>(1, 1000, libroInicial.getStock());
        stockSpinner.setEditable(true);
        stockSpinner.setPrefWidth(70);

        stockSpinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // Permite solo números
                stockSpinner.getEditor().setText(oldValue);
            }
        });

        Label observacionLabel = new Label("Observacion:");
        observacionTextArea = new TextArea();
        observacionTextArea.setPromptText("Escriba alguna observacion...");
        observacionTextArea.setWrapText(true);
        observacionTextArea.setFont(Font.font("System", javafx.scene.text.FontPosture.ITALIC, 12));
        observacionTextArea.setText(libroInicial.getObservacion());
        ScrollPane scrollPane = new ScrollPane(observacionTextArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(160);

        // Agregar elementos al GridPane
        formGrid.addRow(0, idLabel, idField);
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
            else JOptionPane.showMessageDialog(null, "Hay datos invalidos");
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
            protected Void call() {
                int nextID = libroDAO.getNextId();
                Platform.runLater(() -> idField.setText( nextID+""));

                for (IDatoVisual autor : autorDAO.obtenerAutoresAlfabeticamente()) {
                    String nombre = ((Autor)autor).getNombre();
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
                Platform.runLater(() -> {
                    categoriaComboBox.setValue(libroInicial.getNombreCategoria());
                    subCategoriaComboBox.setValue(libroInicial.getNombreSubCategoria());
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
        Libro libroFinal = new Libro.Builder()
                .ID(libroInicial.getID())
                .titulo(titulo)
                .idCategoria(idCategoria)
                .idAutor(idAutor)
                .stock(stock)
                .stockDisponible(stock)
                .observacion(observacion)
                .idSubCategoria(idSubCategoria)
                .publicacion(date)
                .build();
        libroDAO.modificar(libroFinal);
        hubieronCambios = true;
        close();

    }

    public boolean isHubieronCambios() {
        return hubieronCambios;
    }
}

