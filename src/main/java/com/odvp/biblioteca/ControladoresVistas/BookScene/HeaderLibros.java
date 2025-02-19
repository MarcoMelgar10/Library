package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ButtonDefault;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.DefaultSearcher;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.AgregarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EditarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EliminarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.VisualizarLibro;
import com.odvp.biblioteca.Servicios.ServicioIconos;
import javafx.scene.image.Image;

import java.util.Objects;

public class HeaderLibros extends HeaderDefault {

    private ButtonDefault buttonNew = ButtonDefault.getButtonNew();
    private ButtonDefault buttonEdit = ButtonDefault.getButtonEdit();
    private ButtonDefault buttonDelete = ButtonDefault.getButtonDelete();
    private ButtonDefault buttonView = ButtonDefault.getButtonView();

    private DefaultSearcher searcher = DefaultSearcher.getDynamicSearcher(ServicioIconos.LIBRO_POR_AUTOR);
    private boolean busquedaPorAutor = false;

    private final Image imagePorAutor = new Image(ServicioIconos.LIBRO_POR_AUTOR);
    private final Image imagePorTitulo = new Image(ServicioIconos.LIBRO_POR_TITULO);

    public HeaderLibros() {
        super("BIBLIOTECA EBEN-EZER");

        buttonNew.setOnMouseClicked(e -> new AgregarLibro());
        buttonView.setOnMouseClicked(e -> new VisualizarLibro(ManejadorListaLibros.getCurrentLibro()));
        buttonEdit.setOnMouseClicked(e -> new EditarLibro(ManejadorListaLibros.getCurrentLibro()));
        buttonDelete.setOnMouseClicked(e -> new EliminarLibro(ManejadorListaLibros.getCurrentLibro()));

        deshabilitarBotones(true);
        addButtons(buttonNew,buttonView,buttonEdit,buttonDelete);
        getChildren().add(searcher);
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
    public void deshabilitarBotones(boolean deshabilitar){
        buttonEdit.desactivar(deshabilitar);
        buttonView.desactivar(deshabilitar);
        buttonDelete.desactivar(deshabilitar);
    }




}
