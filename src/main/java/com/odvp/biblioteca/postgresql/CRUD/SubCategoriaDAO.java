package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.Objetos.SubCategoryData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla subcategoria.
   */
public class SubCategoriaDAO implements ICRUD {
    private String qry;
    private ConexionDB conexionDB;
    private SubCategoryData subCategoryData;

    public SubCategoriaDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
    public void insertar(Object subCategoryData) {
       this.subCategoryData = (SubCategoryData) subCategoryData;
        qry = "CALL agregar_sub_categoria(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1,((SubCategoryData) subCategoryData).getNombre());
            stmt.setString(2, ((SubCategoryData) subCategoryData).getDescripcion());
            stmt.setString(3, ((SubCategoryData) subCategoryData).getCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + ((SubCategoryData) subCategoryData).getNombre());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());

        }
    }

    @Override
    public Object visualizar(int id) {
        subCategoryData  = null;
        qry = "SELECT id_sub_categoria, nombre, descripcion, id_categoria FROM sub_categoria WHERE id_sub_categoria = ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
            pstmt.setInt(1, id);
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
    public void modificar(Object categorySubData) {
this.subCategoryData = subCategoryData;
    }

    @Override
    public void eliminar(int id) {

    }


    public int getIdCategoria(String nombre){
        int id = -1; // Valor por defecto en caso de error
         qry = "SELECT buscar_categoria(?)";

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
    public ArrayList<SubCategoryData> listaSubCategorias() {
        String qry = "SELECT sc.id_sub_categoria, sc.nombre, sc.descripcion, sc.id_categoria, c.nombre AS categoria " +
                "FROM sub_categoria sc " +
                "JOIN categoria c ON sc.id_categoria = c.id_categoria";
        ArrayList<SubCategoryData> subCategorias = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
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

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
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

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
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
