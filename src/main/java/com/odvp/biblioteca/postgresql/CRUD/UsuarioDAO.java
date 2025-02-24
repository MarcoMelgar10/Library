package com.odvp.biblioteca.postgresql.CRUD;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
  Clase para realizar la interaccion con la base de datas, para la tabla usuario.
   */
public class UsuarioDAO implements ICRUD{
    private  String qry;
    private Usuario usuario;
    private ConexionDB conexionDB;
    public UsuarioDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
    public void insertar(Object usuario) {
        this.usuario = (Usuario) usuario;
        qry = "CALL agregar_usuario(?,?,?,?)";
        try(PreparedStatement stmt =conexionDB.getConexion().prepareStatement(qry)){
            stmt.setString(1, ((Usuario) usuario).getNombre());
            stmt.setString(2, ((Usuario) usuario).getApellidos());
            stmt.setString(3, ((Usuario) usuario).getTelefono());
            stmt.setString(4, ((Usuario) usuario).getDireccion());
            stmt.executeQuery();
            System.out.println("Usuario agregado");
        }catch (SQLException e){
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Object visualizar(String nombre) {
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
    public void modificar(Object usuario) {
        this.usuario = (Usuario) usuario;

        qry   = "UPDATE usuario SET nombre = ?, apellidos = ?, telefono = ?, direccion = ?, multa = ?, estado_bloqueo = ? WHERE id_usuario = ?";
         try (PreparedStatement pstmt = conexionDB.getConexion().prepareStatement(qry)) {
            pstmt.setString(1, ((Usuario) usuario).getNombre());
            pstmt.setString(2, ((Usuario) usuario).getApellidos());
            pstmt.setString(3, ((Usuario) usuario).getTelefono());
            pstmt.setString(4, ((Usuario) usuario).getDireccion());
            pstmt.setInt(5, ((Usuario) usuario).getMulta());
            pstmt.setBoolean(6, ((Usuario) usuario).isEstadoBloqueo());
            pstmt.setInt(7, ((Usuario) usuario).getId());
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
    public void eliminar(int id) {


    }

    public void bloquearUsuario(int idUsuario){
    qry = "UPDATE usuario SET estado_bloque = true WHERE id_usuario = (?)";
    try(PreparedStatement stm = conexionDB.getConexion().prepareStatement(qry)) {
        stm.setInt(1, idUsuario);
        stm.executeQuery();
        System.out.println("Usuario bloqueado");
    }catch (SQLException e){
        System.out.println(e.getMessage());
        System.out.println(e.getSQLState());
    }

    }

}
