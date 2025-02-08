package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class BooksCategoriesController implements IVista {
    @FXML
    public VBox categoriesContainer, categoriesPanel;

    @Override
    public Parent getContainer() {
        return categoriesContainer;
    }
    public VBox getCategoriesPanel() {
        return categoriesPanel;
    }
}
