package com.odvp.biblioteca.LibrosClasses.ManejoCategorias;

import com.odvp.biblioteca.ControladoresVistas.BookScene.BookCardController;
import com.odvp.biblioteca.ControladoresVistas.BookScene.BookCategoryCardController;
import com.odvp.biblioteca.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CargadorCategorias {
    private static VBox categoriasPanel;
    private static List<Integer> selectedCategoriasId;
    private static List<CategoryData> allCategories;

    private CargadorCategorias(){

    }
    public static void setCategoriasPanel(VBox categoriasPanel1) {
        categoriasPanel = categoriasPanel1;
    }
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

    private static BookCategoryCardController createView(){
        try {
            FXMLLoader loader = new FXMLLoader(LibraryApplication.class.getResource("BookScene/book-category-card.fxml"));
            loader.load();
            return loader.getController();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
