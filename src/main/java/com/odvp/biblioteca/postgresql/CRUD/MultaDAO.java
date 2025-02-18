package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroMulta.Multa;
import com.odvp.biblioteca.postgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MultaDAO implements ICRUD{
    private Multa multa;
    private ConexionDB conexionDB;
    private String qry;
    public MultaDAO(Multa multa, ConexionDB conexionDB){
        this.multa = multa;
        this.conexionDB = conexionDB;
    }
    @Override
    public void insertar() {
        qry = "CALL agregar_multa(?)";

    }

    @Override
    public Object buscar() {
        return null;
    }

    @Override
    public void modificar() {

    }

    @Override
    public void eliminar() {

    }


    //Ejecutar en la linea principal del programa, para que cada vez que se inicie se actualicen los prestamos acorde a las fechas limites
    public void validarPrestamosVencidos(){
        qry = "SELECT verificar_prestamos_vencidos();";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.execute();
            System.out.println("Estado del prestamo actualizado");
        } catch (SQLException e) {
            // Manejo de errores
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }
}
