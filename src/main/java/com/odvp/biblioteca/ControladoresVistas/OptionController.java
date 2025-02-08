package com.odvp.biblioteca.ControladoresVistas;

import com.odvp.biblioteca.ManejoAmbientes.ManejadorDeAmbientes;
import com.odvp.biblioteca.MenuOpciones.ManejadorOpciones;
import com.odvp.biblioteca.MenuOpciones.Opcion;
import com.odvp.biblioteca.MenuOpciones.OpcionServicio;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class OptionController implements IVista{
    @FXML
    ImageView optionIcon;
    @FXML
    Label optionLabel;
    @FXML
    VBox optionContainer;
    private Opcion opcion;

    public void initOptionView(Opcion opcion){
        try{
            optionIcon.setImage(new Image(opcion.getImagePath()));
            optionLabel.setText(opcion.getTitle());
            this.opcion = opcion;
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void setCurrentOp(){
        ManejadorOpciones.setCurrentOption(opcion);
    }

    @Override
    public Parent getContainer() {
        return optionContainer;
    }

    public Opcion getOption() {
        return opcion;
    }
}
