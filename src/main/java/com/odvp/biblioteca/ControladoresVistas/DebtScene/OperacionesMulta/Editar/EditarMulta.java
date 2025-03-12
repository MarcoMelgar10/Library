package com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Editar;

import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.Objetos.Multa;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;

import java.util.List;

public class EditarMulta {
    private MultaDAO multaDAO;
    public EditarMulta(ModeloMulta modelo) {
            multaDAO = new MultaDAO();
            Multa multa = multaDAO.obtener(modelo.multaSeleccionada().getID());
            EditarMultaVentana editarMultaVentana = new EditarMultaVentana(multaDAO, multa );
            if(editarMultaVentana.isHubieronCambios()){
                List<IDatoVisual> multas = multaDAO.listaMultasVisual();
                modelo.setMultaMostrada(multas);
            }
    }
}
