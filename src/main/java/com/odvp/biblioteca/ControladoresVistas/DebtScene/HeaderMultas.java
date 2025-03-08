package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Agregar.AgregarMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Editar.EditarMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Eliminar.EliminarMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Visualizar.VisualizarMulta;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSimpleSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.Servicios.ServicioIconos;
import javafx.scene.control.Button;

import java.beans.PropertyChangeEvent;

public class HeaderMultas extends HeaderDefault {
    private ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();
    private ButtonDefault buttonView = ButtonDefault.getButtonView();
    private SearcherMulta searcher = new SearcherMulta();
    private ModeloMulta modelo;

    public HeaderMultas(ModeloMulta modelo) {
        super("Multas");
        this.modelo = modelo;
        this.modelo.addObserver(this);
      /*  Button cancelarMultaButton = new Button("$");
        cancelarMultaButton.setPrefWidth(50);
        cancelarMultaButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
      */
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
