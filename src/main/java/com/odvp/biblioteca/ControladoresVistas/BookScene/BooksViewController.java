package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
    Controlador de la vista principal del maestro Libros, aqui se cargan las vistas de los otros controladores
    (category-card, book-card, etc).

     */

public class BooksViewController implements IVista, PropertyChangeListener {

    @FXML
    BorderPane libroViewContainer;

    private final HeaderLibros header = new HeaderLibros();
    private final ParametersLibros paramsRight = new ParametersLibros();
    private final TableLibros table = new TableLibros();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @FXML
    public void initialize(){       //Inicia los componentes
        libroViewContainer.setTop(header);
        libroViewContainer.setRight(paramsRight);
        libroViewContainer.setCenter(table);
        ManejadorListaLibros.setTable(table);
        CargadorCategorias.setCategoriasPanel(paramsRight.getVentanaCategorias());
        support.addPropertyChangeListener(this);
        ManejadorListaLibros.addObserver(this);
        simularDatos();
    }

    @Override
    public Parent getContainer() {
        return libroViewContainer;
    }

    /*simularDatos() : esta funcion solo fué creada para simular datos de libros y categorias ficticios
    para probar el funcionamiento de los ScrollPane, cuando se tenga disponible la base de datos debe ser eliminada
     */

    public void simularDatos(){
        List<IDatoVisual> libros= new ArrayList<>();
        for(int i=0;i<30;i++) {
            LibroCardData libroData2 = new LibroCardData(
                    i,
                    "La transformacion a través del desarrollo de los años 2000",
                    "Oscar David Valle Pereyra",
                    10,
                    2
            );
            libros.add(libroData2);
        }

        LibroCardData libroData = new LibroCardData(
                30,
                "Las aventuras de los programadores junios",
                "Marco Antonio Melgar Parada",
                10,
                0
        );
        libros.add(libroData);
        List<CategoryData> categorias = new ArrayList<>();

        ManejadorListaLibros.loadBooks(libros);
        CargadorCategorias.setDataList(categorias);

    }

    /*
        Patron observer: detecta cambios en la propiedad currentLibro de la clase ManejadorListaLibros, si ahora
        el libro seleccionado tiene indice -1 (Ninguno) entonces deshabilita los botones (Edicion, Nuevo, Eliminar),
        caso contratrio los habilita y los colorea.
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Cambio");
        if(evt.getPropertyName().equals(ManejadorListaLibros.CURRENT_LIBRO_OBSERVER)){
            if((int)evt.getNewValue() == -1 || (int) evt.getOldValue() == -1){
                header.deshabilitarBotones((int) evt.getNewValue() == -1);
            }
        }
    }
}
