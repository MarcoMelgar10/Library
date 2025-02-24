package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Servicios.ServicioIconos;

import java.beans.PropertyChangeEvent;

public class HeaderMultas extends HeaderDefault {
    private ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();
    private ButtonDefault buttonView = ButtonDefault.getButtonView();
    private DefaultSearcher searcher = DefaultSearcher.getDynamicSearcher(ServicioIconos.OPCION_MODULO_DEUDAS);
    private ModeloMulta modelo;
    public HeaderMultas(ModeloMulta modelo) {
        super("Biblioteca Eben Ezer");
        this.modelo = modelo;
        this.modelo.addObserver(this);
        addButtons(buttonNew, buttonView, buttonEdit, buttonDelete);
        deshabilitarBotones(true);
        setSearcherContainer(searcher);

    }

        @Override
        public void deshabilitarBotones(boolean deshabilitar){
            buttonEdit.desactivar(deshabilitar);
            buttonView.desactivar(deshabilitar);
            buttonDelete.desactivar(deshabilitar);
        }
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals(modelo.getMultaMostrada())){
                if(evt.getOldValue() != null && evt.getNewValue() != null) return;
                deshabilitarBotones(evt.getNewValue() == null);
            }
        }

    }
