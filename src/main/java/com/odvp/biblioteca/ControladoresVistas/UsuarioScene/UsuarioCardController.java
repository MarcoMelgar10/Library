package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.UsuarioData;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class UsuarioCardController implements IVista {
    @FXML
    HBox container;
    @FXML
    Label nombreLabel;
    @FXML
    ImageView userLegend;
    private int id;

    public void init(UsuarioData usuarioData){
        nombreLabel.setText(usuarioData.getNombre());
        userLegend.setImage(usuarioData.getLegenda());
        id = usuarioData.getId();
    }

    public int getId() {
        return id;
    }

    @Override
    public Parent getContainer() {
        return container;
    }
}
