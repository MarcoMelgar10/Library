package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultDynamicSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSimpleSearcher;
import com.odvp.biblioteca.Servicios.ServicioIconos;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class SearcherUsuario extends DefaultSimpleSearcher {

    private final ModeloUsuarios modelo;

    public SearcherUsuario(ModeloUsuarios modelo){
        super();
        this.modelo = modelo;
    }
    @Override
    public void setBuscadorAction(){
        buscador.textProperty().addListener((observable, oldValuem, newValue) -> {
            modelo.setTextoBusqueda(newValue);
        });
    }
}
