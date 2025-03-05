package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla usuario.
   */
public class UsuarioDAO{

    public void insertar(Usuario usuario) {
        String qry = "CALL agregar_usuario(?,?,?,?,?)";
        try(Connection conn = ConexionDB.getOrCreate().getConexion();
            PreparedStatement stmt = conn.prepareStatement(qry)){
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellidoPaterno());
            stmt.setString(3, usuario.getApellidoMaterno());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getDireccion());
            stmt.executeQuery();
            System.out.println("Usuario agregado");
        }catch (SQLException e){
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }

    }


    public Usuario obtener(int id) {

        String qry = "SELECT id_usuario, nombre, apellido_paterno, apellido_materno, telefono, direccion, estado_bloqueo " +
                "FROM usuario WHERE id_usuario ?";
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
            PreparedStatement pstmt = conn.prepareStatement(qry)) {
            pstmt.setInt(1, id); // Buscar con LIKE y sin distinción de mayúsculas/minúsculas
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    Usuario usuario = new Usuario.Builder()
                            .idUsuario(rs.getInt("id_usuario"))
                            .nombre( rs.getString("nombre"))
                            .apellidoPaterno(rs.getString("apellido_paterno"))
                            .apellidoMaterno(rs.getString("apellido_materno"))
                            .telefono(rs.getString("telefono"))
                            .direccion(rs.getString("direccion"))
                            .estadoBloqueo(rs.getBoolean("estado_bloqueo"))
                            .build();

                    System.out.println("Usuario encontrado: " + usuario.getNombre());
                    return usuario;
                } else {
                    System.out.println("No se encontró el usuario con nombre: " + id);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
        }




    public void modificar(Usuario usuario) {

        String qry   = "UPDATE usuario SET nombre = ?, apellido_paterno = ?,apellido_materno = ?, telefono = ?, direccion = ?, estado_bloqueo = ? WHERE id_usuario = ?";
         try (Connection conn = ConexionDB.getOrCreate().getConexion();
            PreparedStatement pstmt = conn.prepareStatement(qry)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidoPaterno());
             pstmt.setString(3, usuario.getApellidoMaterno());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setString(5, usuario.getDireccion());
            pstmt.setBoolean(6, usuario.isEstadoBloqueo());
            pstmt.setInt(7, usuario.getId());

            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró el usuario con ID: " + ((Usuario) usuario).getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Usuario> listaUsuarios() {
        String qry = "SELECT id_usuario, nombre, apellido_paterno, apellido_materno, telefono, direccion, estado_bloqueo FROM usuario";
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstm = conn.prepareStatement(qry);
             ResultSet rs = pstm.executeQuery()){

            while (rs.next()) {
                // Mapeo de los resultados de la consulta a objetos Usuario
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("apellido_paterno");
                String materno = rs.getString("apellido_materno");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                boolean estadoBloqueo = rs.getBoolean("estado_bloqueo");

                // Usando el patrón Builder para construir el objeto Usuario
                Usuario usuario = new Usuario.Builder()
                        .idUsuario(idUsuario)
                        .nombre(nombre)
                        .apellidoPaterno(paterno)
                        .apellidoMaterno(materno)
                        .telefono(telefono)
                        .direccion(direccion)
                        .estadoBloqueo(estadoBloqueo)
                        .build(); // Llamar a build() para obtener el objeto final

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }


    public void eliminar(int id) {


    }

    public void bloquearUsuario(int idUsuario){
    String qry = "UPDATE usuario SET estado_bloque = true WHERE id_usuario = (?)";
    try(Connection conn = ConexionDB.getOrCreate().getConexion();
        PreparedStatement stm = conn.prepareStatement(qry)) {
        stm.setInt(1, idUsuario);
        stm.executeQuery();
        System.out.println("Usuario bloqueado");
    }catch (SQLException e){
        System.out.println(e.getMessage());
        System.out.println(e.getSQLState());
    }

    }

}
