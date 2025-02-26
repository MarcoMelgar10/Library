package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Multa;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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

    public ArrayList<Multa> listaMultas() {
        String qry = "SELECT id_multa, descripcion, monto, fecha_multa, estado, fecha_cancelacion, id_usuario, id_prestamo FROM multa";
        ArrayList<Multa> multas = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idMulta = rs.getInt("id_multa");
                String descripcion = rs.getString("descripcion");
                int monto = rs.getInt("monto");
                Date fechaMulta = rs.getDate("fecha_multa");
                boolean estado = rs.getBoolean("estado");
                Date fechaEliminacion = rs.getDate("fecha_cancelacion");
                int idUsuario = rs.getInt("id_usuario");
                int idPrestamo = rs.getInt("id_prestamo");

                // Crear un objeto Multa y agregarlo a la lista
                Multa multa = new Multa(idMulta, descripcion, monto, fechaMulta, estado, fechaEliminacion, idUsuario, idPrestamo);
                multas.add(multa);
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
}
