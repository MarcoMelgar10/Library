package com.odvp.biblioteca.ControladoresVistas;

import com.odvp.biblioteca.ManejoEscenas.CambiadorDeEscena;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LoginController  implements IVista{
    @FXML
    public VBox loginContainer;
    @FXML
    private Label titleLabel;
    @FXML
    protected void onAceptarButtonClick() {
        CambiadorDeEscena.cambiarEscena("main-view.fxml");
    }

    @Override
    public Parent getContainer() {
        return loginContainer;
    }
}