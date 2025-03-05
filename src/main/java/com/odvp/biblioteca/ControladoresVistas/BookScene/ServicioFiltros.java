package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.FiltroBasico;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.FiltroFecha;
import com.odvp.biblioteca.ControladoresVistas.DefaultComponents.IFiltro;

import java.util.List;

public class ServicioFiltros {
    public static List<IFiltro> obtenerFiltrosPredeterminados() {
        return List.of(
                new FiltroBasico("Hay disponible", " AND stock_disponible > 0"),
                new FiltroBasico("Sin observación", " AND (observacion IS NULL OR observacion = '')"),
                new FiltroFecha("Fecha publicación", "fecha_publicacion")
        );
    }
}