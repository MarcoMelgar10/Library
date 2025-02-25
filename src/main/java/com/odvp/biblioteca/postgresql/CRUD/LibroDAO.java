package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.ObjetosVistas.LibroDTO;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/*
  Clase para realizar la interaccion con la base de datas, para la tabla libro.
   */
public class LibroDAO implements ICRUD {
private  String qry;
private Libro libro;
private ConexionDB conexionDB;

public LibroDAO(){
        conexionDB = ConexionDB.getOrCreate();
    }


    //Inserta nuevos libros en la BD
    @Override
    public void insertar(Object libro) {
    this.libro = (Libro) libro;
         qry = "call agregar_libro(?, ?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1, ((Libro) libro).getTitulo());
            stmt.setString(2, ((Libro) libro).getObservacion());
            stmt.setDate(3, ((Libro) libro).getPublicacion());
            stmt.setInt(4, ((Libro) libro).getStock());
            stmt.setInt(5, ((Libro) libro).getIdAutor());
            stmt.setInt(6,((Libro) libro).getIdCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + ((Libro) libro).getTitulo());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }




    //Busca y devuelve un libro especifico por titulo
    @Override
    public Libro visualizar(String titulo) {
        Libro.Builder builder = new Libro.Builder();
        qry = "SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, " +
                "l.stock, l.stock_disponible, a.nombre AS autor, " +
                "c.nombre AS categoria, s.nombre AS sub_categoria " +
                "FROM libro l " +
                "JOIN autor a ON l.id_autor = a.id_autor " +
                "JOIN categoria c ON l.id_categoria = c.id_categoria " +
                "JOIN sub_categoria s ON l.id_sub_categoria = s.id_sub_categoria " +
                "WHERE UPPER(l.titulo) LIKE ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
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
    public void modificar(Object libro) {

    }

    @Override
    public void eliminar(int id) {

    }



    public ArrayList<LibroDTO> listaLibros() {
        String qry = """
        SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, l.stock, l.stock_disponible, 
               a.nombre AS nombre_autor, 
               c.nombre AS nombre_categoria, 
               sc.nombre AS nombre_subcategoria 
        FROM libro l 
        JOIN autor a ON l.id_autor = a.id_autor 
        JOIN categoria c ON l.id_categoria = c.id_categoria 
        JOIN sub_categoria sc ON l.id_sub_categoria = sc.id_sub_categoria
    """;

        ArrayList<LibroDTO> libros = new ArrayList<>();

        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idLibro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                String observacion = rs.getString("observacion");
                Date fechaPublicacion = rs.getDate("fecha_publicacion");
                int stock = rs.getInt("stock");
                int stockDisponible = rs.getInt("stock_disponible");
                String nombreAutor = rs.getString("nombre_autor");
                String nombreCategoria = rs.getString("nombre_categoria");
                String nombreSubCategoria = rs.getString("nombre_subcategoria");

                // Crear objeto DTO
                LibroDTO libroDTO = new LibroDTO(idLibro, titulo, observacion, fechaPublicacion, stock, stockDisponible,
                        nombreAutor, nombreCategoria, nombreSubCategoria);
                libros.add(libroDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }


}
