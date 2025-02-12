package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.FuncionesMaestros.MaestroAutor.Autor;
import com.odvp.biblioteca.postgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Clase para realizar las consultas y operaciones en Autor: crear, leer, actualizar, eliminar
 */
public class AutorDAO implements ICRUD{
private String sql;
private Autor autor;
private ConexionDB conexionDB;
public AutorDAO(Autor autor){
    this.autor = autor;
    try{
        conexionDB = new ConexionDB();
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
}
    @Override
    public void insertar() {
        sql = "Call agregar_autor(?,?)";
        try (PreparedStatement stmt =conexionDB.getConexion().prepareStatement(sql)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getDescripcion());
            stmt.execute();
            //  logger.info("Informacion cargada a la base de datos",evento.getNombre());
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
