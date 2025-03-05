package com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.AgregarUsuario;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.*;

/*
    crea la ventana para agregar un libro
 */

public class AgregarUsuario {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public AgregarUsuario(ModeloUsuarios modelo){
        Usuario usuario = usuarioDAO.obtener(modelo.getUsuarioSeleccionado().getID());
        //AgregarUsuarioVentana agregarLibro = new AgregarUsuarioVentana(,usuarioDAO);
        //if(agregarLibro.isHubieronCambios()) modelo.anunciarCambio();
    }
}
