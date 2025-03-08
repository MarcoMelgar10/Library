package com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.EliminarUsuario;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
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
