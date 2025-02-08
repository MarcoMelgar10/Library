package com.odvp.biblioteca.MenuOpciones;

import com.odvp.biblioteca.ManejoAmbientes.ManejadorDeAmbientes;
import com.odvp.biblioteca.LibraryApplication;

import java.util.ArrayList;
import java.util.List;

public class OpcionServicio {
    private static List<Opcion> opciones;
    private OpcionServicio(){

    }
    public static void init() throws Exception {
        Opcion libros = new Opcion(
                LibraryApplication.class.getResource("/Icons/Opciones/books-stack-of-three.png").toExternalForm(),
                "Libros",
                "BookScene/books-view.fxml");
        Opcion reservas = new Opcion(
                LibraryApplication.class.getResource("/Icons/Opciones/hand.png").toExternalForm(),
                "Reservas",
                "");
        Opcion deudas = new Opcion(LibraryApplication.class.getResource("/Icons/Opciones/money-bag.png").toExternalForm(),
                "Deudas",
                "");
        Opcion prestamos = new Opcion(
                LibraryApplication.class.getResource("/Icons/Opciones/open-hand.png").toExternalForm(),
                "Prestamos",
                "");
        Opcion autores = new Opcion(
                LibraryApplication.class.getResource("/Icons/Opciones/quill.png").toExternalForm(),
                "Autores",
                "");
        Opcion usuarios = new Opcion(LibraryApplication.class.getResource(
                "/Icons/Opciones/user.png").toExternalForm(),
                "Usuarios",
                "");

        opciones = new ArrayList<>();
        opciones.add(libros);
        opciones.add(reservas);
        opciones.add(deudas);
        opciones.add(prestamos);
        opciones.add(autores);
        opciones.add(usuarios);

        ManejadorOpciones.setOptions();
        ManejadorDeAmbientes.init();
    }

    public static List<Opcion> getOpciones() {
        return opciones;
    }

    public static String getPathPorNombre(String nombre){
        if(opciones == null) return "";
        String respuesta = "";
        for(Opcion opcion : opciones){
            if(opcion.getTitle().equals(nombre)) respuesta = opcion.getViewPath();
        }
        return respuesta;
    }

}
