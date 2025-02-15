package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.ManejadorUsuarios;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.UsuarioData;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class UsuariosViewController implements PropertyChangeListener, IVista {
    @FXML
    BorderPane container;
    @FXML
    ListView<UsuarioData> panelUsuarios;

    HeaderUsuarios header = new HeaderUsuarios();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void initialize(){
        container.setTop(header);
        ManejadorUsuarios.setContenedor(panelUsuarios);
        support.addPropertyChangeListener(this);
        ManejadorUsuarios.addObserver(this);
        setDatosPrueba();
    }

    public void setDatosPrueba(){
        List<UsuarioData> usuarioDatos = new ArrayList<>();
        for(int i=0; i<200; i++){
            usuarioDatos.add(new UsuarioData(i, "Oscar David Valle Pereyra", false));
        }
        ManejadorUsuarios.setData(usuarioDatos);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("observer usuario");
        if(evt.getPropertyName().equals(ManejadorUsuarios.CURRENT_USER_OBSERVER)){
            header.deshabilitarBotones((int) evt.getNewValue() == -1);
        }
    }

    @Override
    public Parent getContainer() {
        return container;
    }
}
