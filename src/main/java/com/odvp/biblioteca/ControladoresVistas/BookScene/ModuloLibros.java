package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.Libro;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import javafx.scene.layout.BorderPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModuloLibros extends BorderPane implements PropertyChangeListener, IModulo {

    private final HeaderLibros header = new HeaderLibros();
    private final ParametersLibros paramsRight = new ParametersLibros();
    private final TableLibros table = new TableLibros();

    private final ManejadorListaLibros manejadorLibros = ManejadorListaLibros.getInstance();
    private final CargadorCategorias manejadorCategorias = CargadorCategorias.getInstance();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ModuloLibros(){       //Inicia los componentes
        setTop(header);
        setRight(paramsRight);
        setCenter(table);

        manejadorLibros.setTable(table);
        manejadorCategorias.setCategoriasPanel(paramsRight.getVentanaCategorias());
        support.addPropertyChangeListener(this);
        manejadorLibros.addObserver(this);
        simularDatos();
    }

    /*simularDatos() : esta funcion solo fué creada para simular datos de libros y categorias ficticios
    para probar el funcionamiento de los ScrollPane, cuando se tenga disponible la base de datos debe ser eliminada
     */

    public void simularDatos(){
        List<IDatoVisual> libros= new ArrayList<>();
        LibroDAO libroDAO = new LibroDAO();
        for(int i=0;i<libroDAO.listaLibros().size();i++) {
            LibroCardData libroData2 = new LibroCardData(
                    libroDAO.listaLibros().get(i).getID(),
                    libroDAO.listaLibros().get(i).getTitulo(),
                    libroDAO.listaLibros().get(i).getNombreAutor(),
                    libroDAO.listaLibros().get(i).getStock(),
                    libroDAO.listaLibros().get(i).getStockDisponible()
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

        manejadorLibros.loadBooks(libros);
        manejadorCategorias.setDataList(categorias);

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
