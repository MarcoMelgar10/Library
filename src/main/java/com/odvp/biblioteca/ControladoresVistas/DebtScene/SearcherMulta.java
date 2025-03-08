package com.odvp.biblioteca.ControladoresVistas.DebtScene;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSimpleSearcher;

import java.beans.PropertyChangeListener;


public class SearcherMulta extends DefaultSimpleSearcher {
    private ModeloMulta modelo;
    public SearcherMulta(){
    }
    public SearcherMulta(ModeloMulta modelo){
        super();
        this.modelo = modelo;
        modelo.addObserver((PropertyChangeListener) this);
    }


    @Override
    public void setBuscadorAction(){
        buscador.textProperty().addListener((observable, oldValuem, newValue) -> {
            modelo.setTextoBusqueda(newValue);
        });
    }


}
