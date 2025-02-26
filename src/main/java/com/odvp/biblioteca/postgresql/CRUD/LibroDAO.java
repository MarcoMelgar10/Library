package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.Objetos.IDatoVisual;
import com.odvp.biblioteca.Objetos.Libro;
import com.odvp.biblioteca.Objetos.LibroCardData;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

/*
  Clase para realizar la interaccion con la base de datas, para la tabla libro.
   */
public class LibroDAO implements ICRUD {
private  String qry;
private ConexionDB conexionDB;

public LibroDAO(){
        conexionDB = ConexionDB.getOrCreate();
    }


    //Inserta nuevos libros en la BD
    @Override
    public void insertar(Object libro) {
         qry = "call agregar_libro(?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            Libro libro1 = (Libro) libro;
            stmt.setString(1, libro1.getTitulo());
            stmt.setString(2, libro1.getObservacion());
            if(libro1.getPublicacion() == null){
                stmt.setNull(3, Types.DATE);
            }else {
                stmt.setDate(3, libro1.getPublicacion());
            }
            stmt.setInt(4, libro1.getStock());
            stmt.setInt(5, libro1.getIdAutor());
            stmt.setInt(6, libro1.getIdCategoria());
            stmt.setInt(7, libro1.getIdSubCategoria());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + libro1.getTitulo());

        } catch (SQLException e) {
            // Manejo de errores más detallado
            
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Integer getNextId(){
        qry = "SELECT MAX(id_libro) as id_libro FROM libro";
        int id=0;
        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_libro");
                return id;
            } else {
                System.out.println("No se encontró el libro con id: " + id + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    //Busca y devuelve un libro especifico por titulo
    @Override
    public Libro visualizar(int id) {
        Libro.Builder builder = new Libro.Builder();
        qry = "SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, " +
                "l.stock, l.stock_disponible, a.nombre AS autor, " +
                "c.nombre AS categoria, s.nombre AS sub_categoria " +
                "FROM libro l " +
                "JOIN autor a ON l.id_autor = a.id_autor " +
                "JOIN categoria c ON l.id_categoria = c.id_categoria " +
                "JOIN sub_categoria s ON l.id_sub_categoria = s.id_sub_categoria " +
                "WHERE l.id_libro = ?";

        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
            pstmt.setInt(1, id); // Usar LIKE con comodines
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                builder.ID(rs.getInt("id_libro"));
                builder.titulo(rs.getString("titulo"));
                builder.observacion(rs.getString("observacion"));
                Date fecha_publicacion = rs.getDate("fecha_publicacion");
                if(fecha_publicacion != null) builder.publicacion(fecha_publicacion);
                builder.stock(rs.getInt("stock"));
                builder.stockDisponible(rs.getInt("stock_disponible"));
                builder.autor(rs.getString("autor"));
                builder.categoria(rs.getString("categoria"));
                builder.subCategoria(rs.getString("sub_categoria"));
                System.out.println("Libro encontrado: " + rs.getString("titulo"));
                return new Libro(builder);
            } else {
                System.out.println("No se encontró el libro con id: " + id);
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



    public ArrayList<Libro> listaLibros() {
         qry = "SELECT l.id_libro, l.titulo, l.observacion, l.fecha_publicacion, l.stock, l.stock_disponible,l.id_autor, l.id_categoria, l.id_sub_categoria, a.nombre FROM libro l JOIN autor a on l.id_autor = a.id_autor";
        ArrayList<Libro> libros = new ArrayList<>();
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {  // Usamos stmt.executeQuery sin pasarle qry, ya que ya lo definimos antes
            while (rs.next()) {
                // Mapeo de los resultados de la consulta a objetos Libro
                int idLibro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                String observacion = rs.getString("observacion");
                Date fechaPublicacion = rs.getDate("fecha_publicacion");
                int stock = rs.getInt("stock");
                int stockDisponible = rs.getInt("stock_disponible");
                int idAutor = rs.getInt("id_autor");
                int idCategoria = rs.getInt("id_categoria");
                int idSubCategoria = rs.getInt("id_sub_categoria");
                String nombreAutor = rs.getString("nombre");

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
                        .autor(nombreAutor)
                        .build(); // Llamar a build() para obtener el objeto final

                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

    public ArrayList<IDatoVisual> listaLibrosVisual() {
        qry = "SELECT l.id_libro, l.titulo, l.stock, l.stock_disponible, a.nombre FROM libro l JOIN autor a on l.id_autor = a.id_autor";
        ArrayList<IDatoVisual> libros = new ArrayList<>();
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {  // Usamos stmt.executeQuery sin pasarle qry, ya que ya lo definimos antes
            while (rs.next()) {
                // Mapeo de los resultados de la consulta a objetos Libro
                int idLibro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                int stock = rs.getInt("stock");
                int stockDisponible = rs.getInt("stock_disponible");
                String nombreAutor = rs.getString("nombre");

                // Usando el patrón Builder para construir el objeto Libro
                LibroCardData libroCardData = new LibroCardData(idLibro, titulo, nombreAutor, stock, stockDisponible);

                libros.add(libroCardData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

}
