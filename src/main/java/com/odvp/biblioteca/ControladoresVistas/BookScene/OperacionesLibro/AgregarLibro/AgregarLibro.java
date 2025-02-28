package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.AgregarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.IOperacion;
import com.odvp.biblioteca.Objetos.IDatoVisual;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.postgresql.CRUD.AutorDAO;
import com.odvp.biblioteca.postgresql.CRUD.CategoriaDAO;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import com.odvp.biblioteca.postgresql.CRUD.SubCategoriaDAO;

import java.util.List;

/*
    crea la ventana para agregar un libro
 */

public class AgregarLibro{
    private LibroDAO libroDAO = new LibroDAO();
    private AutorDAO autorDAO = AutorDAO.getInstance();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private SubCategoriaDAO subCategoriaDAO = new SubCategoriaDAO();

    public AgregarLibro(ModeloLibros modelo){
        AgregarLibroVentana agregarLibro = new AgregarLibroVentana(libroDAO, autorDAO, categoriaDAO, subCategoriaDAO);
        if(agregarLibro.isHubieronCambios()) modelo.setLibrosMostrados(libroDAO.listaLibrosVisual());
    }
}
