package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.FuncionesMaestros.MaestroAutor.Autor;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
Clase para realizar las consultas y operaciones en Autor: crear, leer, actualizar, eliminar
 */

public class AutorDAO implements ICRUD{
private String qry;
private Autor autor;
private ConexionDB conexionDB;
public AutorDAO(Autor autor, ConexionDB conexionDB){
    this.autor = autor;
   this.conexionDB = conexionDB;
}


//Insertar un nuevo autor
    @Override
    public void insertar() {
        qry = "Call agregar_autor(?,?)";
        try (PreparedStatement stmt =conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getDescripcion());
            stmt.execute();
            //  logger.info("Informacion cargada a la base de datos",evento.getNombre());
        } catch (SQLException e) {
            // Manejo de error cuando el correo ya existe o cualquier otra excepción
            if (e.getSQLState().equals("P0001")) { // Código SQL para una excepción RAISE EXCEPTION
                System.out.println("Error: {}"+ e.getMessage());
            } else {
                //   logger.error("Error mientras se ejecutaba el procedimiento: {}", e.getMessage());
            }
        }
    }

    //Devolver el autor que se busca

    @Override
    public Object buscar(String nombre) {
        String qry = "SELECT id_autor, nombre, biografia FROM autor WHERE nombre = ?";
        Autor autor = null;

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idAutor = rs.getInt("id_autor");
                    String nombreAutor = rs.getString("nombre");
                    String biografia = rs.getString("biografia");

                    autor = new Autor(idAutor, nombreAutor, biografia);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }

        return autor;
    }



    //Funcion para modificar autor, pasandole el mismo id pero con los datos modificados
    @Override
    public void modificar() {
    qry = "UPDATE autor SET nombre = ?, biografia = ? WHERE id_autor = ?";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getDescripcion());
            stmt.setInt(3, autor.getID());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Actualizado");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void eliminar() {

    }

    //Busqueda del id del autor para busquedas mas rapidas
    public int getIdAutor(String nombre){
        int id = -1; // Valor por defecto en caso de error
        qry = "SELECT buscar_autor(?)";

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }

        return id;
    }

    //Lista de todos los autores
    public ArrayList<Autor> obtenerTodosAutores() {
        String qry = "SELECT id_autor, nombre, biografia FROM autor";
        ArrayList<Autor> autores = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idAutor = rs.getInt("id_autor");
                String nombre = rs.getString("nombre");
                String biografia = rs.getString("biografia");

                Autor autor = new Autor(idAutor, nombre, biografia);
                autores.add(autor);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
        return autores;
    }
}
