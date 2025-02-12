package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroAutor.Autor;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.postgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CRUD {
private ConexionDB conexionDB;
private String sql;
public CRUD(){
    try {
        conexionDB = new ConexionDB();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}






//Leer
}
