package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLog.Log;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDAO implements ICRUD{
    private ConexionDB conexionDB;
    private String qry;
    private Log log;
    public LogDAO(Log log, ConexionDB conexionDB){
        this.conexionDB = conexionDB;
        this.log = log;
    }
    @Override
    public void insertar() {
        qry = "INSERT INTO LOGS (tipo, descripcion, fecha) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, log.getTipo());
            stmt.setString(2, log.getDescripcion());
            stmt.setDate(3, log.getFecha());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + log.getTipo());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Object buscar(String nombre) {
        return null;
    }

    @Override
    public void modificar() {
    }

    @Override
    public void eliminar() {

    }
}
