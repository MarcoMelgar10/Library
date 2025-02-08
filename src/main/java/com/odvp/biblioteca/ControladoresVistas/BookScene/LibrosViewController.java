package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.LibrosClasses.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.LibrosClasses.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.LibrosClasses.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.LibrosClasses.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.LibrosClasses.OperacionesLibro.AgregarLibro;
import com.odvp.biblioteca.LibrosClasses.OperacionesLibro.EditarLibro;
import com.odvp.biblioteca.LibrosClasses.OperacionesLibro.EliminarLibro;
import com.odvp.biblioteca.LibrosClasses.OperacionesLibro.VisualizarLibro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrosViewController implements IVista, PropertyChangeListener {
    @FXML
    GridPane gridFecha;
    @FXML
    CheckBox parametroFecha, parametroDisponible, parametroObservacion;
    @FXML
    VBox libroViewContainer;
    @FXML
    TextField buscadorField, fieldDesdeFecha, fieldHastaFecha;
    @FXML
    VBox booksPane;
    @FXML
    StackPane visualizarButton, eliminarButton, editarButton;
    @FXML
    VBox categoriesPanel;
    @FXML
    ImageView tipoBusquedaImagen;
    Tooltip autorTool = new Tooltip("Busqueda por Autor");
    Tooltip textoTool = new Tooltip("Busqueda por Título");
    @FXML
    StackPane buttonBuscar;
    int anioDesde = LocalDate.now().getYear()  -1, anioHasta = LocalDate.now().getYear();

    private boolean busquedaPorTitulo = true;
    private final Image textoImagen = new Image(getClass().getResource("/Icons/LibrosResources/texto.png").toExternalForm());
    private final Image autorImagen = new Image(getClass().getResource("/Icons/LibrosResources/pluma-pluma.png").toExternalForm());

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @FXML
    public void initialize(){
        Tooltip.install(buttonBuscar,textoTool);
        ManejadorListaLibros.setPanelDeCarga(booksPane);
        CargadorCategorias.setCategoriasPanel(categoriesPanel);
        support.addPropertyChangeListener(this);
        ManejadorListaLibros.addObserver(this);
        simularDatos();
        initFechaFields();
    }


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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Cambio");
        if(evt.getPropertyName().equals(ManejadorListaLibros.CURRENT_LIBRO_OBSERVER)){
            if((int)evt.getNewValue() == -1){
                editarButton.getStyleClass().add("button-disable");
                eliminarButton.getStyleClass().add("button-disable");
                visualizarButton.getStyleClass().add("button-disable");
                editarButton.setDisable(true);
                eliminarButton.setDisable(true);
                visualizarButton.setDisable(true);
            }else {
                editarButton.getStyleClass().remove("button-disable");
                eliminarButton.getStyleClass().remove("button-disable");
                visualizarButton.getStyleClass().remove("button-disable");
                editarButton.setDisable(false);
                eliminarButton.setDisable(false);
                visualizarButton.setDisable(false);
            }
        }
    }

    @FXML
    public void eliminarButtonAction(){
        if(ManejadorListaLibros.getCurrentLibro() == -1) return;
        EliminarLibro eliminarLibro = new EliminarLibro(ManejadorListaLibros.getCurrentLibro());
    }

    @FXML
    public void addBookButtonAction(){
        AgregarLibro agregarLibro = new AgregarLibro();
    }
    @FXML
    public void viewBookButtonAction(){
        if(ManejadorListaLibros.getCurrentLibro() == -1) return;
        VisualizarLibro agregarLibro = new VisualizarLibro(ManejadorListaLibros.getCurrentLibro());
    }
    @FXML
    public void editBookButtonAction(){
        if(ManejadorListaLibros.getCurrentLibro() == -1) return;
        EditarLibro editarLibro = new EditarLibro(ManejadorListaLibros.getCurrentLibro());
    }
    @FXML
    public void tipoDeBusquedaButtonAction(){
        busquedaPorTitulo = !busquedaPorTitulo;
        if(busquedaPorTitulo) {
            tipoBusquedaImagen.setImage(textoImagen);
            Tooltip.uninstall(buttonBuscar,autorTool);
            Tooltip.install(buttonBuscar,textoTool);
            buttonBuscar.getStyleClass().add("button-orange");
            buttonBuscar.getStyleClass().remove("button-purple");
            buscadorField.getStyleClass().add("text-field-search-orange");
            buscadorField.getStyleClass().remove("text-field-search-purple");

        }else {
            tipoBusquedaImagen.setImage(autorImagen);
            Tooltip.uninstall(buttonBuscar,textoTool);
            Tooltip.install(buttonBuscar,autorTool);
            buttonBuscar.getStyleClass().add("button-purple");
            buttonBuscar.getStyleClass().remove("button-orange");
            buscadorField.getStyleClass().remove("text-field-search-orange");
            buscadorField.getStyleClass().add("text-field-search-purple");
        }
    }
    @FXML
    public void parametroFechaAction(){
        gridFecha.setDisable(!parametroFecha.isSelected());
    }
}
