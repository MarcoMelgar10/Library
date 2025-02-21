package com.odvp.biblioteca.postgresql.CRUD;


public interface ICRUD <A> {
    void insertar(A objeto);
    A visualizar(String nombre);
    void modificar(A objeto);
    void eliminar(int id);
}
