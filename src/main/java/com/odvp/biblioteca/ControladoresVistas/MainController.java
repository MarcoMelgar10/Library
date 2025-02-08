package com.odvp.biblioteca.ControladoresVistas;

import com.odvp.biblioteca.FuncionesBarraOpciones.ManejadorDeMaestros;
import com.odvp.biblioteca.FuncionesBarraOpciones.ManejadorOpciones;
import com.odvp.biblioteca.FuncionesBarraOpciones.OpcionServicio;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/*
    Controlador de la escena principal, donde a la izquiera se carga la barra de opciones
    y a la derecha la vista del Maestro que haya sido establecido en la clase ManejadorDeMaestros
 */

public class MainController implements IVista{
    @FXML
    BorderPane mainContainer;
    @FXML
    VBox panelVistas;

    @FXML
    public void initialize(){
        ManejadorDeMaestros.setMainScene(mainContainer);
        cargarOpciones();
        ManejadorOpciones.setCurrentOption(OpcionServicio.getOpciones().getFirst());

    }
    //cargarOpciones(): se cargan las vistas de la lista 'opciones' de la clase ManejadorOpciones
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
