package com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookCategoryCardController;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/*
    se encarga de cargar las categorias en un panel pequeño (BookCategoriesController) al lado izquierdo
    en el panel principal del Maestro libros (BookViewController)
 */

public class CargadorCategorias {
    private static VBox categoriasPanel;
    private static List<Integer> selectedCategoriasId;
    private static List<CategoryData> allCategories;

    private CargadorCategorias(){

    }

    public static void setCategoriasPanel(VBox categoriasPanel1) {
        categoriasPanel = categoriasPanel1;
    }

    /*
        setDataList(): se le pasa una lista de datos de categorias (CategoryData), se crean las vistas
        y se cargan en el panel de categorias.
     */

    public static void setDataList(List<CategoryData> categorias){
        allCategories = new ArrayList<>();
        selectedCategoriasId = new ArrayList<>();
        for(CategoryData category : categorias){
            BookCategoryCardController categoryCard = createView();
            if(categoryCard == null) continue;
            categoryCard.setData(category);
            allCategories.add(category);
            categoriasPanel.getChildren().add(categoryCard.getContainer());
        }
    }

    /*
        captureCategorySelection() : añade a la lista de opciones seleccionadas una opcion
        o la elimina de ella si ya estaba marcada
     */

    public static void captureCategorySelection(Integer id, boolean estaMarcado){
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

    //crea la vista de una categoria, es usada por la funcion setDataList()

    private static BookCategoryCardController createView(){
        try {
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("Vistas/BookScene/book-category-card.fxml"));
            loader.load();
            return loader.getController();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
