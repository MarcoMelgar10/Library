package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.List;

public class HeaderDefaultController implements IVista {
    @FXML
    StackPane visualizarButton, eliminarButton, editarButton, busquedaDinamicaButton, nuevoButton;
    @FXML
    HBox container;
    @FXML
    TextField buscadorField;
    private boolean busquedaPorTitulo = true;
    @FXML
    ImageView tipoBusquedaImagen;
    private final Image textoImagen = new Image(getClass().getResource("/com/odvp/biblioteca/Icons/LibrosResources/texto.png").toExternalForm());
    private final Image autorImagen = new Image(getClass().getResource("/com/odvp/biblioteca/Icons/LibrosResources/pluma-pluma.png").toExternalForm());
    Tooltip autorTool = new Tooltip("Busqueda por Autor");
    Tooltip textoTool = new Tooltip("Busqueda por Título");

    public void initialize(){
        busquedaDinamicaButton.setDisable(true);
        busquedaDinamicaButton.setOpacity(0);
    }

    public void cambiarEstadoBotones(boolean habilitar){
        if(habilitar){
            editarButton.getStyleClass().remove("button-disable");
            eliminarButton.getStyleClass().remove("button-disable");
            visualizarButton.getStyleClass().remove("button-disable");
            editarButton.setDisable(false);
            eliminarButton.setDisable(false);
            visualizarButton.setDisable(false);
        }else {
            editarButton.getStyleClass().add("button-disable");
            eliminarButton.getStyleClass().add("button-disable");
            visualizarButton.getStyleClass().add("button-disable");
            editarButton.setDisable(true);
            eliminarButton.setDisable(true);
            visualizarButton.setDisable(true);
        }
    }

    @Override
    public Parent getContainer() {
        return container;
    }
    
    /*tipoDeBusqueda():
        cambia el tipo de busqueda y el estilo del botón a la derecha del textFiel de busqueda
     */

    @FXML
    public void tipoDeBusquedaButtonAction(){

        busquedaPorTitulo = !busquedaPorTitulo;
        if(busquedaPorTitulo) {
            tipoBusquedaImagen.setImage(textoImagen);
            Tooltip.uninstall(busquedaDinamicaButton,autorTool);
            Tooltip.install(busquedaDinamicaButton,textoTool);
            busquedaDinamicaButton.getStyleClass().add("button-orange");
            busquedaDinamicaButton.getStyleClass().remove("button-purple");
            buscadorField.getStyleClass().add("text-field-search-orange");
            buscadorField.getStyleClass().remove("text-field-search-purple");

        }else {
            tipoBusquedaImagen.setImage(autorImagen);
            Tooltip.uninstall(busquedaDinamicaButton,textoTool);
            Tooltip.install(busquedaDinamicaButton,autorTool);
            busquedaDinamicaButton.getStyleClass().add("button-purple");
            busquedaDinamicaButton.getStyleClass().remove("button-orange");
            buscadorField.getStyleClass().remove("text-field-search-orange");
            buscadorField.getStyleClass().add("text-field-search-purple");
        }
    }

    public StackPane getEliminarButton() {
        return eliminarButton;
    }

    public StackPane getEditarButton() {
        return editarButton;
    }

    public StackPane getVisualizarButton() {
        return visualizarButton;
    }

    public StackPane getNuevoButton() {
        return nuevoButton;
    }
}
