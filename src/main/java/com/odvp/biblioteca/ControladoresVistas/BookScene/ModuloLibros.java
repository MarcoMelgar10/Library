package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.ObjetosVistas.CategoryData;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.ObjetosVistas.LibroCardData;
import com.odvp.biblioteca.ObjetosVistas.LibroDTO;
import com.odvp.biblioteca.postgresql.CRUD.CategoriaDAO;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.List;

public class ModuloLibros extends BorderPane implements IModulo {

    private ModeloLibros modelo;

    private HeaderLibros header;
    private ParametersLibros paramsRight;
    private TableLibros table;

    public ModuloLibros(ModeloLibros modelo){
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
        new Thread(new Task<>() {

            @Override
            protected Object call() throws Exception {
                List<IDatoVisual> datoLibros= new ArrayList<>();
                LibroDAO libroDAO = new LibroDAO();
                List<LibroDTO> libros = libroDAO.listaLibros();
                for(LibroDTO libro : libros) {
                    LibroCardData libroCardData = new LibroCardData(
                            libro.getIdLibro(),
                            libro.getTitulo(),
                            libro.getNombreAutor(),
                            libro.getStock(),
                            libro.getStockDisponible()
                    );
                    datoLibros.add(libroCardData);
                }
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                List<CategoryData> categoriaDatos = categoriaDAO.listaCategorias();
                modelo.setLibrosMostrados(datoLibros);
                modelo.setCategoriasMostradas(categoriaDatos);
                return null;
            }
        }).start();
    }
}