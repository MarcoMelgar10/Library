package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.postgresql.CRUD.UsuarioDAO;
import javafx.scene.layout.BorderPane;


public class ModuloUsuarios extends BorderPane implements IModulo {
    private HeaderUsuarios header;
    private TableUsuarios table;
    private ModeloUsuarios modelo;
    private ServicioBusquedaUsuarios servicioBusquedaUsuarios;
    public ModuloUsuarios(ModeloUsuarios modelo){
        this.modelo = modelo;
        header = new HeaderUsuarios(this.modelo);
        table = new TableUsuarios(this.modelo);
        servicioBusquedaUsuarios= new ServicioBusquedaUsuarios(modelo);
        setTop(header);
        setCenter(table);
        //simularDatos();
    }


    public void simularDatos(){
        new Thread(() -> {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            modelo.setUsuariosMostrados(usuarioDAO.listaUsuarios());
        }).start();
    }
}
