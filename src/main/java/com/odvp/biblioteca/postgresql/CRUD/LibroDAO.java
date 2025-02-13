package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroAutor.Autor;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.postgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class LibroDAO implements ICRUD {
private  String qry;
private Libro libro;
private ConexionDB conexionDB;
private CategoriaDAO categoriaDAO;
private AutorDAO autorDAO;

    public LibroDAO(Libro libro, ConexionDB conexionDB){
        this.libro = libro;
        this.conexionDB = conexionDB;
    }

    @Override
    public void insertar() {
         qry = "call agregar_libro(?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getObservacion());
            stmt.setDate(3, libro.getPublicacion());
            stmt.setInt(4, libro.getStock());
            stmt.setInt(5, libro.getIdAutor());
            stmt.setInt(6,libro.getIdCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + libro.getTitulo());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Object buscar() {
        return null;
    }

    @Override
    public void modificar() {

    }

    @Override
    public void eliminar() {

    }


}
