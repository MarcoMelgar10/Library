package com.odvp.biblioteca.database.daos;

import com.odvp.biblioteca.objetos.Reserva;
import com.odvp.biblioteca.database.ConexionDB;
import com.odvp.biblioteca.objetosVisuales.IDatoVisual;
import com.odvp.biblioteca.objetosVisuales.ReservaCardData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla reserva.
   */
public class ReservaDAO{
    private static Logger logger = LogManager.getRootLogger();
    public boolean insertar(Reserva reserva) {
        String qry = "CALL agregar_reserva(?, ?, ?)";

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             CallableStatement stmt = conn.prepareCall(qry)) {

            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getIdLibro());
            stmt.setString(3, reserva.getObservacion());

            stmt.execute();

            logger.info("Reserva registrada exitosamente para usuario ID: {}, libro ID: {}",
                    reserva.getIdUsuario(), reserva.getIdLibro());
            return true;

        } catch (SQLException e) {
            // Handle specific SQL states
            if ("23505".equals(e.getSQLState())) {
                logger.error("Error de duplicado al insertar reserva: {}", e.getMessage());
            } else if ("P0001".equals(e.getSQLState())) {
                // This is the state for RAISE EXCEPTION in PostgreSQL
                logger.error("Error de negocio al insertar reserva: {}", e.getMessage());
            } else {
                logger.error("Error SQL al insertar reserva. Estado: {}, Mensaje: {}",
                        e.getSQLState(), e.getMessage(), e);
            }
            return false;
        }
    }

    public void elimnar(int id){
        String qry = "UPDATE reserva set D_E_L_E_T_E = true WHER id_reserva = ? ";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            stmt.executeQuery();
            System.out.println("Reserva eliminada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }
    }
    public ArrayList<IDatoVisual> listaReservasIVisual() {
        String qry = "SELECT r.id_reserva, r.fecha_reserva, r.estado, " +
                "u.nombre, u.apellido_paterno, u.apellido_materno, l.titulo " +
                "FROM reserva r " +
                "JOIN usuario u ON u.id_usuario = r.id_usuario " +
                "JOIN libro l on l.id_libro = r.id_libro WHERE r.D_E_L_E_T_E = FALSE " +
                "ORDER BY r.id_reserva asc";
        ArrayList<IDatoVisual> reservas = new ArrayList<>();
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

    public Reserva visualizar(int id) {
        String qry = "SELECT * FROM reserva WHERE id_reserva = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Use `if` instead of `while` since it's a single record
                int idReserva = rs.getInt("id_reserva");
                int idLibro = rs.getInt("id_libro");
                int idUsuario = rs.getInt("id_usuario");
                Timestamp fechaVencimiento = rs.getTimestamp("fecha_vencimiento");
                String estado = rs.getString("estado");
                Timestamp fechaReserva = rs.getTimestamp("fecha_reserva");
                Timestamp fechaRecogida = rs.getTimestamp("fecha_recogida");
                String observaciones = rs.getString("observaciones");

                // Create and return a Reserva object with all attributes
                return new Reserva(idReserva, idLibro, idUsuario, fechaReserva, fechaVencimiento,fechaRecogida,
                        estado, observaciones);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if not found or in case of an error
    }


    public int getNextId() {
        String qry = "SELECT MAX(id_reserva) FROM reserva";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            int nextId = 1;
            if (rs.next()) {  // Move cursor to first row
                nextId = rs.getInt(1) + 1;
            }
            return nextId;

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return -1;
    }
}
