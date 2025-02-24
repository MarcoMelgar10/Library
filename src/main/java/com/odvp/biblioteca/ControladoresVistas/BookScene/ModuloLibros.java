package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.Objetos.CategoryData;
import com.odvp.biblioteca.Objetos.IDatoVisual;
import com.odvp.biblioteca.Objetos.LibroCardData;
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
                return null;
            }
        }).start();
    }
}
