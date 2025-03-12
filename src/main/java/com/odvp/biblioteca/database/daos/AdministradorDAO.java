package com.odvp.biblioteca.database.daos;

import com.odvp.biblioteca.objetos.Administrador;
import com.odvp.biblioteca.database.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
  Clase para realizar la interaccion con la base de datas, para la tabla administrador.
   */
public class AdministradorDAO {


    public Administrador obtener(int id) {
        return null;
    }

    public void modificar(Administrador administrador) {

    }

    public void eliminar(int id) {

    }

    public void insertar(Administrador administrador) {
        String qry = "CALL agregar_administrador(?,?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1,((Administrador) administrador).getNombre());
            stmt.setString(2, ((Administrador) administrador).getContrasena());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + ((Administrador) administrador).getNombre());

        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());

        }

    }
}
