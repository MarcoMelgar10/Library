package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Log;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
  Clase para realizar la interaccion con la base de datas, para la tabla libro.
   */
public class LogDAO implements ICRUD{
    private ConexionDB conexionDB;
    private String qry;
    private Log log;
    public LogDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
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

    @Override
    public Object visualizar(int id) {
        return null;
    }

    @Override
    public void modificar(Object log) {
        this.log = (Log) log;
    }

    @Override
    public void eliminar(int id) {

    }
}
