package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EditarAutor;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModeloAutores;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.EditarUsuario.EditarUsuarioVentana;
import com.odvp.biblioteca.Objetos.Autor;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;

/*
    crea la ventana para editar un libro
 */

public class EditarAutor {
    private final AutorDAO autorDAO = new AutorDAO();
    public EditarAutor(ModeloAutores modelo){
        Autor autor = autorDAO.obtener(modelo.getAutorSeleccionado().getID());

        EditarAutorVentana editarAutorVentana = new EditarAutorVentana(autorDAO,autor);
        if(editarAutorVentana.isHubieronCambios()) modelo.anunciarCambio();
    }


}
