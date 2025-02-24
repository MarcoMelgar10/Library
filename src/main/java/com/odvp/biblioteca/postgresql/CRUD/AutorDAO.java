package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.Objetos.Autor;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Clase para realizar las consultas y operaciones en Autor: crear, leer, actualizar, eliminar
 */

public class AutorDAO implements ICRUD{
private String qry;
private Autor autor;
private ConexionDB conexionDB;
public AutorDAO(Autor autor, ConexionDB conexionDB){
    this.autor = autor;
   this.conexionDB = conexionDB;
}

    @Override
    public void insertar() {
        qry = "Call agregar_autor(?,?)";
        try (PreparedStatement stmt =conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getDescripcion());
            stmt.execute();
            //  logger.info("Informacion cargada a la base de datos",evento.getNombre());
        } catch (SQLException e) {
            // Manejo de error cuando el correo ya existe o cualquier otra excepción
            if (e.getSQLState().equals("P0001")) { // Código SQL para una excepción RAISE EXCEPTION
                System.out.println("Error: {}"+ e.getMessage());
            } else {
                //   logger.error("Error mientras se ejecutaba el procedimiento: {}", e.getMessage());
            }
        }
    }

    @Override
    public Object buscar(String titulo) {
            return autor;
    }

    @Override
    public void modificar() {

    }

    @Override
    public void eliminar() {

    }

    public int getIdAutor(String nombre){
        int id = -1; // Valor por defecto en caso de error
         qry = "SELECT buscar_autor(?)";

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }

        return id;
    }
}
