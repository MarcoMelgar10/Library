package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.ManejadorListaLibros;
import javafx.scene.layout.BorderPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModuloLibros extends BorderPane implements IModulo {

    private ModeloModuloLibros modelo;

    private HeaderLibros header;
    private ParametersLibros paramsRight;
    private TableLibros table;

    public ModuloLibros(ModeloModuloLibros modelo){
        this.modelo = modelo;
        header = new HeaderLibros(this.modelo);
        paramsRight = new ParametersLibros(this.modelo);
        table = new TableLibros(this.modelo);

        setTop(header);
        setRight(paramsRight);
        setCenter(table);
        simularDatos();
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

        for(int i=0;i<15;i++){
            CategoryData categoryData = new CategoryData(i,"Categoria " +i,"Categoria ficticia");
            categorias.add(categoryData);
        }
        modelo.setLibrosMostrados(libros);
        modelo.setCategoriasMostradas(categorias);

    }

    /*
        Patron observer: detecta cambios en la propiedad currentLibro de la clase ManejadorListaLibros, si ahora
        el libro seleccionado tiene indice -1 (Ninguno) entonces deshabilita los botones (Edicion, Nuevo, Eliminar),
        caso contratrio los habilita y los colorea.
     */
}
