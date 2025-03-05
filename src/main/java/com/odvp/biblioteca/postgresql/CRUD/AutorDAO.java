package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Autor;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
Clase para realizar las consultas y operaciones en Autor: crear, leer, actualizar, eliminar
 */

public class AutorDAO {
    private String qry;
    private Autor autor;
    private static ConexionDB conexionDB;
    private static AutorDAO instance;

    private AutorDAO() {
        conexionDB = ConexionDB.getOrCreate();
    }

    public static AutorDAO getInstance(){
        if(instance == null){
            instance = new AutorDAO();
        }
        return instance;
    }

    // Insertar un nuevo auto
    public void insertar(Object autor) {
        this.autor = (Autor) autor;
        qry = "Call agregar_autor(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, this.autor.getNombre());
            stmt.setString(2, this.autor.getDescripcion());
            stmt.execute();
        } catch (SQLException e) {
            if (e.getSQLState().equals("P0001")) { // Código SQL para una excepción RAISE EXCEPTION
                System.out.println("Error: " + e.getMessage());
            } else {
                System.out.println("Error mientras se ejecutaba el procedimiento: " + e.getMessage());
            }
        }
    }

    // Devolver el autor que se busca

    public Object obtener(int id) {
        String qry = "SELECT id_autor, nombre, biografia FROM autor WHERE id_autor = ?";
        Autor autor = null;

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setInt(1, id);
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

    // Función para modificar autor, pasándole el mismo id pero con los datos modificados

    public void modificar(Object autor) {
        this.autor = (Autor) autor;
        qry = "UPDATE autor SET nombre = ?, biografia = ? WHERE id_autor = ?";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, this.autor.getNombre());
            stmt.setString(2, this.autor.getDescripcion());
            stmt.setInt(3, this.autor.getID());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Actualizado");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void eliminar(int id) {
        // Implementar lógica de eliminación si es necesario
    }

    // Búsqueda del id del autor para búsquedas más rápidas
    public int getIdAutor(String nombre) {
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

    // Lista de todos los autores
    public ArrayList<Autor> obtenerAutoresAlfabeticamente() {
        String qry = "SELECT id_autor, nombre, resena FROM autor ORDER BY nombre";
        ArrayList<Autor> autores = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idAutor = rs.getInt("id_autor");
                String nombre = rs.getString("nombre");
                String biografia = rs.getString("resena");

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