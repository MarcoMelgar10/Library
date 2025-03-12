package com.odvp.biblioteca.main.barraOpciones;

import com.odvp.biblioteca.main.MainEscena;
import com.odvp.biblioteca.main.ModeloMain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*
    Esta clase se encarga de guardar las opciones establecidas en el OpcionServicio
    y asociarla a el controlador de cada card de opcion que se ve a la izquierda en la app.
    Cuando una card de opcion ese seleccionada se colorea y el resto se pone sin color.
*/

public class CargadorModulo implements PropertyChangeListener {

    private MainEscena escenePrincipal;
    private ModeloMain modelo;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CargadorModulo(MainEscena escenePrincipal, ModeloMain modelo){
        this.modelo = modelo;
        this.escenePrincipal = escenePrincipal;

        support.addPropertyChangeListener(this);
        this.modelo.addObserver(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals(ModeloMain.OBS_CURRENT_MODULO)){
            switch(evt.getNewValue().toString()) {
                case ModeloMain.MODULO_LIBROS:
                    escenePrincipal.setCenter(ServicioModulos.getModuloLibros());
                    break;
                case ModeloMain.MODULO_AUTORES:
                    escenePrincipal.setCenter(ServicioModulos.getModuloAutores());
                    break;
                case ModeloMain.MODULO_DEUDAS:
                    escenePrincipal.setCenter(ServicioModulos.getModuloMulta());
                    break;
                case ModeloMain.MODULO_USUARIOS:
                    escenePrincipal.setCenter(ServicioModulos.getModuloUsuarios());
                    break;
                case ModeloMain.MODULO_PRESTAMOS:
                    escenePrincipal.setCenter(null);
                    break;
                case ModeloMain.MODULO_RESERVAS:
                    escenePrincipal.setCenter(null);
                    break;
            }
        }
    }
}
