package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.AgregarLibro;

import com.odvp.biblioteca.ControladoresVistas.IOperacion;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;

/*
    crea la ventana para agregar un libro
 */

public class AgregarLibro{
    private LibroDAO libroDAO = new LibroDAO();

    public AgregarLibro(){
        AgregarLibroVentana agregarLibro = new AgregarLibroVentana();
    }
}
