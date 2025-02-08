package com.odvp.biblioteca.FuncionesBarraOpciones;

import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.ControladoresVistas.OptionController;
import javafx.fxml.FXMLLoader;

import java.util.*;

/*
    Esta clase se encarga de guardar las opciones establecidas en el OpcionServicio
    y asociarla a el controlador de cada card de opcion que se ve a la izquierda en la app.
    Cuando una card de opcion ese seleccionada se colorea y el resto se pone sin color.
*/

public class ManejadorOpciones {
    private static Opcion currentOpcion;
    private static Map<Opcion,OptionController> opciones;

    /*
    setOptions(): carga las opciones del OpcionServicio y crea un diccionario donde
    las llabes son las opcioones y el valor ees el controlador de la vista asociada a esa opcion (Maestro)
     */

    public static void setOptions() throws Exception{
        opciones = new LinkedHashMap<>() {
        };
        for(Opcion opcion : OpcionServicio.getOpciones()){
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/iconOption-view.fxml"));
            loader.load();
            OptionController optionController = loader.getController();
            optionController.initOptionView(opcion);
            opciones.put(opcion,optionController);
        }
    }

    /*
        setCurrentOption() : recibe una opcion como parametro, colorea la opcion nueva en pantalla y
        hace que la clase ManejadorDeMaestros cambia al Maestro asociado a esa opcion.
     */

    public static void setCurrentOption(Opcion opcionNueva){
        if(opcionNueva.equals(currentOpcion)) return;
        currentOpcion = opcionNueva;
        opciones.get(currentOpcion).getContainer().getStyleClass().add("option-container-selected");
        ManejadorDeMaestros.cambiarAmbiente(currentOpcion);
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
