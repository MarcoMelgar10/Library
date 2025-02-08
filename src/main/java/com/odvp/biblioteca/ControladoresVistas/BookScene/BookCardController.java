package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.LibrosClasses.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.LibrosClasses.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.ControladoresVistas.IVista;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class BookCardController implements IVista {
    @FXML
    Label disponibleBook, autorBook, titleBook, stockBook;
    @FXML
    ImageView bookLegend;
    @FXML
    HBox bookCardContainer;

    private int IDBook;
    @Override
    public Parent getContainer() {
        return bookCardContainer;
    }
    public void initComponents(LibroCardData libroData){
        IDBook = libroData.getID();
        bookLegend.setImage(libroData.getImage());
        titleBook.setText(libroData.getTitulo());
        autorBook.setText(libroData.getAutor());
        stockBook.setText(libroData.getStock() + "");
        disponibleBook.setText(libroData.getStockDisponible() + "");
        bookCardContainer.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(bookCardContainer, Priority.ALWAYS);
    }
    @FXML
    public void setSelectedBook(){
        ManejadorListaLibros.setCurrentLibro(IDBook);
    }

    public int getIDBook() {
        return IDBook;
    }
}
