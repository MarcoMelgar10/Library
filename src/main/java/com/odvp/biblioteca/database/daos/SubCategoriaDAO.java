package com.odvp.biblioteca.database.daos;
import com.odvp.biblioteca.objetosVisuales.SubCategoryData;
import com.odvp.biblioteca.database.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla subcategoria.
   */
public class SubCategoriaDAO{

    public void insertar(SubCategoryData subCategoryData) {
        String qry = "CALL agregar_sub_categoria(?,?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, subCategoryData.getNombre());
            stmt.setString(2, subCategoryData.getDescripcion());
            stmt.setString(3, subCategoryData.getCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + subCategoryData.getNombre());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());

        }
    }

    public SubCategoryData obtener(int id) {
        String qry = "SELECT id_sub_categoria, nombre, descripcion, id_categoria FROM sub_categoria WHERE id_sub_categoria = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(qry)) {
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    SubCategoryData subCategoryData = new SubCategoryData(
                            rs.getInt("id_sub_categoria"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("id_categoria"));
                    System.out.println("Subcategoría encontrada: " + subCategoryData.getNombre());
                    return subCategoryData;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void modificar(SubCategoryData categorySubData) {

    }


    public void eliminar(int id) {

    }


    public ArrayList<SubCategoryData> listaSubCategorias() {
        String qry = "SELECT sc.id_sub_categoria, sc.nombre, sc.descripcion, sc.id_categoria, c.nombre AS categoria " +
                "FROM sub_categoria sc " +
                "JOIN categoria c ON sc.id_categoria = c.id_categoria";
        ArrayList<SubCategoryData> subCategorias = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                int id = rs.getInt("id_sub_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int idCategoria = rs.getInt("id_categoria");
                subCategorias.add(new SubCategoryData(id, nombre, descripcion, idCategoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategorias;
    }
    public ArrayList<SubCategoryData> obtenerSubCategoriasPorCategoria(int categoriaId) {
        String qry = "SELECT * " +
                "FROM sub_categoria " +
                "WHERE id_categoria = '"+ categoriaId +"'";
        ArrayList<SubCategoryData> subCategorias = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement ps = conn.prepareStatement(qry);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_sub_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int idCategoria = rs.getInt("id_categoria");
                subCategorias.add(new SubCategoryData(id, nombre, descripcion, idCategoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategorias;
    }

    public ArrayList<SubCategoryData> obtenerSubCategoriasPorCategoriaAlfabeticamente(int categoriaId) {
        String qry = "SELECT * " +
                "FROM sub_categoria " +
                "WHERE id_categoria = '"+ categoriaId +"' order by nombre";
        ArrayList<SubCategoryData> subCategorias = new ArrayList<>();

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement ps = conn.prepareStatement(qry);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_sub_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int idCategoria = rs.getInt("id_categoria");
                subCategorias.add(new SubCategoryData(id, nombre, descripcion, idCategoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategorias;
    }

}
