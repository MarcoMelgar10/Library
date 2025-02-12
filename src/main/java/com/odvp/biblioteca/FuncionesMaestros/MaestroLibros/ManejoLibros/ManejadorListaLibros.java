package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookCardController;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
    clase que se encarga de cargar los libros mediante cards en el ScrollPane del Maestro libros
 */

public class ManejadorListaLibros {
    private static VBox contenedorLibros;
    private static int currentLibro = -1;   //si ningul libro es seleccionado el indice será -1
    private static  List<BookCardController> cards;
    private static final PropertyChangeSupport observerSupport = new PropertyChangeSupport(ManejadorListaLibros.class);
    public static final String CURRENT_LIBRO_OBSERVER = "CURRENT_LIBRO";

    //se establece el panel donde se cargaran los cards
    public static void setPanelDeCarga(VBox vbox){
        contenedorLibros = vbox;
    }

    //recibe una lista de objetos tipo LibroCardData, crea las vistas y las almacena en una lista
    public static void loadBooks(List<LibroCardData> libros){
        cards = new ArrayList<>();
        contenedorLibros.getChildren().removeAll();
        //CAmbiar por query de busqueda de libros
        for(LibroCardData libro: libros){
            try{
                BookCardController bookCard = createView();
                bookCard.initComponents(libro);
                contenedorLibros.getChildren().add(bookCard.getContainer());
                cards.add(bookCard);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //crea la vista (libro card)
    private static BookCardController createView() throws Exception{
        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/BookScene/book-card.fxml"));
        loader.load();
        return loader.getController();
    }


    public static int getCurrentLibro() {
        return currentLibro;
    }

    public static void addObserver(PropertyChangeListener observer){
        observerSupport.addPropertyChangeListener(observer);
    }

    /*
        setCurrentLibro(): actualiza el libro seleccionado (si no se selecciona ninguno se -1)
        aplica el estilo a la card que ha sido pulsada
        informa a su observador que ocurrió un cambio, en este caso el observado es la clase BookView y lo que hace
        al escuchar el cambio es habilitar o deshabilitar sus botones (edit, view, delete)
     */
    public static void setCurrentLibro(int currentLibro) {
        if(ManejadorListaLibros.currentLibro == currentLibro) currentLibro = -1;
        int oldCurrentLibro = ManejadorListaLibros.currentLibro;
        ManejadorListaLibros.currentLibro = currentLibro;
        System.out.println("oldValue: " + oldCurrentLibro + " new: " + ManejadorListaLibros.currentLibro);
        observerSupport.firePropertyChange(CURRENT_LIBRO_OBSERVER, oldCurrentLibro, currentLibro);
        for(BookCardController card : cards){
            if(card.getIDBook() == currentLibro){
                card.getContainer().getStyleClass().add("selected-book-card");
            }
            else{
                card.getContainer().getStyleClass().remove("selected-book-card");
            }
        }
    }
}
