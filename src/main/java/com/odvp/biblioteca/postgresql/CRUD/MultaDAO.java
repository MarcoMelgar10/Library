package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Multa;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.ObjetosVistas.MultaCardData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla multaCardData.
   */
public class MultaDAO {

    public void insertar(Multa multa) {
        String qry = "CALL agregar_multa(?,?,?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, multa.getDescripcion());
            stmt.setInt(2, multa.getMonto());
            stmt.setInt(3, multa.getIdPrestamo());
            stmt.execute();
            System.out.println("multa agregada");
        } catch (SQLException e) {
            // Manejo de errores
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }

    }

    /*
    estado = false, significa "Cancelada", al contrario "Activa"
     */
    public void modificar(Multa multa) {
        String qry = "UPDATE multa SET descripcion = ?, monto =?, fecha_multa= ?, estado = ? WHERE id_multa = ? AND D_E_L_E_T_E = FALSE";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement ps = conn.prepareStatement(qry)) {
            ps.setString(1, multa.getDescripcion());
            ps.setInt(2, multa.getMonto());
            ps.setDate(3, multa.getFechaMulta());
            ps.setBoolean(4, multa.isEstado());
            ps.setInt(5, multa.getIdMulta());
            ps.execute();
            System.out.println("Multa actualizada: " + multa.getIdMulta());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Multa obtener(int id) {
        String qry = "SELECT * FROM MULTA WHERE id_multa = ?";

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement ps = conn.prepareStatement(qry)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Usa if en lugar de while, porque esperamos solo una multaCardData
                    String descripcion = rs.getString("descripcion");
                    int monto = rs.getInt("monto");
                    Date fechaMulta = rs.getDate("fecha_multa");
                    boolean estado = rs.getBoolean("estado");
                    Date fechaCancelacion = rs.getDate("fecha_cancelacion");
                    boolean D_E_L_E_T_E = rs.getBoolean("D_E_L_E_T_E"); // Mejor cambiar el nombre en la BD
                    int idPrestamo = rs.getInt("id_prestamo");

                    System.out.println("Multa encontrada");
                    return new Multa(id, descripcion, monto, fechaMulta, estado, fechaCancelacion, D_E_L_E_T_E, idPrestamo);

                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
        }

        System.out.println("No se encontró la multaCardData con ID: " + id);
        return null;
    }

    public void eliminar(int id) {
        String qry = "UPDATE multa SET D_E_L_E_T_E = TRUE WHERE id_multa = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement st = conn.prepareStatement(qry)) {
            st.setInt(1, id);
            st.execute();
            System.out.println("Multa cancelada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


    }


    public ArrayList<MultaCardData> listaMultas() {
        String qry = """
                    SELECT m.id_multa, m.descripcion, m.monto, m.fecha_multa, m.estado, 
                           m.fecha_cancelacion, m.id_prestamo, u.nombre, u.apellido_paterno, u.apellido_materno
                    FROM multa m 
                    JOIN prestamo p ON p.id_prestamo = m.id_prestamo
                    JOIN usuario u ON p.id_usuario = u.id_usuario 
                    WHERE  m.D_E_L_E_T_E = FALSE
                    ORDER BY m.estado DESC, m.id_multa ASC
                """;

        ArrayList<MultaCardData> multas = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idMulta = rs.getInt("id_multa");
                int idPrestamo = rs.getInt("id_prestamo");
                int monto = rs.getInt("monto");
                Date fechaMulta = rs.getDate("fecha_multa");
                boolean estado = rs.getBoolean("estado");
                String nombreUsuario = rs.getString("nombre"); // Datos de usuario
                nombreUsuario += " ";
                nombreUsuario += rs.getString("apellido_paterno");
                nombreUsuario += " ";
                nombreUsuario += rs.getString("apellido_materno");

                // Crear objeto DTO
                MultaCardData multaCardData = new MultaCardData(idMulta,nombreUsuario, monto, fechaMulta, estado, idPrestamo);
                multas.add(multaCardData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()
            + " " + e.getSQLState());
        }
        return multas;
    }


    //Ejecutar en la linea principal del programa, para que cada vez que se inicie se actualicen los prestamos acorde a las fechas limites
    public void validarPrestamosVencidos() {
        String qry = "SELECT actualizar_prestamos_y_multas()";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.execute();
            System.out.println("Estado del prestamo y multas actualizado");
        } catch (SQLException e) {
            // Manejo de errores
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<IDatoVisual> listaMultasVisual() {
        String qry = """
                    SELECT m.id_multa, m.monto, m.fecha_multa, m.estado, m.id_prestamo, 
                    u.nombre, u.apellido_materno, u.apellido_paterno
                    FROM multa m 
                    JOIN prestamo p on p.id_prestamo = m.id_prestamo
                    JOIN usuario u ON p.id_usuario = u.id_usuario 
                    WHERE m.D_E_L_E_T_E = FALSE
                    ORDER BY m.estado DESC, m.id_multa ASC
                """;

        ArrayList<IDatoVisual> multas = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idMulta = rs.getInt("id_multa");
                int idPrestamo = rs.getInt("id_prestamo");
                int monto = rs.getInt("monto");
                boolean estado = rs.getBoolean("estado");
                Date fechaMulta = rs.getDate("fecha_multa");
                String nombreUsuario = rs.getString("nombre");
                nombreUsuario += " ";
                nombreUsuario += rs.getString("apellido_paterno");
                nombreUsuario += " ";
                nombreUsuario += rs.getString("apellido_materno");
                System.out.println("Se ejecuta");


                MultaCardData multaCardData = new MultaCardData(idMulta, nombreUsuario, monto, fechaMulta, estado, idPrestamo);

                multas.add(multaCardData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return multas;

    }

    public int getNextId() {
        String qry = "Select MAX(id_multa) from multa";
        int idMulta = 0;
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                idMulta = rs.getInt(1);
                idMulta += 1;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMulta;
    }

    public void cancelarMulta(int idMulta) {
        String qry = "UPDATE multa SET estado = false, fecha_cancelacion = ? WHERE id_multa = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement st = conn.prepareStatement(qry)) {
            st.setDate(1, Date.valueOf(LocalDate.now()));
            st.setInt(2, idMulta);
            st.execute();
            System.out.println("Multa cancelada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public List<IDatoVisual> listaMultasVisual(String textoBusqueda) {
        String qry = """
            SELECT m.id_multa, m.monto, m.fecha_multa, m.estado, m.id_prestamo,
                   u.apellido_paterno, u.apellido_materno, u.nombre 
            FROM multa m 
            JOIN prestamo p ON p.id_prestamo = m.id_prestamo
            JOIN usuario u ON p.id_usuario = u.id_usuario 
            WHERE unaccent(CONCAT(u.nombre, ' ', u.apellido_paterno, ' ', u.apellido_materno)) ILIKE unaccent(?)
               OR unaccent(CONCAT(u.apellido_paterno, ' ', u.apellido_materno)) ILIKE unaccent(?) AND m.D_E_L_E_T_E = FALSE
            ORDER BY m.estado DESC, m.id_multa ASC
        """;

        ArrayList<IDatoVisual> multas = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {

            String filtro = "%" + textoBusqueda + "%"; // Permitir coincidencias parciales

            stmt.setString(1, filtro); // Buscar por nombre completo sin tildes
            stmt.setString(2, filtro); // Buscar por apellidos sin tildes

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idMulta = rs.getInt("id_multa");
                    int idPrestamo = rs.getInt("id_prestamo");
                    int monto = rs.getInt("monto");
                    boolean estado = rs.getBoolean("estado");
                    Date fechaMulta = rs.getDate("fecha_multa");

                    // Concatenar nombre y apellidos
                    String nombreUsuario = rs.getString("nombre") + " " +
                            rs.getString("apellido_paterno") + " " +
                            rs.getString("apellido_materno");

                    MultaCardData multaCardData = new MultaCardData(idMulta, nombreUsuario, monto, fechaMulta, estado, idPrestamo);
                    multas.add(multaCardData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return multas;
    }




}
