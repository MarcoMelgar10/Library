package com.odvp.biblioteca.database.daos;

import com.odvp.biblioteca.objetos.Log;
import com.odvp.biblioteca.database.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
  Clase para realizar la interaccion con la base de datas, para la tabla libro.
   */
public class LogDAO {
    private ConexionDB conexionDB;
    private String qry;
    private Log log;
    public LogDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }

    public void insertar(Object log) {
        this.log = (Log) log;
        qry = "INSERT INTO LOGS (tipo, descripcion, fecha) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, ((Log) log).getTipo());
            stmt.setString(2, ((Log) log).getDescripcion());
            stmt.setDate(3, ((Log) log).getFecha());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + ((Log) log).getTipo());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }


    public Object obtener(int id) {
        return null;
    }


    public void modificar(Object log) {
        this.log = (Log) log;
    }


    public void eliminar(int id) {

    }
}
