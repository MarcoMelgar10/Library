package com.odvp.biblioteca.postgresql.CRUD;


public interface ICRUD {
    void insertar();
    Object buscar(String nombre);
    void modificar();
    void eliminar();
}
