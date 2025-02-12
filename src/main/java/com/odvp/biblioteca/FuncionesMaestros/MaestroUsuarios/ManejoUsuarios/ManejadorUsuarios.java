package com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookCardController;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.UsuarioCardController;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.Usuario;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuarios {
    private static List<UsuarioCardController> usuarioCards;
    private static int usuarioActual = -1;
    private static FlowPane contenedor;
    private static final PropertyChangeSupport observerSupport = new PropertyChangeSupport(ManejadorListaLibros.class);
    public static final String CURRENT_USER_OBSERVER = "CURRENT_LIBRO";

    public static void setContenedor(FlowPane contenedor) {
        ManejadorUsuarios.contenedor = contenedor;
    }

    public static void setData(List<UsuarioData> data){
        usuarioCards = new ArrayList<>();
        for(UsuarioData userData : data){
            try {
                UsuarioCardController userController = createView();
                userController.init(userData);
                contenedor.getChildren().add(userController.getContainer());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static UsuarioCardController createView() throws Exception{
        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/UsuarioScene/usuario-card.fxml"));
        loader.load();
        return loader.getController();
    }

    public static void addObserver(PropertyChangeListener observer){
        observerSupport.addPropertyChangeListener(observer);
    }

    public static void setCurrentLibro(int nuevoUsuario) {
        if(usuarioActual == nuevoUsuario) nuevoUsuario = -1;
        int oldCurrentLibro = usuarioActual;
        usuarioActual = nuevoUsuario;
        System.out.println("oldValue: " + oldCurrentLibro + " new: " + usuarioActual);
        observerSupport.firePropertyChange(CURRENT_USER_OBSERVER, oldCurrentLibro, usuarioActual);
        for(UsuarioCardController card : usuarioCards){
            if(card.getId() == nuevoUsuario){
                card.getContainer().getStyleClass().add("selected-book-card");
            }
            else{
                card.getContainer().getStyleClass().remove("selected-book-card");
            }
        }
    }
}
