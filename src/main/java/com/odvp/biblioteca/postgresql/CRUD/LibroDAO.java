package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.postgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


public class LibroDAO implements ICRUD {
private  String sql;
private Libro libro;
private ConexionDB conexionDB;

    public LibroDAO(Libro libro){
        this.libro = libro;
        try{
            conexionDB = new ConexionDB();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



    @Override
    public void insertar() {
        sql = "Call agregar_libro(?, ?, ?, ?, ?, ?)";
        Date date = Date.from(Instant.from(libro.getPublicacion()));
        try (PreparedStatement stmt =conexionDB.getConexion().prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getObservacion());

            stmt.setDate(3, (java.sql.Date) date);
            stmt.setInt(4, libro.getStock());
            stmt.setString(5, libro.getTitulo());
            stmt.setString(6, libro.getObservacion());

            stmt.execute();
            System.out.println("Informacion cargada a la base de datos "+ libro.getTitulo());
        } catch (SQLException e) {
            // Manejo de error cuando el correo ya existe o cualquier otra excepción
            if (e.getSQLState().equals("P0001")) { // Código SQL para una excepción RAISE EXCEPTION
                //      logger.warn("Error: {}", e.getMessage());
            } else {
                //   logger.error("Error mientras se ejecutaba el procedimiento: {}", e.getMessage());
            }
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
