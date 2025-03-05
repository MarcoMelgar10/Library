package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EditarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;
import com.odvp.biblioteca.postgresql.CRUD.CategoriaDAO;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import com.odvp.biblioteca.postgresql.CRUD.SubCategoriaDAO;

/*
    crea la ventana para editar un libro
 */

public class EditarLibro {
    private final LibroDAO libroDAO = new LibroDAO();
    private final AutorDAO autorDAO = AutorDAO.getInstance();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final SubCategoriaDAO subCategoriaDAO =  new SubCategoriaDAO();
    public EditarLibro(ModeloLibros modelo){

        Libro libro = libroDAO.obtener(modelo.getLibroSeleccionado().getID());
        EditarLibroVentana editarLibro = new EditarLibroVentana(libro,libroDAO ,autorDAO, categoriaDAO, subCategoriaDAO);
        if(editarLibro.isHubieronCambios()) modelo.anunciarCambio();
    }


}
