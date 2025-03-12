package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.VisualizarUsuario;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModeloAutores;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.Autor;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;

/*
    crea la vista para visualizar libro
 */

public class VisualizarAutor {
    private final AutorDAO autorDAO = new AutorDAO();
    public VisualizarAutor(ModeloAutores modelo){
        Autor autor = autorDAO.obtener(modelo.getAutorSeleccionado().getID());
        VisualizarAutorVentana visualizarAutorVentana = new VisualizarAutorVentana(autor);
    }
}
