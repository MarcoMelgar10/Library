package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.ControladoresVistas.BookScene.IFiltro;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ObjetosVistas.CategoryData;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.ObjetosVistas.LibroCardData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
  Clase para realizar la interacción con la base de datos, para la tabla libro.
 */
public class LibroDAO {

    // Inserta nuevos libros en la BD
    public void insertar(Libro libro) {
        String qry = "CALL agregar_libro(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getObservacion());
            if (libro.getPublicacion() == null) {
                stmt.setNull(3, Types.DATE);
            } else {
                stmt.setDate(3, libro.getPublicacion());
            }
            stmt.setInt(4, libro.getStock());
            stmt.setInt(5, libro.getIdAutor());
            stmt.setInt(6, libro.getIdCategoria());
            stmt.setInt(7, libro.getIdSubCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + libro.getTitulo());
        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Obtiene el siguiente ID disponible para un libro
    public Integer getNextId() {
        String qry = "SELECT MAX(id_libro) AS id_libro FROM libro";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(qry);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_libro") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Busca y devuelve un libro específico por ID
    public Libro obtener(int id) {
        String qry = "SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, " +
                "l.stock, l.stock_disponible, a.nombre AS autor, " +
                "c.nombre AS categoria, s.nombre AS sub_categoria " +
                "FROM libro l " +
                "JOIN autor a ON l.id_autor = a.id_autor " +
                "JOIN categoria c ON l.id_categoria = c.id_categoria " +
                "JOIN sub_categoria s ON l.id_sub_categoria = s.id_sub_categoria " +
                "WHERE l.id_libro = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstmt = conn.prepareStatement(qry)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Libro.Builder()
                            .ID(rs.getInt("id_libro"))
                            .titulo(rs.getString("titulo"))
                            .observacion(rs.getString("observacion"))
                            .publicacion(rs.getDate("fecha_publicacion"))
                            .stock(rs.getInt("stock"))
                            .stockDisponible(rs.getInt("stock_disponible"))
                            .autor(rs.getString("autor"))
                            .categoria(rs.getString("categoria"))
                            .subCategoria(rs.getString("sub_categoria"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Modifica un libro existente
    public void modificar(Libro libro) {
        String qry = "UPDATE libro SET titulo = ?, observacion = ?, fecha_publicacion = ?, stock = ?, " +
                "stock_disponible = ?, id_autor = ?, id_categoria = ?, id_sub_categoria = ? " +
                "WHERE id_libro = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getObservacion());
            stmt.setDate(3, libro.getPublicacion());
            stmt.setInt(4, libro.getStock());
            stmt.setInt(5, libro.getStockDisponible());
            stmt.setInt(6, libro.getIdAutor());
            stmt.setInt(7, libro.getIdCategoria());
            stmt.setInt(8, libro.getIdSubCategoria());
            stmt.setInt(9, libro.getID());
            stmt.executeUpdate();
            System.out.println("Libro actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Elimina un libro (marcándolo como eliminado)
    public void eliminar(int id) {
        String qry = "UPDATE libro SET d_e_l_e_t_e = 'true' WHERE id_libro = ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Libro marcado como eliminado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtiene los nombres de las columnas de la tabla libro
    public ArrayList<String> nombreColumnas() {
        String qry = "SELECT column_name FROM information_schema.columns WHERE table_name = 'libro'";
        ArrayList<String> columnas = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                columnas.add(rs.getString("column_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnas;
    }

    // Obtiene una lista de todos los libros
    public ArrayList<Libro> listaLibros() {
        String qry = "SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, l.stock, " +
                "l.stock_disponible, l.id_autor, l.id_categoria, l.id_sub_categoria, a.nombre " +
                "FROM libro l JOIN autor a ON l.id_autor = a.id_autor";
        ArrayList<Libro> libros = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                libros.add(new Libro.Builder()
                        .ID(rs.getInt("id_libro"))
                        .titulo(rs.getString("titulo"))
                        .observacion(rs.getString("observacion"))
                        .publicacion(rs.getDate("fecha_publicacion"))
                        .stock(rs.getInt("stock"))
                        .stockDisponible(rs.getInt("stock_disponible"))
                        .idAutor(rs.getInt("id_autor"))
                        .idCategoria(rs.getInt("id_categoria"))
                        .idSubCategoria(rs.getInt("id_sub_categoria"))
                        .autor(rs.getString("nombre"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    // Obtiene una lista de libros para visualización (sin detalles completos)
    public ArrayList<IDatoVisual> listaLibrosVisual() {
        String qry = "SELECT l.id_libro, l.titulo, l.stock, l.stock_disponible, a.nombre " +
                "FROM libro l JOIN autor a ON l.id_autor = a.id_autor " +
                "WHERE l.d_e_l_e_t_e = 'false'";
        ArrayList<IDatoVisual> libros = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement stmt = conn.prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                libros.add(new LibroCardData(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getInt("stock"),
                        rs.getInt("stock_disponible")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }
/*
    // Obtiene una lista de libros con filtros aplicados
    public ArrayList<IDatoVisual> listaLibrosVisualParametrizada(String textoBusqueda, List<CategoryData> categoriasSeleccionadas, List<IFiltro> filtrosSeleccionados, String tipoBusqueda) {
        StringBuilder query = new StringBuilder(
                "SELECT l.id_libro, l.titulo, l.stock, l.stock_disponible, a.nombre " +
                        "FROM libro l JOIN autor a ON l.id_autor = a.id_autor " +
                        "WHERE l.d_e_l_e_t_e = 'false'"
        );

        // Agregar condición de búsqueda por título o autor
        if (textoBusqueda != null && !textoBusqueda.isEmpty()) {
            if (ModeloLibros.BUSQUEDA_POR_TITULO.equals(tipoBusqueda)) {
                query.append(" AND l.titulo ILIKE '%").append(textoBusqueda.replace("'", "''")).append("%'");
            } else if (ModeloLibros.BUSQUEDA_POR_AUTOR.equals(tipoBusqueda)) {
                query.append(" AND a.nombre ILIKE '%").append(textoBusqueda.replace("'", "''")).append("%'");
            }
        }

        // Agregar filtro de categorías
        if (categoriasSeleccionadas != null && !categoriasSeleccionadas.isEmpty()) {
            query.append(" AND l.id_categoria IN (");
            for (CategoryData categoria : categoriasSeleccionadas) {
                query.append(categoria.getId()).append(",");
            }
            query.deleteCharAt(query.length() - 1); // Eliminar la última coma
            query.append(")");
        }

        ArrayList<IDatoVisual> libros = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query.toString())) {

            while (rs.next()) {
                libros.add(new LibroCardData(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("nombre"),
                        rs.getInt("stock"),
                        rs.getInt("stock_disponible")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }*/
}

