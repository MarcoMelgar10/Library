package com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios;

import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.UsuarioCardController;
import com.odvp.biblioteca.LibraryApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.List;

public class ManejadorUsuarios {
    private static ObservableList<UsuarioData> usuarios = FXCollections.observableArrayList();
    private static int usuarioActual = -1;
    private static ListView<UsuarioData> listView;
    private static final PropertyChangeSupport observerSupport = new PropertyChangeSupport(ManejadorUsuarios.class);
    public static final String CURRENT_USER_OBSERVER = "CURRENT_USUARIO";

    public static void setContenedor(ListView<UsuarioData> listView) {
        ManejadorUsuarios.listView = listView;
        listView.setItems(usuarios);
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(UsuarioData userData, boolean empty) {
                super.updateItem(userData, empty);
                if (empty || userData == null) {
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/UsuarioScene/usuario-card.fxml"));
                        Pane card = loader.load();
                        UsuarioCardController controller = loader.getController();
                        controller.init(userData);
                        setGraphic(card);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void setData(List<UsuarioData> data) {
        usuarios.setAll(data);
    }

    public static void addObserver(PropertyChangeListener observer) {
        observerSupport.addPropertyChangeListener(observer);
    }

    public static void setCurrentUsuario(int nuevoUsuario) {
        if (usuarioActual == nuevoUsuario) nuevoUsuario = -1;
        int oldCurrentUsuario = usuarioActual;
        usuarioActual = nuevoUsuario;
        System.out.println("oldValue: " + oldCurrentUsuario + " new: " + usuarioActual);
        observerSupport.firePropertyChange(CURRENT_USER_OBSERVER, oldCurrentUsuario, usuarioActual);

        // Resaltar la tarjeta seleccionada
        listView.refresh();
    }
}
