package com.odvp.biblioteca.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static ConexionDB instancia;
    private final String url = "jdbc:postgresql://10.5.200.209:5432/biblioteca";
    //  private static final Logger logger = LogManager.getRootLogger();
    private final String user = "biblioteca";
    private final String password = "biblioteca";
    private Connection conexion;
    public static ConexionDB getOrCreate() throws SQLException {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public ConexionDB() throws SQLException {
        conexion = DriverManager.getConnection(url , user, password);
        System.out.println("Database connected!");
    }

    public void desconectar() throws SQLException {
        conexion.close();
        //logger.info("Database disconnected!");
    }

    public Connection getConexion() {
        return conexion;
    }
}
