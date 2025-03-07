package com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Visualizar;

import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.Objetos.Multa;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;

public class VisualizarMulta {
    public VisualizarMulta(ModeloMulta modelo) {
        MultaDAO multaDAO = new MultaDAO();
        Multa multa = multaDAO.obtener(modelo.multaSeleccionada().getID());
        new VisualizarMultaVentana(multa);
    }
}
