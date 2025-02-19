package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ParametersDefault;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/*
    se encarga de cargar las categorias en un panel pequeño (BookCategoriesController) al lado izquierdo
    en el panel principal del Maestro libros (BookViewController)
 */

public class CargadorCategorias {
    private static CargadorCategorias instance;
    private VBox categoriasPanel;
    private List<Integer> selectedCategoriasId;

    private CargadorCategorias(){

    }

    public static CargadorCategorias getInstance(){
        if(instance == null){
            instance = new CargadorCategorias();
        }
        return instance;
    }

    public void setCategoriasPanel(VBox categoriasPanel1) {
        categoriasPanel = categoriasPanel1;
    }

    /*
        setDataList(): se le pasa una lista de datos de categorias (CategoryData), se crean las vistas
        y se cargan en el panel de categorias.
     */

    public void setDataList(List<CategoryData> categorias){
        categoriasPanel.getChildren().clear();
        selectedCategoriasId = new ArrayList<>();
        for(CategoryData category : categorias){
            CheckBox categoryCard = ParametersDefault.createSimpleParam(category.getNombre());
            categoryCard.setOnMouseClicked(e -> captureCategorySelection(category.getID(),categoryCard.isSelected()));
            categoriasPanel.getChildren().add(categoryCard);
        }
    }

    /*
        captureCategorySelection() : añade a la lista de opciones seleccionadas una opcion
        o la elimina de ella si ya estaba marcada
     */

    public void captureCategorySelection(Integer id, boolean estaMarcado){
        if(estaMarcado){
            selectedCategoriasId.add(id);
            System.out.println("Cateforia '" + id + "' agregada");
        }else{
            selectedCategoriasId.remove(id);
            System.out.println("Cateforia '" + id + "' removida");
        }
        System.out.print("Categorias seleccionadas: ");
        for(int categoryID : selectedCategoriasId){
            System.out.print(categoryID + " - ");
        }
    }
}
