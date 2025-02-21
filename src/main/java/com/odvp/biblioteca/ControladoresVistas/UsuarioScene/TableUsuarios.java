package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.TableDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.ManejadorTableDefault;

import java.util.List;

public class TableUsuarios extends TableDefault {


    public TableUsuarios() {
        super(
                List.of("Estado", "Carnet", "Nombre"),
                List.of(45, 70,300),
                List.of(false,false,true),
                List.of(false,false,false)
        );
    }

    @Override
    public void setCardsAction() {
        for(Card card : getCards()){

        }
    }


}
