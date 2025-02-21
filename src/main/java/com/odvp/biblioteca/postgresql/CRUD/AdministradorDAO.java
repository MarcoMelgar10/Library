package com.odvp.biblioteca.postgresql.CRUD;

import com.odvp.biblioteca.FuncionesMaestros.MaestroAdministrador.Administrador;
import com.odvp.biblioteca.postgresql.conexionPostgresql.ConexionDB;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
  Clase para realizar la interaccion con la base de datas, para la tabla administrador.
   */
public class AdministradorDAO implements ICRUD{
    private String qry;
    private ConexionDB conexionDB;
    private Administrador administrador;


    public AdministradorDAO(){
        this.conexionDB = ConexionDB.getOrCreate();
    }
    @Override
    public Object visualizar(String usuario) {
        return null;
    }

    @Override
    public void modificar(Object administrador) {

    }

    @Override
    public void eliminar(int id) {

    }

    //Agregar un nuevo administrador
    @Override
    public void insertar(Object administrador) {
        this.administrador = (Administrador) administrador;
        qry = "CALL agregar_administrador(?,?)";
        try (PreparedStatement stmt = conexionDB.getConexion().prepareStatement(qry)) {
            stmt.setString(1,((Administrador) administrador).getNombre());
            stmt.setString(2, ((Administrador) administrador).getContrasena());
            stmt.execute();
            System.out.println("Información cargada a la base de datos: " + ((Administrador) administrador).getNombre());

        } catch (SQLException e) {
            System.out.println("Error SQL State: " + e.getSQLState());
            System.out.println("Error: " + e.getMessage());

        }

    }

    //Buscar el nombre del administrador si existe
    public String obtenerUsuarioAdmin(Administrador administrador){
        this.administrador = administrador;
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
            stmt.setString(1, obtenerUsuarioAdmin(administrador));
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
