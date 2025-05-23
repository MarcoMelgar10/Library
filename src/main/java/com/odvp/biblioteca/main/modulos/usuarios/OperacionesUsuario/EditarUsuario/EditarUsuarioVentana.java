package com.odvp.biblioteca.main.modulos.usuarios.OperacionesUsuario.EditarUsuario;

import com.odvp.biblioteca.objetos.Usuario;
import com.odvp.biblioteca.database.daos.UsuarioDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class EditarUsuarioVentana extends Stage {

    private TextField nombreField, apellidoPaternoField, apellidoMaternoField, telefonoField, direccionField, idField;
    private Button cancelarButton, aceptarButton;
    private UsuarioDAO DAO;
    private Usuario usuario;
    private boolean hubieronCambios = false;

    public EditarUsuarioVentana(UsuarioDAO usuarioDAO, Usuario usuario) {
        this.usuario = usuario;
        this.DAO = usuarioDAO;
        setTitle("Modificar Usuario");
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
        root.setPrefWidth(330);
        root.setSpacing(40);

        // Título
        Label titleWindow = new Label("Modificar Usuario");
        titleWindow.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 22));
        StackPane titleContainer = new StackPane(titleWindow);
        titleContainer.setPrefHeight(40);

        Separator separator = new Separator();

        // GridPane para los campos del formulario
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        Label idLabel = new Label("CI:");
        idField = new TextField();

        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField();

        Label apellidoPaternoLabel = new Label("Apellido Pat.:");
        apellidoPaternoField = new TextField();


        Label apellidoMaternoLabel = new Label("Apellido Mat.:");
        apellidoMaternoField = new TextField();

        Label telefonoLabel = new Label("Telefono:");
        telefonoField = new TextField();

        Label direccionLabel = new Label("Dirección:");
        direccionField = new TextField();

        // Agregar elementos al GridPane
        formGrid.addRow(0, idLabel, idField);
        formGrid.addRow(1, nombreLabel, nombreField);
        formGrid.addRow(2, apellidoPaternoLabel, apellidoPaternoField);
        formGrid.addRow(3, apellidoMaternoLabel, apellidoMaternoField);
        formGrid.addRow(4, telefonoLabel, telefonoField);
        formGrid.addRow(5, direccionLabel, direccionField);

        // Contenedor de botones
        cancelarButton = new Button("Cancelar");
        cancelarButton.setOnAction( e -> close());
        aceptarButton = new Button("Aceptar");
        aceptarButton.setOnAction( e-> {
            if(validar()){
                if(validarExistenciaDeUsuario()) ejecutar();
                else JOptionPane.showMessageDialog(null,"El ci '" + idField.getText() + "' ya está en uso");

            }
            else JOptionPane.showMessageDialog(null,"Hay datos invalidos");

        });
        HBox buttonsContainer = new HBox(8, cancelarButton, aceptarButton);
        buttonsContainer.setAlignment(Pos.CENTER);

        // Agregar elementos al VBox principal
        root.getChildren().addAll(titleContainer, separator, formGrid, buttonsContainer);

        return new Scene(root);
    }

    public void initValues(){
        idField.setText(Integer.toString(usuario.getId()));
        nombreField.setText(usuario.getNombre());
        apellidoPaternoField.setText(usuario.getApellidoPaterno());
        apellidoMaternoField.setText(usuario.getApellidoMaterno());
        telefonoField.setText(usuario.getTelefono());
        direccionField.setText(usuario.getDireccion());
    }

    public boolean validarExistenciaDeUsuario(){
        if(usuario.getId() == Integer.parseInt(idField.getText())){
            return true;
        }
        return DAO.obtener(Integer.parseInt(idField.getText())) == null;
    }

    public boolean validar() {
        boolean ciValido = idField.getText().trim().matches("^\\d{7,8}$");
        boolean nombreVacio = nombreField.getText().trim().isEmpty();
        boolean apellidoPaternoVacio = apellidoPaternoField.getText().trim().isEmpty();
        boolean apellidoMaternoVacio = apellidoMaternoField.getText().trim().isEmpty();
        boolean telefonoVacio = telefonoField.getText().trim().isEmpty();
        boolean direccionVacio = direccionField.getText().trim().isEmpty();
        return ciValido && !nombreVacio && !apellidoPaternoVacio && !apellidoMaternoVacio && !telefonoVacio && !direccionVacio;
    }

    public void ejecutar(){
        int id = Integer.parseInt(idField.getText().trim());
        String nombre = nombreField.getText();
        String apellidoPaterno = apellidoPaternoField.getText();
        String apellidoMaterno = apellidoMaternoField.getText();
        String telefono = telefonoField.getText();
        String direccion = direccionField.getText();
        Usuario nuevoUsuario = new Usuario.Builder()
                .idUsuario(id)
                .nombre(nombre)
                .apellidoPaterno(apellidoPaterno)
                .apellidoMaterno(apellidoMaterno)
                .telefono(telefono)
                .direccion(direccion)
                .build();
        DAO.modificar(nuevoUsuario);
        hubieronCambios = true;
        close();
    }

    public boolean isHubieronCambios() {
        return hubieronCambios;
    }
}

