package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros;

import com.odvp.biblioteca.ControladoresVistas.BookScene.TableLibros;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.TableDefault;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/*
    clase que se encarga de cargar los libros mediante cards en el ScrollPane del Maestro libros
 */

public class ManejadorListaLibros {
    private static ManejadorListaLibros instance;
    private TableLibros tableLibros;
    private int currentLibro = -1;   //si ningul libro es seleccionado el indice será -1

    private final PropertyChangeSupport observerSupport = new PropertyChangeSupport(ManejadorListaLibros.class);
    public static final String CURRENT_LIBRO_OBSERVER = "CURRENT_LIBRO";

    private ManejadorListaLibros(){
    }

    public static ManejadorListaLibros getInstance(){
        if(instance == null){
            instance = new ManejadorListaLibros();
        }
        return instance;
    }

    //se establece el panel donde se cargaran los cards
    public void setTable(TableLibros tableLibros){
       this.tableLibros = tableLibros;
    }

    //recibe una lista de objetos tipo LibroCardData, crea las vistas y las almacena en una lista
    public void loadBooks(List<IDatoVisual> libros){
        tableLibros.addCards(libros);
    }

    public int getCurrentLibro() {
        return currentLibro;
    }

    public void addObserver(PropertyChangeListener observer){
        observerSupport.addPropertyChangeListener(observer);
    }

    /*
        setCurrentLibro(): actualiza el libro seleccionado (si no se selecciona ninguno se -1)
        aplica el estilo a la card que ha sido pulsada
        informa a su observador que ocurrió un cambio, en este caso el observado es la clase BookView y lo que hace
        al escuchar el cambio es habilitar o deshabilitar sus botones (edit, view, delete)
     */
    public void setCurrentLibro(int currentLibro) {
        if(this.currentLibro == currentLibro) currentLibro = -1;
        int oldCurrentLibro = this.currentLibro;
        this.currentLibro = currentLibro;
        System.out.println("oldValue: " + oldCurrentLibro + " new: " + this.currentLibro);
        observerSupport.firePropertyChange(CURRENT_LIBRO_OBSERVER, oldCurrentLibro, currentLibro);
        for(TableDefault.Card card : tableLibros.getCards()){
            //card.setSelected(card.getID() == currentLibro);
        }
    }


}
