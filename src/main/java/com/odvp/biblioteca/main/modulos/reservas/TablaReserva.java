package com.odvp.biblioteca.main.modulos.reservas;

import com.odvp.biblioteca.main.modulos.defaulltComponents.TableDefault;
import com.odvp.biblioteca.main.modulos.deudas.ModeloMulta;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Objects;

public class TablaReserva extends TableDefault {
    private ModeloReserva modelo;
    public TablaReserva(ModeloReserva modelo) {
        super(
                List.of("Estado", "Codigo", "Usuario", "Fecha"),
                List.of(50, 70, 500, 50),
                List.of(false, false, false, false),
                List.of(false, true, false, false));
        this.modelo = modelo;
        this.modelo.addObserver(this);
    }
    @Override
    public void setCardsAction() {
        for(Card card : getCards()){
            card.getVista().setOnMouseClicked(e -> modelo.setReservaSeleccionada(card.getDatoVisual()));
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if(Objects.equals(evt.getPropertyName(), ModeloReserva.OBS_RESERVA_MOSTRADAS)){
            addCards(modelo.getReservaMostrada());
        }
        if(evt.getPropertyName().equals(ModeloReserva.OBS_RESERVA_SELECCIONADA)){
            for(Card card : getCards()){
                card.setSelected(card.getDatoVisual().equals(evt.getNewValue()));
            }
        }
    }
}
