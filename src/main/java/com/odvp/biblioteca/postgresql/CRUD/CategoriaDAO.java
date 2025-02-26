package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.Objetos.CategoryData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
  Clase para realizar la interaccion con la base de datas, para la tabla categoria.
   */
public class CategoriaDAO implements ICRUD{
    private String qry;
    private ConexionDB conexionDB;
    private CategoryData categoryData;

    public CategoriaDAO(){
      this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
    public void insertar(Object categoryData) {
        this.categoryData = (CategoryData) categoryData;
        qry = "CALL agregar_categoria(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1,((CategoryData) categoryData).getDescripcion());
            stmt.setString(2, ((CategoryData) categoryData).getDescripcion());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + ((CategoryData) categoryData).getNombre());

        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public Object visualizar(int id) {
        CategoryData categoria = null;
        String sql = "SELECT id_categoria, nombre, descripcion FROM categoria WHERE id_categoria = ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
    public void modificar(Object categoryData) {

    }

    @Override
    public void eliminar(int id) {

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

    public ArrayList<CategoryData> listaCategorias() {
         qry = "SELECT id_categoria, nombre, descripcion FROM categoria";
        ArrayList<CategoryData> categorias = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                categorias.add(new CategoryData(id, nombre, descripcion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public ArrayList<CategoryData> listaCategoriasAlfabeticamente() {
        qry = "SELECT id_categoria, nombre, descripcion FROM categoria order by nombre";
        ArrayList<CategoryData> categorias = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                categorias.add(new CategoryData(id, nombre, descripcion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

}
