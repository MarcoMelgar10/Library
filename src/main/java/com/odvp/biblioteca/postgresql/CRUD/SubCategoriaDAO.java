package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.SubCategoryData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubCategoriaDAO implements ICRUD {
    private String qry;
    private ConexionDB conexionDB;
    private SubCategoryData subCategoryData;

    public SubCategoriaDAO(SubCategoryData subCategoryData, ConexionDB conexionDB){
        this.conexionDB = conexionDB;
        this.subCategoryData = subCategoryData;
    }
    @Override
    public void insertar() {
        qry = "CALL agregar_sub_categoria(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1,subCategoryData.getNombre());
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

    @Override
    public Object buscar(String nombre) {
        subCategoryData  = null;
        String sql = "SELECT id_sub_categoria, nombre, descripcion, id_categoria FROM sub_categoria WHERE nombre = ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                subCategoryData = new SubCategoryData(
                        rs.getInt("id_sub_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("id_categoria")
                );

                System.out.println("Subcategoría encontrada: " + subCategoryData.getNombre());
            } else {
                System.out.println("No se encontró la subcategoría: " + subCategoryData.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCategoryData;
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
