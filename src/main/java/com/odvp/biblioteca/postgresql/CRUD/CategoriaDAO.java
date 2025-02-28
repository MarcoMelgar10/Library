package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.CategoryData;
import com.odvp.biblioteca.postgresql.CRUD.ICRUD;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO{

    public void insertar(CategoryData categoryData) {
        String qry = "CALL agregar_categoria(?, ?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, categoryData.getNombre());
            stmt.setString(2, categoryData.getDescripcion());
            stmt.execute();
            System.out.println("Categoría agregada: " + categoryData.getNombre());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CategoryData obtener(int id) {
        String sql = "SELECT id_categoria, nombre, descripcion FROM categoria WHERE id_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CategoryData(
                            rs.getInt("id_categoria"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modificar(CategoryData categoryData) {
        String qry = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, categoryData.getNombre());
            stmt.setString(2, categoryData.getDescripcion());
            stmt.setInt(3, categoryData.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String qry = "DELETE FROM categoria WHERE id_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdCategoria(String nombre) {
        String qry = "SELECT buscar_categoria(?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // No encontrado
    }

    public List<CategoryData> listaCategorias() {
        List<CategoryData> categorias = new ArrayList<>();
        String qry = "SELECT id_categoria, nombre, descripcion FROM categoria";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(new CategoryData(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public List<CategoryData> listaCategoriasAlfabeticamente() {
        List<CategoryData> categorias = new ArrayList<>();
        String qry = "SELECT id_categoria, nombre, descripcion FROM categoria ORDER BY nombre";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(new CategoryData(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
