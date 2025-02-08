package com.odvp.biblioteca.FuncionesBarraOpciones;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/*
    Cambia lo que se ve en la parte derecha de la vista principal de acuerdo a la opcion seleccionada a la izquierda
 */

public class ManejadorDeMaestros {
    private static BorderPane mainScene;
    private static Opcion currentAmbient;
    private static Map<Opcion,IVista> ambientes;

    //Se guarda la referencia del BorderPane del MainScene al que se el cambiará el contenido

    public static void setMainScene(BorderPane newScene) {
        mainScene = newScene;
    }

    /*
    init(): obtiene todas las opciones registradas en la clase OpcionServicio,
    carga los fxml (path) de cada Maestro que fué registrado ahí y obtiene sus controladores.
    Cada controlador (IVista) lo guarda en un diccionario donde la llave es la opcion.
     */

    public static void init() {
        try{
            ambientes = new LinkedHashMap<>();
            for(Opcion opcion : OpcionServicio.getOpciones()){
                if(Objects.equals(opcion.getViewPath(), "")) continue;
                if(currentAmbient == null) cambiarAmbiente(opcion);
                FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource(opcion.getViewPath()));
                loader.load();
                IVista ambienteController = loader.getController();
                ambientes.put(opcion, ambienteController);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al cargar los datos de los ambientes");
        }
    }

    /*
        cambiarAmbiente(): segun la opcion pasada como parametro se le cargará al mainScene (BordePane)
        la vista del Maestro que coincida con esa opcion en el diccionario
    */

    public static void cambiarAmbiente(Opcion opcion) {
        try {
            if (opcion.equals(currentAmbient)) return;
            IVista ambienteNuevo = ambientes.get(opcion);
            if(ambienteNuevo == null) return;
            mainScene.setCenter(ambienteNuevo.getContainer());
            currentAmbient = opcion;
        } catch (Exception e) {
            System.out.println("No se pudo cargar el ambiente");
            e.printStackTrace();
        }
    }
}