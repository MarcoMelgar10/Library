package com.odvp.biblioteca.ControladoresVistas.AutorScene;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.AgregarAutor.AgregarAutor;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EditarAutor.EditarAutor;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.EliminarAutor.EliminarAutor;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.OperacionesAutores.VisualizarUsuario.VisualizarAutor;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.Servicios.ServicioBotones;

import java.beans.PropertyChangeEvent;

public class HeaderAutores extends HeaderDefault {

    private final ButtonDefault agregarButton = ServicioBotones.createBotonAgregar();
    private final ButtonDefault editarButton = ServicioBotones.createButtonEditar();
    private final ButtonDefault visualizarButton = ServicioBotones.createButtonVisualizar();
    private final ButtonDefault eliminarButton = ServicioBotones.createButtonEliminar();


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
