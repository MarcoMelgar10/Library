package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.Objetos.IDatoVisual;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModeloMulta {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private IDatoVisual multaSeleccionada;
    private List<IDatoVisual> multaMostrada;
    public static final String OBS_TEXTO_BUSCADOR = "OBS_TEXTO_BUSCADOR";
    public static final String OBS_MULTA_MOSTRADAS = "OBS_MULTA_MOSTRADAS";
    public static final String OBS_MULTA_SELECCIONADA = "OBS_MULTA_SELECCIONADA";

    public ModeloMulta(){
        multaMostrada = new ArrayList<>();
    }

    public void setMultaMostrada(List<IDatoVisual> multaMostrada){
        List<IDatoVisual> oldDeudaMostrada = List.copyOf(this.multaMostrada);
        this.multaMostrada = multaMostrada;
        support.firePropertyChange(OBS_MULTA_MOSTRADAS, oldDeudaMostrada, this.multaMostrada);
    }


    public void addObserver(PropertyChangeListener observer){
        support.addPropertyChangeListener(observer);
    }

    public void setMultaSeleccionada(IDatoVisual multaSeleccionada){
        if(this.multaSeleccionada == multaSeleccionada) multaSeleccionada = null;
            IDatoVisual oldDeudaSeleccionada = this.multaSeleccionada;
            this.multaSeleccionada = multaSeleccionada;
            support.firePropertyChange(OBS_MULTA_SELECCIONADA, oldDeudaSeleccionada,this.multaSeleccionada);
    }
    public List<IDatoVisual> getMultaMostrada(){
        return multaMostrada;
    }

}
