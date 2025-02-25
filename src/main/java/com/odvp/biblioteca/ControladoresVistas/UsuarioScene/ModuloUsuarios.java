package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.Objetos.Usuario;
import com.odvp.biblioteca.ObjetosVistas.UsuarioData;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class ModuloUsuarios extends BorderPane implements IModulo {
    private HeaderUsuarios header;
    private TableUsuarios table;

    private ModeloUsuarios modelo;

    public ModuloUsuarios(ModeloUsuarios modelo){
        this.modelo = modelo;
        header = new HeaderUsuarios(this.modelo);
        table = new TableUsuarios(this.modelo);
        setTop(header);
        setCenter(table);
        simularDatos();
    }


    public void simularDatos(){
        new Thread(new Task<>() {
            @Override
            protected Object call() throws Exception {
                List<IDatoVisual> datoUsuario= new ArrayList<>();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                List<Usuario> usuarios = usuarioDAO.listaUsuarios();
                for(Usuario usuario: usuarios){
                    datoUsuario.add(new UsuarioData(usuario.getId(), usuario.getNombre(), usuario.isEstadoBloqueo()));
                }
                modelo.setUsuariosMostrados(datoUsuario);
                return null;
            }
        }).start();


    }
}
