package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefaultController;
import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.AgregarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EditarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EliminarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.VisualizarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.ManejadorUsuarios;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.UsuarioData;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class UsuariosViewController implements PropertyChangeListener, IVista {
    @FXML
    BorderPane container;
    @FXML
    FlowPane panelUsuarios;

    HeaderDefaultController header;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void initialize(){
        setHeader();
        ManejadorUsuarios.setContenedor(panelUsuarios);
        support.addPropertyChangeListener(this);
        ManejadorUsuarios.addObserver(this);
        setDatosPrueba();
    }

    public void setHeader(){
        try{
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/DefaultComponents/header-default.fxml"));
            loader.load();
            HeaderDefaultController headerDefaultController = loader.getController();
            this.header = headerDefaultController;
            header.getEditarButton().setOnMouseClicked(e-> new EditarLibro(ManejadorListaLibros.getCurrentLibro()));
            header.getNuevoButton().setOnMouseClicked(e -> new AgregarLibro());
            header.getEliminarButton().setOnMouseClicked(e -> new EliminarLibro(ManejadorListaLibros.getCurrentLibro()));
            header.getVisualizarButton().setOnMouseClicked(e -> new VisualizarLibro(ManejadorListaLibros.getCurrentLibro()));
            container.setTop(headerDefaultController.getContainer());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setDatosPrueba(){
        List<UsuarioData> usuarioDatos = new ArrayList<>();
        for(int i=0; i<20; i++){
            usuarioDatos.add(new UsuarioData(i, "Oscar David Valle Pereyra", true));
        }
        ManejadorUsuarios.setData(usuarioDatos);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(ManejadorUsuarios.CURRENT_USER_OBSERVER)){
            header.cambiarEstadoBotones((int) evt.getNewValue() == -1);
        }
    }

    @Override
    public Parent getContainer() {
        return container;
    }
}
