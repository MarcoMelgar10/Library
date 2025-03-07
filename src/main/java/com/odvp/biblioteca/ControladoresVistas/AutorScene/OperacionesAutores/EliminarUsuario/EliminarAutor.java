package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EliminarUsuario;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModeloAutores;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;

/*
    crea la ventana de eliminar libro
 */

public class EliminarAutor {
    private final AutorDAO autorDAO = new AutorDAO();
    public EliminarAutor(ModeloAutores modelo){
        EliminarAutorVentana eliminarAutorVentana = new EliminarAutorVentana(modelo.getAutorSeleccionado().getID(),  autorDAO);
        if(eliminarAutorVentana.isEliminar()) modelo.anunciarCambio();
    }
}
