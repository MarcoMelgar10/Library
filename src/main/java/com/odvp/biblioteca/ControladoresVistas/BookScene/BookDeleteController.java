package com.odvp.biblioteca.ControladoresVistas.BookScene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookDeleteController {
    @FXML
    public VBox deleteContainer;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button aceptarButton;
    @FXML
    Label alertaLabel;

    @FXML
    public void aceptarButtonAction(){
        Stage ventana =  (Stage) deleteContainer.getScene().getWindow();
        ventana.close();
    }

    public void initComponents(int libroId) {
        alertaLabel.setText("¿Estás seguro que deseas dar de baja el libro con el ID: " +
                libroId + " ? EL libro dejará de estar disponible para prestamos");
    }
}
