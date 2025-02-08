package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.IVista;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/*
    Controlador que muestra el contenedor de la derecha (VBox) donde se cargaran las categorias
 */

public class BookCategoriesController implements IVista {
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
