package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroAdministrador.Administrador;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO implements ICRUD{
    private String qry;
    private ConexionDB conexionDB;
    private Administrador administrador;

    public AdministradorDAO(Administrador administrador,ConexionDB conexionDB){
        this.conexionDB = conexionDB;
        this.administrador = administrador;
    }
    @Override
    public Object buscar(String usuario) {
        return null;
    }

    @Override
    public void modificar() {

    }

    @Override
    public void eliminar() {

    }

    //Agregar un nuevo administrador
    @Override
    public void insertar() {
        qry = "CALL agregar_administrador(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1,administrador.getNombre());
            stmt.setString(2, administrador.getContrasena());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + administrador.getNombre());

        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());

        }

    }

    //Buscar el nombre del administrador si existe
    public String obtenerUsuarioAdmin(){
        String usuario = null;
         qry = "select usuario from administrador where usuario = (?)";
        try(PreparedStatement stmt =conexionDB.getConexion().prepareStatement(qry)){
            stmt.setString(1, administrador.getNombre());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                usuario = rs.getString("usuario");
             //   logger.info("Se hallo el usuario");
            }else{
             //   logger.error("No existe el usuario");
                return null;
            }
        }catch (SQLException e){
            //logger.debug(e.getMessage());
        }
        return usuario;
    }

    //Comprobar la constasena del administrador
    public boolean comprobarContrasena() {
        String sql = "SELECT contrasena FROM administrador WHERE usuario = ?";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(sql)) {
            stmt.setString(1, obtenerUsuarioAdmin());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String contrasenaGuardada = rs.getString("contrasena");
                // Comparar las contraseñas usando crypt
                return BCrypt.checkpw(administrador.getContrasena(), contrasenaGuardada);
            }
        } catch (SQLException e) {
            //logger.error("Error al comprobar la contraseña: " + e.getMessage());
        }
        return false;
    }
}
