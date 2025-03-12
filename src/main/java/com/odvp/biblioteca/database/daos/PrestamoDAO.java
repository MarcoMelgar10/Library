package com.odvp.biblioteca.database.daos;

import com.odvp.biblioteca.objetos.Prestamo;
import com.odvp.biblioteca.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
/*
  Clase para realizar la interaccion con la base de datas, para la tabla prestamo.
   */
public class PrestamoDAO{



    public void eliminar(int id) {

    }
    public ArrayList<Prestamo> listaPrestamos() {
        String qry = "SELECT p.fecha_prestamo, p.fecha_vencimiento, p.fecha_devolucion, u.nombre AS usuario, l.titulo AS libro, p.estado " +
                "FROM prestamo p " +
                "JOIN usuario u ON p.id_usuario = u.id_usuario " +
                "JOIN libro l ON p.id_libro = l.id_libro";
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement ps = conn.prepareStatement(qry);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Date fechaPrestamo = rs.getDate("fecha_prestamo");
                Date fechaVencimiento = rs.getDate("fecha_vencimiento");
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                String usuario = rs.getString("usuario");
                String libro = rs.getString("libro");
                String estado = rs.getString("estado");

                // Crear un nuevo objeto Prestamo y agregarlo a la lista
             //   Prestamo prestamo = new Prestamo(fechaPrestamo, fechaVencimiento, fechaDevolucion, usuario, libro, estado);
             //   prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamos;
    }

    public String getLibro(int codigoPrestamo) {
        String qry = "SELECT l.titulo FROM prestamo p JOIN libro l on l.id_libro = p.id_libro WHERE id_prestamo = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, codigoPrestamo); // Mover esto antes de ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIdUsuario(int idPrestamo) {
        String qry = "SELECT id_usuario FROM prestamo WHERE id_prestamo = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, idPrestamo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getUsuario(int codigoPrestamo) {
        String qry = "SELECT u.nombre FROM prestamo p JOIN usuario u on u.id_usuario = p.id_usuario WHERE id_prestamo = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, codigoPrestamo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
