package com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EliminarLibro;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;

/*
    crea la ventana de eliminar libro
 */

public class EliminarLibro {
    private final LibroDAO libroDAO = new LibroDAO();
    public EliminarLibro(ModeloLibros modeloLibros){
        EliminarLibroVentana eliminarLibroVentana = new EliminarLibroVentana(modeloLibros.getLibroSeleccionado().getID(), libroDAO);
        if(eliminarLibroVentana.isEliminar()) modeloLibros.setLibrosMostrados(libroDAO.listaLibrosVisual());
    }
}
