package com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.CancelarMulta;

import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.ObjetosVistas.MultaCardData;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;

import java.util.List;

public class CancelarMulta {
    MultaDAO multaDAO = new MultaDAO();

    public CancelarMulta(ModeloMulta modelo) {
        MultaCardData multaCardData = modelo.multaSeleccionada();
        CancelarMultaVentana cancelarMultaVentana = new CancelarMultaVentana(multaDAO, multaCardData);
        if (cancelarMultaVentana.isHubieronCambios()) {
            List<IDatoVisual> multas = multaDAO.listaMultasVisual();
            modelo.setMultaMostrada(multas);
        }
    }
}
