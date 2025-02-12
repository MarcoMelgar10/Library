package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.HeaderDefaultController;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.AgregarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EditarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EliminarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.VisualizarLibro;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    Controlador de la vista principal del maestro Libros, aqui se cargan las vistas de los otros controladores
    (category-card, book-card, etc).

     */

public class BooksViewController implements IVista, PropertyChangeListener {
    @FXML
    GridPane gridFecha;
    @FXML
    CheckBox parametroFecha, parametroDisponible, parametroObservacion;
    @FXML
    BorderPane libroViewContainer;
    @FXML
    TextField buscadorField, fieldDesdeFecha, fieldHastaFecha;
    @FXML
    VBox booksPane;
    @FXML
    VBox categoriesPanel;
    @FXML
    ImageView tipoBusquedaImagen;
    @FXML
    HeaderDefaultController header;
    @FXML
    StackPane buttonBuscar;
    int anioDesde = LocalDate.now().getYear()  -1, anioHasta = LocalDate.now().getYear();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @FXML
    public void initialize(){       //Inicia los componentes
        setHeader();
        ManejadorListaLibros.setPanelDeCarga(booksPane);
        CargadorCategorias.setCategoriasPanel(categoriesPanel);
        support.addPropertyChangeListener(this);
        ManejadorListaLibros.addObserver(this);
        simularDatos();
        initFechaFields();
    }

    /*
    initFechaField() : Define las reestricciones de los campos desde y hasta del parametro de busqueda 'Fecha'.
     */

    private void initFechaFields(){
        fieldDesdeFecha.setText(anioDesde + "");
        fieldHastaFecha.setText(anioHasta + "");
        fieldDesdeFecha.setTextFormatter(new javafx.scene.control.TextFormatter<String>(change -> {
            // Verificar que el texto nuevo sea un número y que no exceda 4 caracteres
            if (change.getControlNewText().matches("\\d{0,4}")) {
                return change;
            }
            return null; // Si no es válido, no se realiza el cambio
        }));

        fieldHastaFecha.setTextFormatter(new javafx.scene.control.TextFormatter<String>(change -> {
            // Verificar que el texto nuevo sea un número y que no exceda 4 caracteres
            if (change.getControlNewText().matches("\\d{0,4}")) {
                return change;
            }
            return null; // Si no es válido, no se realiza el cambio
        }));
    }

    @Override
    public Parent getContainer() {
        return libroViewContainer;
    }

    /*simularDatos() : esta funcion solo fué creada para simular datos de libros y categorias ficticios
    para probar el funcionamiento de los ScrollPane, cuando se tenga disponible la base de datos debe ser eliminada
     */

    public void simularDatos(){
        List<LibroCardData> libros= new ArrayList<>();
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
        categorias.add(new CategoryData(1, "Ciencia Ficcion"));
        categorias.add(new CategoryData(2, "Romanticismo"));
        categorias.add(new CategoryData(3, "Historia Boliviana"));
        categorias.add(new CategoryData(4, "Postulados científicos"));
        categorias.add(new CategoryData(5, "Crecimiento personal"));

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
            if((int)evt.getNewValue() == -1){
                header.cambiarEstadoBotones(false);
            }else {
                header.cambiarEstadoBotones(true);
            }
        }
    }



    @FXML
    public void parametroFechaAction(){
        gridFecha.setDisable(!parametroFecha.isSelected());
    }

    public void setHeader(){
        try{
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/DefaultComponents/header-default.fxml"));
            loader.load();
            HeaderDefaultController headerDefaultController = loader.getController();
            this.header = headerDefaultController;
            header.getEditarButton().setOnMouseClicked(e-> new EditarLibro(ManejadorListaLibros.getCurrentLibro()));
            header.getNuevoButton().setOnMouseClicked(e -> new AgregarLibro());
            header.getEliminarButton().setOnMouseClicked(e -> new EliminarLibro(ManejadorListaLibros.getCurrentLibro()));
            header.getVisualizarButton().setOnMouseClicked(e -> new VisualizarLibro(ManejadorListaLibros.getCurrentLibro()));
            libroViewContainer.setTop(headerDefaultController.getContainer());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
