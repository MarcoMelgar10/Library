package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.BookScene.IFiltro;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.UsuarioData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModeloUsuarios {
    private String textoBusqueda;
    private IDatoVisual usuarioSeleccionado;
    private List<IDatoVisual> usuariosMostrados;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public static final String OBS_TEXTO_BUSCADOR = "OBS_TEXTO_BUSCADOR";
    public static final String OBS_USUARIOS_MOSTRADOS = "OBS_LIBROS_MOSTRADOS";
    public static final String OBS_USUARIO_SELECCIONADO = "OBS_LIBRO_SELECCIONADO";

    public ModeloUsuarios(){
        usuariosMostrados = new ArrayList<>();
    }

    public void addObserver(PropertyChangeListener observer){
        support.addPropertyChangeListener(observer);
    }

    public void setUsuariosMostrados(List<IDatoVisual> usuarios){
        List<IDatoVisual> oldUsuariosMostrados = List.copyOf(usuariosMostrados);
        this.usuariosMostrados = usuarios;
        support.firePropertyChange(OBS_USUARIOS_MOSTRADOS, oldUsuariosMostrados, this.usuariosMostrados);
    }
    public void setUsuarioSeleccionado(IDatoVisual usuarioSeleccionado){
        IDatoVisual oldUsuarioSeleccionado = this.usuarioSeleccionado;
        this.usuarioSeleccionado = usuarioSeleccionado;
        support.firePropertyChange(OBS_USUARIO_SELECCIONADO, oldUsuarioSeleccionado,this.usuarioSeleccionado);
    }

    public UsuarioData getUsuarioSeleccionado() {
        return (UsuarioData) usuarioSeleccionado;
    }

    public List<IDatoVisual> getUsuariosMostrados() {
        return usuariosMostrados;
    }
}
