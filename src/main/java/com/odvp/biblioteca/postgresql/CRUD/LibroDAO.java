package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


public class LibroDAO implements ICRUD {
private  String qry;
private Libro libro;
private ConexionDB conexionDB;

public LibroDAO(Libro libro, ConexionDB conexionDB){
        this.libro = libro;
        this.conexionDB = conexionDB;
    }


    //Inserta nuevos libros en la BD
    @Override
    public void insertar() {
         qry = "call agregar_libro(?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getObservacion());
            stmt.setDate(3, libro.getPublicacion());
            stmt.setInt(4, libro.getStock());
            stmt.setInt(5, libro.getIdAutor());
            stmt.setInt(6,libro.getIdCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + libro.getTitulo());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }


    //Busca y devuelve un libro especifico por titulo
    @Override
    public Libro buscar(String titulo) {
        Libro.Builder builder = new Libro.Builder();
        String sql = "SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, " +
                "l.stock, l.stock_disponible, a.nombre AS autor, " +
                "c.nombre AS categoria, s.nombre AS sub_categoria " +
                "FROM libro l " +
                "JOIN autor a ON l.id_autor = a.id_autor " +
                "JOIN categoria c ON l.id_categoria = c.id_categoria " +
                "JOIN sub_categoria s ON l.id_sub_categoria = s.id_sub_categoria " +
                "WHERE UPPER(l.titulo) LIKE ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(sql)) {
            pstmt.setString(1, "%" + titulo.toUpperCase() + "%"); // Usar LIKE con comodines
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                builder.ID(rs.getInt("id_libro"));
                builder.titulo(rs.getString("titulo"));
                builder.observacion(rs.getString("observacion"));
                LocalDate fecha_publicacion = rs.getDate("fecha_publicacion").toLocalDate();
                builder.publicacion(fecha_publicacion);
                builder.stock(rs.getInt("stock"));
                builder.stockDisponible(rs.getInt("stock_disponible"));
                builder.autor(rs.getString("autor"));
                builder.categoria(rs.getString("categoria"));
                builder.subCategoria(rs.getString("sub_categoria"));
                System.out.println("Libro encontrado: " + rs.getString("titulo"));
                return new Libro(builder);
            } else {
                System.out.println("No se encontró el libro con título: " + titulo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retornar null si no se encuentra el libro
    }



    @Override
    public void modificar() {

    }

    @Override
    public void eliminar() {

    }

    public ArrayList<Libro> listaLibros() {
        String qry = "SELECT id_libro, titulo, observacion, fecha_publicacion, stock, stock_disponible, id_autor, id_categoria, id_sub_categoria FROM libro";
        ArrayList<Libro> libros = new ArrayList<>();
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {  // Usamos stmt.executeQuery sin pasarle qry, ya que ya lo definimos antes
            while (rs.next()) {
                // Mapeo de los resultados de la consulta a objetos Libro
                int idLibro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                String observacion = rs.getString("observacion");
                LocalDate fechaPublicacion = rs.getDate("fecha_publicacion").toLocalDate();
                int stock = rs.getInt("stock");
                int stockDisponible = rs.getInt("stock_disponible");
                int idAutor = rs.getInt("id_autor");
                int idCategoria = rs.getInt("id_categoria");
                int idSubCategoria = rs.getInt("id_sub_categoria");

                // Usando el patrón Builder para construir el objeto Libro
                Libro libro = new Libro.Builder()
                        .ID(idLibro)
                        .titulo(titulo)
                        .observacion(observacion)
                        .publicacion(fechaPublicacion)
                        .stock(stock)
                        .stockDisponible(stockDisponible)
                        .idAutor(idAutor)
                        .idCategoria(idCategoria)
                        .idSubCategoria(idSubCategoria)
                        .build(); // Llamar a build() para obtener el objeto final

                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

}
