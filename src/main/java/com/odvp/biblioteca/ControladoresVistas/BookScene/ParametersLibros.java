package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.ParametersDefault;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ParametersLibros extends ParametersDefault {

    private final CheckBox PARAM_UNIDADES_DISPONIBLES = ParametersDefault.createSimpleParam("Hay unidades disponibles");
    private final CheckBox PARAM_TIENE_OBSERVACION = ParametersDefault.createSimpleParam("Tiene observaciones");
    private final VBox PARAM_ANO_PUBLICACION = ParametersDefault.createDataParam("Año de publicacion");

    private VBox ventanaCategorias, ventanaFiltros;


    public ParametersLibros(){
        List<Parent> categorias = new ArrayList<>();
        ventanaCategorias = addSubWindow("Categorias",categorias);

        List<Parent> filtros = new ArrayList<>();
        filtros.add(PARAM_UNIDADES_DISPONIBLES);
        filtros.add(PARAM_TIENE_OBSERVACION);
        filtros.add(PARAM_ANO_PUBLICACION);

        ventanaFiltros = addSubWindow("Filtros", filtros);
    }

    public VBox getVentanaCategorias() {
        return ventanaCategorias;
    }

    public VBox getVentanaFiltros() {
        return ventanaFiltros;
    }
}
