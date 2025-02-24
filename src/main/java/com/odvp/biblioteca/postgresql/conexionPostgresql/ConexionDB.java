package com.odvp.biblioteca.postgresql.conexionPostgresql;

import javafx.application.Platform;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    private static ConexionDB instancia;
    private final String url = "jdbc:postgresql://ep-jolly-lab-a8wz5ags-pooler.eastus2.azure.neon.tech:5432/neondb";
    //  private static final Logger logger = LogManager.getRootLogger();
    private final String user = "neondb_owner";
    private final String password = "npg_XgQ6PsVu3OtR";
    private Connection conexion;

    public static ConexionDB getOrCreate(){
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    private ConexionDB(){
        try{
            conexion = DriverManager.getConnection(url , user, password);
            System.out.println("Database connected!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,
                    "Error con la Base de datos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    public void desconectar() throws SQLException {
        conexion.close();
        //logger.info("Database disconnected!");
    }

    public Connection getConexion() {
        return conexion;
    }
}
