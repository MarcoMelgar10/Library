package com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.EditarUsuario;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.*;

/*
    crea la ventana para editar un libro
 */

public class EditarUsuario {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    public EditarUsuario(ModeloUsuarios modelo){
        Usuario usuario = usuarioDAO.obtener(modelo.getUsuarioSeleccionado().getID());

        EditarUsuarioVentana editarLibro = new EditarUsuarioVentana(usuarioDAO,usuario);
        if(editarLibro.isHubieronCambios()) modelo.anunciarCambio();
    }


}
