package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EditarLibro;

import com.odvp.biblioteca.ControladoresVistas.IOperacion;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;
import com.odvp.biblioteca.postgresql.CRUD.CategoriaDAO;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import com.odvp.biblioteca.postgresql.CRUD.SubCategoriaDAO;

/*
    crea la ventana para editar un libro
 */

public class EditarLibro {
    private Libro libro;
    private final LibroDAO libroDAO = new LibroDAO();
    private final AutorDAO autorDAO =  new AutorDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();
    public EditarLibro(Integer libroID){
        this.libro = libroDAO.visualizar(libroID);
        EditarLibroVentana editarLibro = new EditarLibroVentana(libro,libroDAO ,autorDAO, categoriaDAO, subCategoriaDAO);
    }


}
