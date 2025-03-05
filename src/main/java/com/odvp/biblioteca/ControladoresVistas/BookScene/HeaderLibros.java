package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultDynamicSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSimpleSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.AgregarLibro.AgregarLibro;
import com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EditarLibro.EditarLibro;
import com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.EliminarLibro.EliminarLibro;
import com.odvp.biblioteca.ControladoresVistas.BookScene.OperacionesLibro.VisualizarLibro.VisualizarLibro;
import com.odvp.biblioteca.Servicios.ServicioIconos;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import javafx.scene.image.Image;

import java.beans.PropertyChangeEvent;

public class HeaderLibros extends HeaderDefault {

    private ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();
    private ButtonDefault buttonView = ButtonDefault.getButtonView();

    private SearcherLibro searcher;
    private ModeloLibros modelo;

    public HeaderLibros(ModeloLibros modelo) {
        super("BIBLIOTECA EBEN-EZER");

        this.modelo = modelo;
        this.modelo.addObserver(this);
        searcher = new SearcherLibro(this.modelo);

        deshabilitarBotones(true);
        addButtons(buttonNew,buttonView,buttonEdit,buttonDelete);
        setSearcherContainer(searcher);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(ModeloLibros.OBS_LIBRO_SELECCIONADO)){
            if(evt.getOldValue() == null){
                deshabilitarBotones(false);
            }
            if(evt.getNewValue() == null){
                deshabilitarBotones(true);
            }
        }
    }

    @Override
    public void setButtonsAction(){
        buttonNew.setOnMouseClicked(e -> new AgregarLibro(modelo));
        buttonView.setOnMouseClicked(e -> new VisualizarLibro(modelo));
        buttonEdit.setOnMouseClicked(e -> new EditarLibro(modelo));
        buttonDelete.setOnMouseClicked(e -> new EliminarLibro(modelo));
    }

    @Override
    public void deshabilitarBotones(boolean deshabilitar){
        buttonEdit.desactivar(deshabilitar);
        buttonView.desactivar(deshabilitar);
        buttonDelete.desactivar(deshabilitar);
    }




}
