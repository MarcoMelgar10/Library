package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSimpleSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.AgregarUsuario.AgregarUsuario;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.EditarUsuario.EditarUsuario;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.EliminarUsuario.EliminarUsuario;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.OperacionesUsuario.VisualizarUsuario.VisualizarUsuario;

import java.beans.PropertyChangeEvent;

public class HeaderUsuarios extends HeaderDefault {
    private final ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private final ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private final ButtonDefault buttonView = ButtonDefault.getButtonView();
    private final ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();

    private final SearcherUsuario searcher;
    private ModeloUsuarios modelo;

    public HeaderUsuarios(ModeloUsuarios modelo) {
        super("USUARIOS");
        this.modelo = modelo;
        this.modelo.addObserver(this);
        searcher = new SearcherUsuario(modelo);
        buttonNew.setOnMouseClicked(e-> new AgregarUsuario(modelo));
        buttonEdit.setOnMouseClicked(e -> new EditarUsuario(modelo));
        buttonView.setOnMouseClicked(e->new VisualizarUsuario(modelo));
        buttonDelete.setOnMouseClicked(e->new EliminarUsuario(modelo));
        addButtons(buttonNew, buttonView, buttonEdit, buttonDelete);
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
            if(evt.getOldValue() != null && evt.getNewValue() != null) return;
            deshabilitarBotones(evt.getNewValue() == null);
        }
    }
}
