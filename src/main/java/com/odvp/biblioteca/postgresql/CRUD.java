package com.odvp.biblioteca.postgresql;

import com.odvp.biblioteca.FuncionesMaestros.MaestroAutor.Autor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Clase para realizar las consultas y operaciones en la: crear, leer, actualizar, eliminar
 */
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

//Crear
public void insertarAutor(Autor autor){
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



//Leer
}
