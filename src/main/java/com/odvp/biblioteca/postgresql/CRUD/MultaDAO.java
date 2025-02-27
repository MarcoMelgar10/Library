package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Multa;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.ObjetosVistas.MultaCardData;
import com.odvp.biblioteca.ObjetosVistas.MultaDTO;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla multa.
   */
public class MultaDAO implements ICRUD{
    private Multa multa;
    private ConexionDB conexionDB;
    private String qry;
    public MultaDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
    public void insertar(Object multa) {
        this.multa = (Multa) multa;
        qry = "CALL agregar_multa(?,?,?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, ((Multa) multa).getDescripcion());
            stmt.setInt(2, ((Multa) multa).getMonto());
            stmt.setInt(3, ((Multa) multa).getIdPrestamo());
            stmt.setInt(4, ((Multa) multa).getIdUsuario());
            stmt.execute();
            System.out.println("Prestamo agregato");
        } catch (SQLException e) {
            // Manejo de errores
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public Object visualizar(int id) {
        return null;
    }

    @Override
    public void modificar(Object multa) {
        this.multa = (Multa) multa;

    }

    @Override
    public void eliminar(int id) {

    }

    public ArrayList<MultaDTO> listaMultas() {
         qry = """
        SELECT m.id_multa, m.descripcion, m.monto, m.fecha_multa, m.estado, 
               m.fecha_cancelacion, m.id_prestamo, u.nombre 
        FROM multa m 
        JOIN usuario u ON m.id_usuario = u.id_usuario 
        WHERE m.estado = true
        ORDER BY m.id_multa ASC
    """;

        ArrayList<MultaDTO> multas = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idMulta = rs.getInt("id_multa");
                int idPrestamo = rs.getInt("id_prestamo");
                String descripcion = rs.getString("descripcion");
                int monto = rs.getInt("monto");
                Date fechaMulta = rs.getDate("fecha_multa");
                boolean estado = rs.getBoolean("estado");
                Date fechaEliminacion = rs.getDate("fecha_cancelacion");
                String nombreUsuario = rs.getString("nombre"); // Datos de usuario

                // Crear objeto DTO
                MultaDTO multaDTO = new MultaDTO(idMulta, descripcion, monto, fechaMulta, estado, fechaEliminacion, nombreUsuario, idPrestamo);
                multas.add(multaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return multas;
    }


    //Ejecutar en la linea principal del programa, para que cada vez que se inicie se actualicen los prestamos acorde a las fechas limites
    public void validarPrestamosVencidos(){
        qry = "SELECT actualizar_prestamos_y_multas()";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.execute();
            System.out.println("Estado del prestamo y multas actualizado");
        } catch (SQLException e) {
            // Manejo de errores
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int getNextId() {
        qry  = "SELECT MAX(id_multa) from multa ";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){
                int maxId = rs.getInt(1);
                return (maxId > 0) ? maxId + 1 : 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public List<IDatoVisual> listaMultasVisual() {
        qry = """
        SELECT m.id_multa, m.monto, m.fecha_multa, 
               m.id_prestamo, u.nombre 
        FROM multa m 
        JOIN usuario u ON m.id_usuario = u.id_usuario 
        WHERE m.estado = true
        ORDER BY m.id_multa ASC
    """;

        ArrayList<IDatoVisual> multas = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idMulta = rs.getInt("id_multa");
                int idPrestamo = rs.getInt("id_prestamo");
                int monto = rs.getInt("monto");
                Date fechaMulta = rs.getDate("fecha_multa");
                String nombreUsuario = rs.getString("nombre"); // Datos de usuario
                MultaCardData multaCardData = new MultaCardData(idMulta, nombreUsuario, monto, fechaMulta, idPrestamo);
                multas.add(multaCardData);
            }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return multas;
    }
}
