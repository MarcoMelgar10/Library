package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EditarUsuario;

import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;

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
