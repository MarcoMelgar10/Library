package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroPrestamo.Prestamo;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
/*
  Clase para realizar la interaccion con la base de datas, para la tabla prestamo.
   */
public class PrestamoDAO implements  ICRUD{
    private String qry;
    private Prestamo prestamo;
    private ConexionDB conexionDB;
    public PrestamoDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
    public void insertar(Object prestamo) {
        this.prestamo = (Prestamo) prestamo;

    }

    @Override
    public Object visualizar(String nombre) {
        return null;
    }

    @Override
    public void modificar(Object prestamo) {
        this.prestamo = (Prestamo) prestamo;

    }

    @Override
    public void eliminar(int id) {

    }
    public ArrayList<Prestamo> listaPrestamos() {
        qry = "SELECT p.fecha_prestamo, p.fecha_vencimiento, p.fecha_devolucion, u.nombre AS usuario, l.titulo AS libro, p.estado " +
                "FROM prestamo p " +
                "JOIN usuario u ON p.id_usuario = u.id_usuario " +
                "JOIN libro l ON p.id_libro = l.id_libro";
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Date fechaPrestamo = rs.getDate("fecha_prestamo");
                Date fechaVencimiento = rs.getDate("fecha_vencimiento");
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                String usuario = rs.getString("usuario");
                String libro = rs.getString("libro");
                String estado = rs.getString("estado");

                // Crear un nuevo objeto Prestamo y agregarlo a la lista
                Prestamo prestamo = new Prestamo(fechaPrestamo, fechaVencimiento, fechaDevolucion, usuario, libro, estado);
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamos;
    }

}
