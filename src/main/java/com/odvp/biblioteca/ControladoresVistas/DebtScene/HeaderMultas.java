package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Agregar.AgregarMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Editar.EditarMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Eliminar.EliminarMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Visualizar.VisualizarMulta;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSimpleSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Servicios.ServicioBotones;
import com.odvp.biblioteca.Servicios.ServicioIconos;

import java.beans.PropertyChangeEvent;

public class HeaderMultas extends HeaderDefault {
    private final ButtonDefault buttonNew = ServicioBotones.createBotonAgregar();
    private final ButtonDefault buttonEdit = ServicioBotones.createButtonEditar();
    private final ButtonDefault buttonView = ServicioBotones.createButtonVisualizar();
    private final ButtonDefault buttonDelete = ServicioBotones.createButtonEliminar();
    private DefaultSimpleSearcher searcher = new DefaultSimpleSearcher();
    private ModeloMulta modelo;

    public HeaderMultas(ModeloMulta modelo) {
        super("Multas");
        this.modelo = modelo;
        this.modelo.addObserver(this);
        addButtons(buttonNew, buttonView, buttonEdit, buttonDelete);
        deshabilitarBotones(true);
        setSearcherContainer(searcher);
        buttonNew.setOnMouseClicked(e -> new AgregarMulta(modelo));
        buttonDelete.setOnMouseClicked(e -> new EliminarMulta(modelo));
        buttonView.setOnMouseClicked(e -> new VisualizarMulta(modelo));
        buttonEdit.setOnMouseClicked(e -> new EditarMulta(modelo));
    }

        @Override
        public void deshabilitarBotones(boolean deshabilitar){
            buttonEdit.desactivar(deshabilitar);
            buttonView.desactivar(deshabilitar);
            buttonDelete.desactivar(deshabilitar);
        }
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals(ModeloMulta.OBS_MULTA_SELECCIONADA())){
                if(evt.getOldValue() != null && evt.getNewValue() != null) return;
                deshabilitarBotones(evt.getNewValue() == null);
            }
        }
    }
