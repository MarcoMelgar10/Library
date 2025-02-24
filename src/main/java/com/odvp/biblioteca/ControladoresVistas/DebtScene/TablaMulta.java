package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.TableDefault;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import javafx.scene.Node;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;

public class TablaMulta extends TableDefault {

    private ModeloMulta modelo;
    public TablaMulta(ModeloMulta modelo) {
        super(
                List.of("Estado", "Codigo", "Usuario", "Monto"),
                List.of(50,100,150,70),
                List.of(false, false, true, false),
                List.of(false, false, true, true));
     this.modelo = modelo;
     this.modelo.addObserver(this);
    }


    @Override
    public void setCardsAction() {
        for(Card card : getCards()){
        card.getVista().setOnMouseClicked(e -> modelo.setMultaSeleccionada(card.getDatoVisual()));
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if(Objects.equals(evt.getPropertyName(), ModeloMulta.OBS_MULTA_MOSTRADAS)){
            addCards(modelo.getMultaMostrada());
        }
        if(evt.getPropertyName().equals(ModeloMulta.OBS_MULTA_SELECCIONADA)){
            for(Card card : getCards()){
                card.setSelected(card.getDatoVisual().equals(evt.getNewValue()));
            }
        }
    }


}
