package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSearcher;
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

    private DefaultSearcher searcher = DefaultSearcher.getDynamicSearcher(ServicioIconos.LIBRO_POR_AUTOR);
    private boolean busquedaPorAutor = false;

    private final Image imagePorAutor = new Image(ServicioIconos.LIBRO_POR_AUTOR);
    private final Image imagePorTitulo = new Image(ServicioIconos.LIBRO_POR_TITULO);

    private ModeloLibros modelo;
    private LibroDAO libroDao = new LibroDAO();

    public HeaderLibros(ModeloLibros modelo) {
        super("BIBLIOTECA EBEN-EZER");

        this.modelo = modelo;
        this.modelo.addObserver(this);

        buttonNew.setOnMouseClicked(e -> new AgregarLibro());
        buttonView.setOnMouseClicked(e -> new VisualizarLibro(modelo.getLibroSeleccionado().getID()));
        buttonEdit.setOnMouseClicked(e -> new EditarLibro(modelo.getLibroSeleccionado().getID()));
        buttonDelete.setOnMouseClicked(e -> new EliminarLibro(modelo.getLibroSeleccionado().getID()));

        deshabilitarBotones(true);
        addButtons(buttonNew,buttonView,buttonEdit,buttonDelete);
        setSearcherContainer(searcher);
        setTipoBusqueda();
        searcher.getBuscador().getStyleClass().remove("text-field-search");
        searcher.getButtonSearcher().setOnMouseClicked(e -> {
            busquedaPorAutor = !busquedaPorAutor;
            setTipoBusqueda();
        });

    }

    public void setTipoBusqueda(){
        if(busquedaPorAutor){
            searcher.setIcon(imagePorAutor);
            searcher.getButtonSearcher().getStyleClass().add("button-purple");
            searcher.getButtonSearcher().getStyleClass().remove("button-orange");
            searcher.getBuscador().getStyleClass().add("text-field-search-purple");
            searcher.getBuscador().getStyleClass().remove("text-field-search-orange");
        }
        else{
            searcher.setIcon(imagePorTitulo);
            searcher.getButtonSearcher().getStyleClass().add("button-orange");
            searcher.getButtonSearcher().getStyleClass().remove("button-purple");
            searcher.getBuscador().getStyleClass().remove("text-field-search-purple");
            searcher.getBuscador().getStyleClass().add("text-field-search-orange");
        }
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
    public void deshabilitarBotones(boolean deshabilitar){
        buttonEdit.desactivar(deshabilitar);
        buttonView.desactivar(deshabilitar);
        buttonDelete.desactivar(deshabilitar);
    }




}
