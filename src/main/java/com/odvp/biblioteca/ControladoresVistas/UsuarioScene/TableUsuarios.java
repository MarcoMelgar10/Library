package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.TableDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.ManejadorTableDefault;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class TableUsuarios extends TableDefault {

    private ModeloUsuarios modelo;

    public TableUsuarios(ModeloUsuarios modelo) {
        super(
                List.of("Estado", "Carnet", "Nombre"),
                List.of(45, 70,300),
                List.of(false,false,true),
                List.of(false,false,false)
        );
        this.modelo = modelo;
    }

    @Override
    public void setCardsAction() {
        for(Card card : getCards()){
            card.getVista().setOnMouseClicked(e-> modelo.setUsuarioSeleccionado(card.getDatoVisual()));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource().equals(ModeloUsuarios.OBS_USUARIOS_MOSTRADOS)){
            addCards(modelo.getUsuariosMostrados());
        }
    }

}
