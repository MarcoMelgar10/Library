package com.odvp.biblioteca.LibrosClasses.ManejoLibros;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookCardController;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ManejadorListaLibros {
    public static final String CURRENT_LIBRO_OBSERVER = "CURRENT_LIBRO";
    private static VBox contenedorLibros;
    private static int currentLibro = -1;
    private static  List<BookCardController> cards;
    private static final PropertyChangeSupport observerSupport = new PropertyChangeSupport(ManejadorListaLibros.class);
    public static void setPanelDeCarga(VBox borderPane){
        contenedorLibros = borderPane;
    }
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

    private static BookCardController createView() throws Exception{
        FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("BookScene/book-card.fxml"));
        loader.load();
        return loader.getController();
    }

    public static int getCurrentLibro() {
        return currentLibro;
    }

    public static void addObserver(PropertyChangeListener observer){
        observerSupport.addPropertyChangeListener(observer);
    }

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
