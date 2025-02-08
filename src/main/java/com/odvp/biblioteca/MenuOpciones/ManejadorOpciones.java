package com.odvp.biblioteca.MenuOpciones;

import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.ControladoresVistas.OptionController;
import com.odvp.biblioteca.ManejoAmbientes.ManejadorDeAmbientes;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.util.*;

public class ManejadorOpciones {
    private static Opcion currentOpcion;
    private static Map<Opcion,OptionController> opciones;

    public static void setOptions() throws Exception{
        opciones = new LinkedHashMap<>() {
        };
        for(Opcion opcion : OpcionServicio.getOpciones()){
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("iconOption-view.fxml"));
            loader.load();
            OptionController optionController = loader.getController();
            optionController.initOptionView(opcion);
            opciones.put(opcion,optionController);
        }
    }

    public static void setCurrentOption(Opcion opcionNueva){
        if(opcionNueva.equals(currentOpcion)) return;
        currentOpcion = opcionNueva;
        opciones.get(currentOpcion).getContainer().getStyleClass().add("option-container-selected");
        ManejadorDeAmbientes.cambiarAmbiente(currentOpcion);
        for (Opcion opcion : opciones.keySet()) {
            if(opcion != currentOpcion){
                opciones.get(opcion).getContainer().getStyleClass().remove("option-container-selected");
            }
        }
    }

    public static Map<Opcion, OptionController> getOpciones() {
        return opciones;
    }
}
