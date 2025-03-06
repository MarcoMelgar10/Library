package com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.VisualizarUsuario;

import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;

/*
    crea la vista para visualizar libro
 */

public class VisualizarUsuario {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    public VisualizarUsuario(ModeloUsuarios modelo){
        Usuario usuario = usuarioDAO.obtener(modelo.getUsuarioSeleccionado().getID());
        VIsualizarUsuarioVentana vIsualizarUsuarioVentana = new VIsualizarUsuarioVentana(usuario);
    }
}
