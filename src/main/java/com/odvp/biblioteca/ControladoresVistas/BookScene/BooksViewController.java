package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.AgregarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EditarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.EliminarLibro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.OperacionesLibro.VisualizarLibro;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
    private final Image textoImagen = new Image(getClass().getResource("/com/odvp/biblioteca/Icons/LibrosResources/texto.png").toExternalForm());
    private final Image autorImagen = new Image(getClass().getResource("/com/odvp/biblioteca/Icons/LibrosResources/pluma-pluma.png").toExternalForm());

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @FXML
    public void initialize(){       //Inicia los componentes
        Tooltip.install(buttonBuscar,textoTool);
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
    /*tipoDeBusqueda():
        cambia el tipo de busqueda y el estilo del botón a la derecha del textFiel de busqueda
     */

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
