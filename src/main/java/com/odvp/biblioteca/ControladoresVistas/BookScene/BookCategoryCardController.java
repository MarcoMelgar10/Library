package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import com.odvp.biblioteca.LibrosClasses.ManejoCategorias.CargadorCategorias;
import com.odvp.biblioteca.LibrosClasses.ManejoCategorias.CategoryData;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

public class BookCategoryCardController implements IVista {
    @FXML
    public HBox CategoryCardContainer;
    @FXML
    public CheckBox categoryCheck;

    private Integer idCategory;
    @Override
    public Parent getContainer() {
        return CategoryCardContainer;
    }

    public void setData(CategoryData category) {
        categoryCheck.setText(category.getNombre());
        this.idCategory = category.getID();
    }

    public Integer getIdCategory() {
        return idCategory;
    }
    public void checkBoxAction(){
        CargadorCategorias.captureCategorySelection(idCategory, categoryCheck.isSelected());
    }
}
