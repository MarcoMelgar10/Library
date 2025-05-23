package com.odvp.biblioteca.database.daos;
import com.odvp.biblioteca.objetos.Usuario;
import com.odvp.biblioteca.objetosVisuales.IDatoVisual;
import com.odvp.biblioteca.objetosVisuales.UsuarioData;
import com.odvp.biblioteca.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla usuario.
   */
public class UsuarioDAO{

    public void insertar(Usuario usuario) {
        String qry = "INSERT INTO usuario(id_usuario, nombre, apellido_paterno, apellido_materno, telefono, direccion, estado_bloqueo) VALUES (?,?,?,?,?,?,?)";
        try(Connection conn = ConexionDB.getOrCreate().getConexion();
            PreparedStatement stmt = conn.prepareStatement(qry)){
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellidoPaterno());
            stmt.setString(4, usuario.getApellidoMaterno());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getDireccion());
            stmt.setBoolean(7, false);
            stmt.executeQuery();
            System.out.println("Usuario agregado");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    public Usuario obtener(int id) {

        String qry = "SELECT id_usuario, nombre, apellido_paterno, apellido_materno, telefono, direccion, estado_bloqueo " +
                "FROM usuario WHERE id_usuario = ?";
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
    public ArrayList<IDatoVisual> listaUsuarios() {
        String qry = "SELECT id_usuario, nombre, apellido_paterno, apellido_materno, telefono, direccion, estado_bloqueo FROM usuario WHERE D_E_L_E_T_E = false";
        ArrayList<IDatoVisual> usuarios = new ArrayList<>();
        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstm = conn.prepareStatement(qry);
             ResultSet rs = pstm.executeQuery()){

            while (rs.next()) {
                // Mapeo de los resultados de la consulta a objetos Usuario
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("apellido_paterno");
                String materno = rs.getString("apellido_materno");
                boolean estadoBloqueo = rs.getBoolean("estado_bloqueo");

                // Usando el patrón Builder para construir el objeto Usuario
                UsuarioData usuario = new UsuarioData(idUsuario, nombre + " " + paterno + " " + materno,estadoBloqueo);


                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public ArrayList<IDatoVisual> listaUsuariosParametrizado(String textoBusqueda) {
        String qry = "SELECT id_usuario, CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) as nombre_completo, telefono, direccion, estado_bloqueo FROM usuario WHERE D_E_L_E_T_E = false";
        ArrayList<IDatoVisual> usuarios = new ArrayList<>();

        if (textoBusqueda != null && !textoBusqueda.isEmpty()) {
            qry += " AND CONCAT(nombre, ' ', apellido_paterno, ' ', apellido_materno) ILIKE ?";
        }

        try (Connection conn = ConexionDB.getOrCreate().getConexion();
             PreparedStatement pstm = conn.prepareStatement(qry)) {

            if (textoBusqueda != null && !textoBusqueda.isEmpty()) {
                String parametroBusqueda = "%" + textoBusqueda + "%";
                pstm.setString(1, parametroBusqueda);
            }

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    int idUsuario = rs.getInt("id_usuario");
                    String nombre = rs.getString("nombre_completo");
                    boolean estadoBloqueo = rs.getBoolean("estado_bloqueo");

                    UsuarioData usuario = new UsuarioData(idUsuario, nombre,estadoBloqueo);

                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }



    public void eliminar(int id) {
        String qry = "UPDATE usuario SET d_e_l_e_t_e = 'true' WHERE id_usuario = ?";
        try(Connection conn = ConexionDB.getOrCreate().getConexion();
            PreparedStatement stm = conn.prepareStatement(qry)){
            stm.setInt(1,id);
            stm.executeUpdate();
            System.out.println("Usuario dado de baja correctamente");
        }catch (SQLException e){
            e.printStackTrace();
        }
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
