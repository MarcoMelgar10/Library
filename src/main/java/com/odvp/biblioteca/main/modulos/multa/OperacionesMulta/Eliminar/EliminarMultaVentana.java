package com.odvp.biblioteca.main.modulos.multa.OperacionesMulta.Eliminar;

import com.odvp.biblioteca.database.daos.MultaDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EliminarMultaVentana extends Stage {

    private Button aceptarButton, cancelarButton;
    private PasswordField passwordField;
    private boolean eliminar = false;
    private MultaDAO multaDAO;
    private int ID;
    private final String contra = "odvp";

    public EliminarMultaVentana(int multaId, MultaDAO multaDAO) {
        setTitle("Eliminar");
        this.multaDAO = multaDAO;
        this.ID = multaId;
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setPrefSize(332, 290);

        Label titleLabel = new Label("Eliminar Multa");
        titleLabel.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 16));

        Separator separator = new Separator();
        separator.setPrefWidth(200);

        Label alertaLabel = new Label("Mensaje de alerta");
        alertaLabel.setText("¿Estás seguro que deseas dar de baja la multa con el ID: " + multaId );
        alertaLabel.setWrapText(true);
        alertaLabel.setAlignment(Pos.CENTER);
        alertaLabel.setPrefWidth(280);
        alertaLabel.setFont(Font.font("System", javafx.scene.text.FontWeight.BOLD, javafx.scene.text.FontPosture.ITALIC, 12));

        Label passwordPromptLabel = new Label("Para continuar introduce la clave de seguridad:");

        passwordField = new PasswordField();
        passwordField.setMaxWidth(200);
        passwordField.setPromptText("Clave");


        aceptarButton = new Button("Confirmar");
        aceptarButton.setOnAction(e-> {
            if(validar()) ejecutar();
            else System.out.println("Clave incorrecta");
        });

        cancelarButton = new Button("Cancelar");
        cancelarButton.setOnAction(e-> close());

        HBox buttonContainer = new HBox(cancelarButton, aceptarButton);
        buttonContainer.setSpacing(8);
        buttonContainer.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, separator, alertaLabel, passwordPromptLabel, passwordField, buttonContainer);

        Scene scene = new Scene(root);
        setScene(scene);
        centerOnScreen();
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }

    public boolean isEliminar() {
        return eliminar;
    }

    private boolean validar(){
        return contra.equals(passwordField.getText());
    }
    private void ejecutar(){
        multaDAO.eliminar(ID);
        eliminar = true;
        close();
    }
}
