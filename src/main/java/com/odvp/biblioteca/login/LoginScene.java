package com.odvp.biblioteca.login;

import com.odvp.biblioteca.main.MainEscena;
import com.odvp.biblioteca.main.ModeloMain;
import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.objetos.Administrador;
import com.odvp.biblioteca.servicios.ServicioIconos;
import com.odvp.biblioteca.servicios.ServicioSesion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScene extends VBox{

    private HBox topBar;
    private StackPane botonAmbiente;
    private ImageView ambienteIcon;
    private Label titleLabel, ambienteLabel;
    private TextField userField;
    private PasswordField passwordField;
    private Button aceptarButton;

    public LoginScene(){
        init();
    }

    public void init(){

        setPrefHeight(370);
        // Barra superior con icono
        getStylesheets().add(LibraryApplication.class.getResource("Styles/Styles.css").toExternalForm());

        topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(10, 10, 0, 10));

    /*    ambienteIcon = new ImageView(ServicioIconos.LOGIN_CONFIGURADOR);
        ambienteIcon.setFitHeight(30);
        ambienteIcon.setFitWidth(30);

        botonAmbiente = new StackPane(ambienteIcon);
        botonAmbiente.setPrefSize(30, 30);
        topBar.getChildren().add(botonAmbiente);
*/
        // Contenedor de Login
        VBox loginContainer = new VBox();
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.setPrefSize(565, 270);
        loginContainer.setSpacing(10);

        titleLabel = new Label("EBEN-EZER");
        titleLabel.getStyleClass().add("label-title");
        titleLabel.setFont(new Font(13));

        ambienteLabel = new Label("BIBLIOTECA");
        ambienteLabel.getStyleClass().add("label-sub-title");
        ambienteLabel.setFont(new Font(13));

        userField = new TextField();
        userField.setPromptText("Usuario");
        userField.setMaxWidth(200);

        passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        passwordField.setMaxWidth(200);

        aceptarButton = new Button("Aceptar");
        aceptarButton.setOnAction(event -> onAceptarButtonClick());

        // Agregar elementos al contenedor de login
        loginContainer.getChildren().addAll(titleLabel, ambienteLabel, userField, passwordField, aceptarButton);

        // Agregar componentes al root
        getChildren().addAll(topBar, loginContainer);

    }

    private void onAceptarButtonClick() {

        ServicioSesion.setAdministrador(new Administrador(1,"Oscar",""));

        setAmbiente();
    }

    private void setAmbiente(){
        Stage stage = (Stage) getScene().getWindow();
        Scene scene = new Scene(new MainEscena(new ModeloMain()));
        stage.setScene(scene);
        stage.setTitle("Main");
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();
    }
}
