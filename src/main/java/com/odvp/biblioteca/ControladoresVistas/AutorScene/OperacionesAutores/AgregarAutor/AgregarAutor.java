package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.AgregarAutor;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModeloAutores;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;

/*
    crea la ventana para agregar un libro
 */

public class AgregarAutor {
    private AutorDAO autorDAO = new AutorDAO();
    public AgregarAutor(ModeloAutores modelo){
        AgregarAutorVentana agregarLibro = new AgregarAutorVentana(autorDAO);
        if(agregarLibro.isHubieronCambios()) modelo.anunciarCambio();
    }
}
