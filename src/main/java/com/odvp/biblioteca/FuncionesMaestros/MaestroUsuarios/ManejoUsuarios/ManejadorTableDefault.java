package com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.TableDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ManejadorTableDefault {
    private TableDefault table;
    private int idActual = -1;
    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(ManejadorTableDefault.class);
    public final String CURRENT_DATO_OBSERVER = "CURRENT_DATO";

    public ManejadorTableDefault(TableDefault table){
        this.table = table;
    }
    public void setDatos(List<IDatoVisual> data) {
        table.addCards(data);
    }

    public void addObserver(PropertyChangeListener observer) {
        observerSupport.addPropertyChangeListener(observer);
    }

    public void setCurrentID(int nuevoId) {
        if (idActual == nuevoId) nuevoId = -1;
        int oldCurrentId = idActual;
        idActual = nuevoId;
        System.out.println("oldValue: " + oldCurrentId + " new: " + idActual);
        observerSupport.firePropertyChange(CURRENT_DATO_OBSERVER, oldCurrentId, idActual);
    }
}
