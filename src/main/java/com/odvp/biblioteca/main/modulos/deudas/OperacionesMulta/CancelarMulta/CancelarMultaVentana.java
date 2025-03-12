package com.odvp.biblioteca.main.modulos.deudas.OperacionesMulta.CancelarMulta;

import com.odvp.biblioteca.objetosVisuales.MultaCardData;
import com.odvp.biblioteca.database.daos.MultaDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class CancelarMultaVentana extends Stage {
    public boolean isHubieronCambios() {
        return hubieronCambios;
    }
    private boolean hubieronCambios;


    public CancelarMultaVentana(MultaDAO multaDAO, MultaCardData multaCardData) {
        setTitle("Confirmar Cancelación");
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        // 📌 Etiquetas con los datos de la multa
        Label lblTitulo = new Label("¿Desea cancelar la siguiente multa?");
        lblTitulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblId = new Label("ID: " + multaCardData.getID());
        Label lblMonto = new Label("Monto: " + multaCardData.getMonto() + "Bs.");
        Label lblUsuario = new Label("Usuario: " + multaCardData.getNombreUsuario());

        // 📌 Botón para confirmar cancelación
        Button btnConfirmar = new Button("Cancelar Multa");
        btnConfirmar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                multaDAO.cancelarMulta(multaCardData.getID());
                hubieronCambios = true;
                close();
            }
        });

        //  Botón para cerrar la ventana sin cancelar
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> close());

        //  Layout principal
        VBox layout = new VBox(10, lblTitulo, lblId, lblMonto, lblUsuario, btnConfirmar, btnCerrar);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ccc; -fx-border-width: 2px; -fx-border-radius: 10px;");

        //  Escena
        Scene scene = new Scene(layout, 300, 300);
        setScene(scene);
        showAndWait();
    }
}
