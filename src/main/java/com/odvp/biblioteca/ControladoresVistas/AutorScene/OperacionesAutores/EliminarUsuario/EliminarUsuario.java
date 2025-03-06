package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EliminarUsuario;

import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;

/*
    crea la ventana de eliminar libro
 */

public class EliminarUsuario {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    public EliminarUsuario(ModeloUsuarios modelo){
        EliminarUsuarioVentana eliminarUsuarioVentana = new EliminarUsuarioVentana(modelo.getUsuarioSeleccionado().getID(), usuarioDAO);
        if(eliminarUsuarioVentana.isEliminar()) modelo.anunciarCambio();
    }
}
