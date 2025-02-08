package com.odvp.biblioteca.ControladoresVistas;

import com.odvp.biblioteca.ManejoAmbientes.ManejadorDeAmbientes;
import com.odvp.biblioteca.MenuOpciones.ManejadorOpciones;
import com.odvp.biblioteca.MenuOpciones.OpcionServicio;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainController implements IVista{
    @FXML
    BorderPane mainContainer;
    @FXML
    VBox panelVistas;

    @FXML
    public void initialize(){
        ManejadorDeAmbientes.setMainScene(mainContainer);
        cargarOpciones();
        ManejadorOpciones.setCurrentOption(OpcionServicio.getOpciones().getFirst());

    }

    public void cargarOpciones(){
        try{
            for(OptionController opcion: ManejadorOpciones.getOpciones().values()){
                panelVistas.getChildren().add(opcion.getContainer());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Parent getContainer() {
        return mainContainer;
    }
}
