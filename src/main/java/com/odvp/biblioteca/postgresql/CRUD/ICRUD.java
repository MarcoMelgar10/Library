package com.odvp.biblioteca.postgresql.CRUD;


public interface ICRUD <A> {
    void insertar(A objeto);
    A visualizar(int id);
    void modificar(A objeto);
    void eliminar(int id);
}
