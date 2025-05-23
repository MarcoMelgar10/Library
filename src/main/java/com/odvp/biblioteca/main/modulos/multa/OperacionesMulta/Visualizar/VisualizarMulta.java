package com.odvp.biblioteca.main.modulos.multa.OperacionesMulta.Visualizar;

import com.odvp.biblioteca.main.modulos.multa.ModeloMulta;
import com.odvp.biblioteca.objetos.Multa;
import com.odvp.biblioteca.database.daos.MultaDAO;

public class VisualizarMulta {
    public VisualizarMulta(ModeloMulta modelo) {
        MultaDAO multaDAO = new MultaDAO();
        Multa multa = multaDAO.obtener(modelo.multaSeleccionada().getID());
        new VisualizarMultaVentana(multa);
    }
}
