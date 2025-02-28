package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.SubCategoryData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubCategoriaDAO{

    public void insertar(SubCategoryData subCategoryData) {
        String qry = "CALL agregar_sub_categoria(?, ?, ?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, subCategoryData.getNombre());
            stmt.setString(2, subCategoryData.getDescripcion());
            stmt.setString(3, subCategoryData.getCategoria());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public SubCategoryData obtener(int id) {
        String qry = "SELECT id_sub_categoria, nombre, descripcion, id_categoria FROM sub_categoria WHERE id_sub_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(qry)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new SubCategoryData(
                            rs.getInt("id_sub_categoria"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("id_categoria")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modificar(SubCategoryData subCategoryData) {
        String qry = "UPDATE sub_categoria SET nombre = ?, descripcion = ?, id_categoria = ? WHERE id_sub_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, subCategoryData.getNombre());
            stmt.setString(2, subCategoryData.getDescripcion());
            stmt.setInt(3, subCategoryData.getId_categoria());
            stmt.setInt(4, subCategoryData.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String qry = "DELETE FROM sub_categoria WHERE id_sub_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SubCategoryData> listaSubCategorias() {
        String qry = "SELECT id_sub_categoria, nombre, descripcion, id_categoria FROM sub_categoria";
        ArrayList<SubCategoryData> subCategorias = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                subCategorias.add(new SubCategoryData(
                        rs.getInt("id_sub_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_categoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategorias;
    }


    public ArrayList<SubCategoryData> obtenerSubCategoriasPorCategoriaAlfabeticamente(int categoriaId) {
        String qry = "SELECT * FROM sub_categoria WHERE id_categoria = ? ORDER BY nombre";
        ArrayList<SubCategoryData> subCategorias = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, categoriaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_sub_categoria");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    int idCategoria = rs.getInt("id_categoria");
                    subCategorias.add(new SubCategoryData(id, nombre, descripcion, idCategoria));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
        return subCategorias;
    }
}
