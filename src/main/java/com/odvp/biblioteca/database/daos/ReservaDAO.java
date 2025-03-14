package com.odvp.biblioteca.database.daos;

import com.odvp.biblioteca.objetos.Reserva;
import com.odvp.biblioteca.database.ConexionDB;
import com.odvp.biblioteca.objetosVisuales.ReservaCardData;

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

    public ArrayList<ReservaCardData> listaReservasVisual() {
        String qry = "SELECT r.id_reserva, r.fecha_reserva, r.estado, " +
                "u.nombre, u.apellido_paterno, u.apellido_materno, l.titulo " +
                "FROM reserva r " +
                "JOIN usuario u ON u.id_usuario = r.id_usuario " +
                "JOIN libro l on l.id_libro = r.id_libro WHERE r.D_E_L_E_T_E = FALSE " +
                "ORDER BY r.id_reserva asc";
        ArrayList<ReservaCardData> reservas = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
                PreparedStatement stmt = conn.prepareStatement(qry);
                 ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idReserva = rs.getInt("id_reserva");
                Date fechaReserva = rs.getDate("fecha_reserva"); // Se obtiene como java.sql.Date
                String estado = rs.getString("estado");
                String nombreCompletoUsuario = rs.getString("nombre");
                nombreCompletoUsuario += " ";
                nombreCompletoUsuario += rs.getString("apellido_paterno");
                nombreCompletoUsuario += " ";
                nombreCompletoUsuario += rs.getString("apellido_materno");
                String titulo = rs.getString("titulo");

                ReservaCardData reserva = new ReservaCardData(idReserva, nombreCompletoUsuario, titulo, new Date(fechaReserva.getTime()), estado);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }
}
