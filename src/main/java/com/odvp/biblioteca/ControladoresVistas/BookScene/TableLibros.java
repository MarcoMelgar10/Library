package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.TableDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableLibros extends TableDefault {

    public TableLibros() {
        super(
                List.of("Estado", "Titulo", "Autor", "Stock", "Disponible"),
                List.of(50,200,100,70,70),
                List.of(false,true,true,false,false),
                List.of(false,false,false,true,true)
        );
    }
    @Override
    public void setCardsAction(){
        for(Card card : getCards()){
            card.getVista().setOnMouseClicked(e -> ManejadorListaLibros.getInstance().setCurrentLibro(card.getID()));
        }
    }
}
