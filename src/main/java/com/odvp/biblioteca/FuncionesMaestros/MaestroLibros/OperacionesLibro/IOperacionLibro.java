package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;

/*
    define las funciones que toda operacion que se haga sobre un libro debe tener
 */

public interface IOperacionLibro {
    void buildWindow();
    Libro getLibroData();
}
