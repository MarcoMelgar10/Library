package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;

public class HeaderUsuarios extends HeaderDefault {
    private final ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private final ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private final ButtonDefault buttonView = ButtonDefault.getButtonView();
    private final ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();


    public HeaderUsuarios() {
        super("USUARIOS");
        addButtons(buttonNew, buttonView, buttonEdit, buttonDelete);
        deshabilitarBotones(true);
    }

    @Override
    public void deshabilitarBotones(boolean deshabilitar){
        buttonEdit.desactivar(deshabilitar);
        buttonView.desactivar(deshabilitar);
        buttonDelete.desactivar(deshabilitar);
    }

}
