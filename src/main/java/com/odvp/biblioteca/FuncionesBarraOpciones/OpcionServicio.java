package com.odvp.biblioteca.FuncionesBarraOpciones;

import com.odvp.biblioteca.LibraryApplication;

import java.util.ArrayList;
import java.util.List;

/*
    Esta clase lo unico que hace es guardar la informacion de las opciones de la barra izquierda
    para ser usada por las funciones BarraOpciones como ManejadorAmbientes o ManejadorOpciones
*/

public class OpcionServicio {
    private static List<Opcion> opciones;
    private OpcionServicio(){

    }
    public static void init(){
        try{
            Opcion libros = new Opcion(
                    LibraryApplication.class.getResource("Icons/Opciones/books-stack-of-three.png").toExternalForm(),
                    "Libros",
                    "Vistas/BookScene/books-view.fxml");
            Opcion reservas = new Opcion(
                    LibraryApplication.class.getResource("Icons/Opciones/hand.png").toExternalForm(),
                    "Reservas",
                    "");
            Opcion deudas = new Opcion(LibraryApplication.class.getResource("Icons/Opciones/money-bag.png").toExternalForm(),
                    "Deudas",
                    "");
            Opcion prestamos = new Opcion(
                    LibraryApplication.class.getResource("Icons/Opciones/open-hand.png").toExternalForm(),
                    "Prestamos",
                    "");
            Opcion autores = new Opcion(
                    LibraryApplication.class.getResource("Icons/Opciones/quill.png").toExternalForm(),
                    "Autores",
                    "");
            Opcion usuarios = new Opcion(LibraryApplication.class.getResource(
                    "Icons/Opciones/user.png").toExternalForm(),
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
            ManejadorDeMaestros.init();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static List<Opcion> getOpciones() {
        return opciones;
    }
}
