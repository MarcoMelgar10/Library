package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

/*
    controlador que muestra la ventana emergente cuando se pulsiona las opciones (Editar, Add, Visualizar) libro.
    Es una ventana que se reutiliza para cada opcion de acuerdo al tipo de operacion que se pasa en forma de String
    como parametro en la funcion initComponents()
 */

public class BookOperationController {
    private Libro libro;
    public static final String TYPE_ADD = "ADD_BOOK";
    public static final String TYPE_EDIT = "EDIT_BOOK";
    public static final String TYPE_VIEW = "VIEW_BOOK";

    @FXML
    VBox operationBookContainer;
    @FXML
    HBox buttonsContainer;

    @FXML
    Label titleWindow;
    @FXML
    Button aceptarButton, cancelarButton;
    @FXML
    TextArea observacionTextArea;
    @FXML
    TextField titleField, idField;
    @FXML
    Spinner stockSpinner, disponibleSpinner;
    @FXML
    DatePicker publicacionDatePicker;
    @FXML
    ComboBox autorComboBox, categoriaComboBox;

    @FXML
    public void initialize(){
        cancelarButton.setOnAction(e -> {
            Stage ventana = (Stage) buttonsContainer.getScene().getWindow();
            ventana.close();
        });
    }

    //initComponents: recibe el tipo de operacion y de acuerdo a eso configura la ventana

    public void initComponents(Libro libro, String operationType){
        this.libro = libro;
        switch (operationType){
            case TYPE_ADD:
                initAddMode();
                break;
            case TYPE_EDIT:
                initEditMode();
                break;
            case TYPE_VIEW:
                initViewMode();
                break;
        }
    }

    private void initAddMode(){
        titleWindow.setText("Nuevo Libro");
        aceptarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hola");
            }

        });
    }

    private void initEditMode(){
        idField.setText(libro.getID() +"");
        titleWindow.setText("Editar Libro");
        titleField.setText(libro.getTitulo());
        autorComboBox.setValue(libro.getIdAutor());
        categoriaComboBox.setValue(libro.getIdCategoria());
        publicacionDatePicker.setValue(libro.getPublicacion().toLocalDate());
        observacionTextArea.setText(libro.getObservacion());
    }
    private void initViewMode(){
        idField.setText(libro.getID() +"");
        titleWindow.setText("Visualizar Libro");
        titleField.setEditable(false);
        titleField.setText(libro.getTitulo());
        autorComboBox.setValue(libro.getIdAutor());
        autorComboBox.setDisable(true);
        autorComboBox.setOpacity(1);
        categoriaComboBox.setDisable(true);
        categoriaComboBox.setOpacity(1);
        categoriaComboBox.setValue(libro.getIdCategoria());
        stockSpinner.setDisable(true);
        stockSpinner.setOpacity(1);
        disponibleSpinner.setEditable(false);
        disponibleSpinner.setDisable(true);
        disponibleSpinner.setOpacity(1);
        publicacionDatePicker.setDisable(true);
        publicacionDatePicker.setValue(libro.getPublicacion().toLocalDate());
        publicacionDatePicker.setOpacity(1);
        observacionTextArea.setEditable(false);
        observacionTextArea.setText(libro.getObservacion());
        buttonsContainer.getChildren().remove(aceptarButton);
        cancelarButton.setText("Salir");
    }
}
