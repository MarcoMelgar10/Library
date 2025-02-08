package com.odvp.biblioteca.ManejoAmbientes;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.MenuOpciones.Opcion;
import com.odvp.biblioteca.MenuOpciones.OpcionServicio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ManejadorDeAmbientes {
    private static BorderPane mainScene;
    private static Opcion currentAmbient;
    private static Map<Opcion,IVista> ambientes;

    public static void setMainScene(BorderPane newScene) {
        mainScene = newScene;
    }

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

    public static IVista getAmbiente(Opcion opcion){
        return ambientes.get(opcion);
    }
}