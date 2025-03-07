package com.odvp.biblioteca.ControladoresVistas.AutorScene;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.AgregarAutor.AgregarAutor;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EditarAutor.EditarAutor;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EliminarUsuario.EliminarAutor;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.VisualizarUsuario.VisualizarAutor;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;

import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HeaderAutores extends HeaderDefault {

    private final ButtonDefault agregarButton = ButtonDefault.getButtonNew();
    private final ButtonDefault editarButton = ButtonDefault.getButtonEdit();
    private final ButtonDefault visualizarButton = ButtonDefault.getButtonView();
    private final ButtonDefault eliminarButton = ButtonDefault.getButtonDelete();

    private ModeloAutores modelo;

    public HeaderAutores(ModeloAutores modelo) {
        super("Autores");
        this.modelo = modelo;
        this.modelo.addObserver(this);
        agregarButton.setOnMouseClicked(e -> new AgregarAutor(this.modelo));
        editarButton.setOnMouseClicked(e -> new EditarAutor(this.modelo));
        visualizarButton.setOnMouseClicked(e -> new VisualizarAutor(this.modelo));
        eliminarButton.setOnMouseClicked(e -> new EliminarAutor(this.modelo));
        addButtons(agregarButton, visualizarButton, editarButton, eliminarButton);

    }

    @Override
    public void deshabilitarBotones(boolean deshabilitar) {

        editarButton.desactivar(deshabilitar);
        visualizarButton.desactivar(deshabilitar);
        eliminarButton.desactivar(deshabilitar);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(ModeloAutores.OBS_AUTOR_SELECCIONADO)){
            if(evt.getOldValue() != null && evt.getNewValue() != null) return;
            deshabilitarBotones(evt.getNewValue() == null);
        }
    }
}
