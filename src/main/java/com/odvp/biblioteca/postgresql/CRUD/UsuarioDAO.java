package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuario.Usuario;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioDAO implements ICRUD{
    private  String qry;
    private Usuario usuario;
    private ConexionDB conexionDB;
    public UsuarioDAO(Usuario usuario, ConexionDB conexionDB){
        this.usuario = usuario;
        this.conexionDB = conexionDB;
    }
    @Override
    public void insertar() {
        qry = "CALL agregar_usuario(?,?,?,?)";
        try(PreparedStatement stmt =conexionDB.getConexion().prepareStatement(qry)){
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getTelefono());
            stmt.setString(4, usuario.getDireccion());
            stmt.executeQuery();
            System.out.println("Usuario agregado");
        }catch (SQLException e){
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Object buscar(String nombre) {
        Usuario usuario = null;
        qry = "SELECT id_usuario, nombre, apellidos, telefono, direccion, multa, estado_bloqueo " +
                "FROM usuario WHERE UPPER(nombre) LIKE ?";
        try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
            pstmt.setString(1, "%" + nombre.toUpperCase() + "%"); // Buscar con LIKE y sin distinción de mayúsculas/minúsculas
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario.Builder builder = new Usuario.Builder();
                builder.idUsuario(rs.getInt("id_usuario"));
                builder.nombre( rs.getString("nombre"));
                builder.apellidos(rs.getString("apellidos"));
                builder.telefono(rs.getString("telefono"));
                builder.direccion(rs.getString("direccion"));
                builder.multa(rs.getInt("multa"));
                builder.estadoBloqueo(rs.getBoolean("estado_bloqueo"));
                usuario = new Usuario(builder);
                System.out.println("Usuario encontrado: " + usuario.getNombre());
            } else {
                System.out.println("No se encontró el usuario con nombre: " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return usuario;
        }



    @Override
    public void modificar() {
         qry   = "UPDATE usuario SET nombre = ?, apellidos = ?, telefono = ?, direccion = ?, multa = ?, estado_bloqueo = ? WHERE id_usuario = ?";
         try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getTelefono());
            pstmt.setString(4, usuario.getDireccion());
            pstmt.setInt(5, usuario.getMulta());
            pstmt.setBoolean(6, usuario.isEstadoBloqueo());
            pstmt.setInt(7, usuario.getId());
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró el usuario con ID: " + usuario.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Usuario> listaUsuarios() {
        String qry = "SELECT id_usuario, nombre, apellidos, telefono, direccion, multa, estado_bloqueo FROM usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Mapeo de los resultados de la consulta a objetos Usuario
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                int multa = rs.getInt("multa");
                boolean estadoBloqueo = rs.getBoolean("estado_bloqueo");

                // Usando el patrón Builder para construir el objeto Usuario
                Usuario usuario = new Usuario.Builder()
                        .idUsuario(idUsuario)
                        .nombre(nombre)
                        .apellidos(apellidos)
                        .telefono(telefono)
                        .direccion(direccion)
                        .multa(multa)
                        .estadoBloqueo(estadoBloqueo)
                        .build(); // Llamar a build() para obtener el objeto final

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public void eliminar() {

    }

}
