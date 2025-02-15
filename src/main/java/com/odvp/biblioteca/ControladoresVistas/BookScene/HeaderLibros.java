package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.AgregarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EditarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EliminarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.VisualizarLibro;

public class HeaderLibros extends HeaderDefault {

    private ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();
    private ButtonDefault buttonView = ButtonDefault.getButtonView();

    public HeaderLibros() {
        super("BIBLIOTECA EBEN-EZER");

        buttonNew.setOnMouseClicked(e -> new AgregarLibro());
        buttonView.setOnMouseClicked(e -> new VisualizarLibro(ManejadorListaLibros.getCurrentLibro()));
        buttonEdit.setOnMouseClicked(e -> new EditarLibro(ManejadorListaLibros.getCurrentLibro()));
        buttonDelete.setOnMouseClicked(e -> new EliminarLibro(ManejadorListaLibros.getCurrentLibro()));

        deshabilitarBotones(true);
        addButtons(buttonNew,buttonView,buttonEdit,buttonDelete);
    }

    @Override
    public void deshabilitarBotones(boolean deshabilitar){
        buttonEdit.desactivar(deshabilitar);
        buttonView.desactivar(deshabilitar);
        buttonDelete.desactivar(deshabilitar);
    }


}
