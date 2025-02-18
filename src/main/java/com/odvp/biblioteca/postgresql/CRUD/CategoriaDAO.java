package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO implements ICRUD{
    private String qry;
    private ConexionDB conexionDB;
    private CategoryData categoryData;

    public CategoriaDAO(CategoryData categoryData, ConexionDB conexionDB){
      this.conexionDB = conexionDB;
      this.categoryData = categoryData;
    }
    @Override
    public void insertar() {
        qry = "CALL agregar_categoria(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1,categoryData.getNombre());
            stmt.setString(2, categoryData.getDescripcion());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + categoryData.getNombre());

        } catch (SQLException e) {
            // Manejo de errores más detallado

            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());

        }

    }

    @Override
    public Object buscar(String titulo) {
        CategoryData categoria = null;
        String sql = "SELECT id_categoria, nombre, descripcion FROM categoria WHERE id_categoria = ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                categoria = new CategoryData(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );

                System.out.println("Categoría encontrada: " + categoria.getNombre());
            } else {
                System.out.println("No se encontró la categoría: " + categoria.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }



    @Override
    public void modificar() {

    }

    @Override
    public void eliminar() {

    }




    public int getIdCategoria(String nombre){
        int id = -1; // Valor por defecto en caso de error
        String qry = "SELECT buscar_categoria(?)";

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
}
