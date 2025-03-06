package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Reserva;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.util.ArrayList;



/*
  Clase para realizar la interaccion con la base de datas, para la tabla reserva.
   */
public class ReservaDAO{

    public void insertar(Reserva reserva) {
        String qry = "CALL agregar_reserva(?,?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
                PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2,reserva.getIdLibro());
            stmt.executeQuery();
            System.out.println("Reserva registrada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
    }

    public ArrayList<Reserva> listaReservas() {
        String qry = "SELECT id_reserva, fecha_reserva, estado, id_usuario, id_libro FROM reserva";
        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
                PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idReserva = rs.getInt("id_reserva");
                Date fechaReserva = rs.getDate("fecha_reserva"); // Se obtiene como java.sql.Date
                boolean estado = rs.getBoolean("estado");
                int idUsuario = rs.getInt("id_usuario");
                int idLibro = rs.getInt("id_libro");

                // Convertir java.sql.Date a java.util.Date
                Reserva reserva = new Reserva(idReserva, new Date(fechaReserva.getTime()), estado, idUsuario, idLibro);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }
}
