package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;

import java.beans.PropertyChangeEvent;

public class HeaderUsuarios extends HeaderDefault {
    private final ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private final ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private final ButtonDefault buttonView = ButtonDefault.getButtonView();
    private final ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();

    private final DefaultSearcher searcher = DefaultSearcher.getSimpleSearcher();
    private ModeloUsuarios modelo;

    public HeaderUsuarios(ModeloUsuarios modelo) {
        super("USUARIOS");
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
        if(evt.getPropertyName().equals(ModeloUsuarios.OBS_USUARIO_SELECCIONADO)){
            System.out.println(evt.getNewValue());
            if(evt.getOldValue() != null && evt.getNewValue() != null) return;
            deshabilitarBotones(evt.getNewValue() == null);
        }
    }
}
