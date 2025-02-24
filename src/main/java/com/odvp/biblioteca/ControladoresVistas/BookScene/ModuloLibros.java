package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import com.odvp.biblioteca.postgresql.CRUD.CategoriaDAO;
import com.odvp.biblioteca.postgresql.CRUD.LibroDAO;
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
        List<CategoryData> categorias = new ArrayList<>();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        for(int i=0;i<categoriaDAO.listaCategorias().size();i++){
            CategoryData categoryData = new CategoryData(categoriaDAO.listaCategorias().get(i).getId(),
                    categoriaDAO.listaCategorias().get(i).getNombre(),
                    categoriaDAO.listaCategorias().get(i).getDescripcion()
                    );
            categorias.add(categoryData);
        }
        modelo.setLibrosMostrados(libros);
        modelo.setCategoriasMostradas(categorias);

    }
}