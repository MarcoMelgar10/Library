package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.FiltroBasico;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.FiltroFecha;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.IFiltro;
import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.ObjetosVistas.CategoryData;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.postgresql.CRUD.CategoriaDAO;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;
import java.util.List;

public class ModuloLibros extends BorderPane implements IModulo {

    private ModeloLibros modelo;

    private HeaderLibros header;
    private ParametersLibros paramsRight;
    private TableLibros table;

    private ServicioBusquedaLibros busquedaLibros;

    public ModuloLibros(ModeloLibros modelo){
        this.modelo = modelo;
        header = new HeaderLibros(this.modelo);
        paramsRight = new ParametersLibros(this.modelo);
        table = new TableLibros(this.modelo);
        busquedaLibros = new ServicioBusquedaLibros(this.modelo);

        setTop(header);
        setRight(paramsRight);
        setCenter(table);
        cargarDatosIniciales();
    }

    /*simularDatos() : esta funcion solo fué creada para simular datos de libros y categorias ficticios
    para probar el funcionamiento de los ScrollPane, cuando se tenga disponible la base de datos debe ser eliminada
     */


    public void cargarDatosIniciales(){
        new Thread(new Task<>() {

            @Override
            protected Object call() throws Exception {
                LibroDAO libroDAO = new LibroDAO();
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                modelo.setLibrosMostrados(libroDAO.listaLibrosVisual());
                modelo.setCategoriasMostradas(categoriaDAO.listaCategorias());
                modelo.setFiltrosMostrados(ServicioFiltros.obtenerFiltrosPredeterminados());
                return null;
            }
        }).start();
    }
}