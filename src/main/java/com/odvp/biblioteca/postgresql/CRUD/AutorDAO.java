package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.Autor;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.ObjetosVistas.UsuarioData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Clase para realizar las consultas y operaciones en Autor: crear, leer, actualizar, eliminar
 */

public class AutorDAO {

    // Insertar un nuevo auto
    public void insertar(Autor autor) {
        String qry = "INSERT INTO autor(nombre,resena) VALUES (?,?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getDescripcion());
            stmt.execute();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    // Devolver el autor que se busca

    public Autor obtener(int id) {
        String qry = "SELECT id_autor, nombre, biografia FROM autor WHERE id_autor = ?";
        Autor autor = null;

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
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
            e.printStackTrace();
        }
        return autor;
    }

    // Función para modificar autor, pasándole el mismo id pero con los datos modificados

    public void modificar(Autor autor) {
        String  qry = "UPDATE autor SET nombre = ?, biografia = ? WHERE id_autor = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getDescripcion());
            stmt.setInt(3, autor.getID());
            stmt.execute();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        // Implementar lógica de eliminación si es necesario
    }



    public ArrayList<IDatoVisual> obtenerAutoresAlfabeticamente() {
        String qry = "SELECT id_autor, nombre, resena FROM autor WHERE D_E_L_E_T_E = false ORDER BY nombre";
        ArrayList<IDatoVisual> autores = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idAutor = rs.getInt("id_autor");
                String nombre = rs.getString("nombre");
                String biografia = rs.getString("resena");

                Autor autor = new Autor(idAutor, nombre, biografia);
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }


    public Integer getNextId(){
        String qry = "SELECT MAX(id_autor) AS max_autor from autor";
        int maxId;
        try(Connection conn = ConexionDB.getOrCreate().getConexion();
            PreparedStatement ps = conn.prepareStatement(qry);
            ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                maxId = rs.getInt("max_autor");
                return maxId +1;
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    public List<IDatoVisual> listaAutoresParametrizado(String textoBusqueda) {
        String qry = "SELECT id_autor, nombre, resena FROM autor WHERE D_E_L_E_T_E = false";
        ArrayList<IDatoVisual> autores = new ArrayList<>();

        if (textoBusqueda != null && !textoBusqueda.isEmpty()) {
            qry += " AND nombre ILIKE ?";
        }

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstm = conn.prepareStatement(qry)) {

            if (textoBusqueda != null && !textoBusqueda.isEmpty()) {
                String parametroBusqueda = "%" + textoBusqueda + "%";
                pstm.setString(1, parametroBusqueda);
            }

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    int idAutor = rs.getInt("id_autor");
                    String nombre = rs.getString("nombre");
                    String resena = rs.getString("resena");

                    Autor autor = new Autor(idAutor, nombre,resena);

                    autores.add(autor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}