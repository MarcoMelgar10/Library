package com.odvp.biblioteca.database.daos;

import com.odvp.biblioteca.objetos.Reserva;
import com.odvp.biblioteca.database.ConexionDB;
import com.odvp.biblioteca.objetosVisuales.IDatoVisual;
import com.odvp.biblioteca.objetosVisuales.ReservaCardData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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


    public ArrayList<IDatoVisual> listaReservasIVisual() {
        String qry = "SELECT r.id_reserva, r.fecha_reserva, r.estado, " +
                "u.nombre, u.apellido_paterno, u.apellido_materno, l.titulo " +
                "FROM reserva r " +
                "JOIN usuario u ON u.id_usuario = r.id_usuario " +
                "JOIN libro l on l.id_libro = r.id_libro WHERE r.D_E_L_E_T_E = FALSE " +
                "ORDER BY  r.estado desc, r.id_reserva asc";
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
                "ORDER BY r.estado desc, r.id_reserva asc";
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

    public List<IDatoVisual> listaReservasVisual(String textoBusqueda) {
        // Consulta SQL mejorada con manejo adecuado de búsqueda y condiciones
        String qry = """
                SELECT r.id_reserva, r.fecha_reserva, r.estado, 
                u.nombre, u.apellido_paterno, u.apellido_materno, l.titulo 
                FROM reserva r 
                JOIN usuario u ON u.id_usuario = r.id_usuario 
                JOIN libro l ON l.id_libro = r.id_libro 
                WHERE r.D_E_L_E_T_E = FALSE 
                AND (
                    unaccent(CONCAT(u.nombre, ' ', u.apellido_paterno, ' ', u.apellido_materno)) ILIKE unaccent(?)
                    OR unaccent(l.titulo) ILIKE unaccent(?)
                    OR CAST(r.id_reserva AS TEXT) LIKE ?
                )
                ORDER BY r.estado desc, r.id_reserva ASC
            """;

        List<IDatoVisual> reservas = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {

            String filtro = "%" + textoBusqueda + "%";

            // Establecer parámetros de búsqueda
            stmt.setString(1, filtro); // Nombre completo del usuario
            stmt.setString(2, filtro); // Título del libro
            stmt.setString(3, filtro); // ID de reserva (como texto)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Construir objeto ReservaCardData de forma más eficiente
                    reservas.add(new ReservaCardData(
                            rs.getInt("id_reserva"),
                            String.format("%s %s %s",
                                    rs.getString("nombre"),
                                    rs.getString("apellido_paterno"),
                                    rs.getString("apellido_materno")),
                            rs.getString("titulo"),
                            new Date(rs.getDate("fecha_reserva").getTime()),
                            rs.getString("estado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return reservas;
    }


    public void eliminar(int id) {
        String qry = "UPDATE reserva SET D_E_L_E_T_E = TRUE WHERE id_multa = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement st = conn.prepareStatement(qry)) {
            st.setInt(1, id);
            st.execute();
            System.out.println("Reserva cancelada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public boolean actualizar(Reserva reserva) throws SQLException {
        String sql = """
            UPDATE reserva SET
                id_usuario = ?,
                id_libro = ?,
                fecha_reserva = ?,
                fecha_vencimiento = ?,
                fecha_recogida = ?,
                estado = ?,
                observacion = ?,
            WHERE id_reserva = ?
            """;

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer parámetros
            pstmt.setInt(1, reserva.getIdUsuario());
            pstmt.setInt(2, reserva.getIdLibro());
            pstmt.setDate(3, (Date) reserva.getFechaReserva());
            pstmt.setDate(4, (Date) reserva.getFechaVencimiento());
            pstmt.setDate(5, (Date) reserva.getFechaRecogida());
            pstmt.setString(6, reserva.getEstado());
            pstmt.setString(7, reserva.getObservacion());
            pstmt.setInt(8, reserva.getIdReserva());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public Reserva obtener(int idReserva) {
        String qry = """
        SELECT 
            id_reserva, id_libro, id_usuario, 
            fecha_reserva, fecha_vencimiento, 
            fecha_recogida, estado, observaciones
        FROM reserva 
        WHERE id_reserva = ? AND D_E_L_E_T_E = FALSE
        """;

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setInt(1, idReserva);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Usamos if porque esperamos solo una reserva
                    int idLibro = rs.getInt("id_libro");
                    int idUsuario = rs.getInt("id_usuario");
                    Date fechaReserva = rs.getDate("fecha_reserva");
                    Date fechaVencimiento = rs.getDate("fecha_vencimiento");
                    Date fechaRecogida = rs.getDate("fecha_recogida");
                    String estado = rs.getString("estado");
                    String observaciones = rs.getString("observaciones");

                    System.out.println("Reserva encontrada");
                    return new Reserva(
                            idReserva,
                            idLibro,
                            idUsuario,
                            fechaReserva,
                            fechaVencimiento,
                            fechaRecogida,
                            estado,
                            observaciones
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reserva: " + e.getMessage());
            System.out.println("Estado SQL: " + e.getSQLState());
        }

        System.out.println("No se encontró la reserva con ID: " + idReserva);
        return null;
    }
}
