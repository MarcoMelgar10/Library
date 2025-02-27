package com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Agregar;

import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;

import java.util.List;

public class AgregarMulta {
    private MultaDAO multaDAO;
    public AgregarMulta(ModeloMulta modelo){
        multaDAO = new MultaDAO();
        AgregarMultaVentana agregarMulta = new AgregarMultaVentana(multaDAO);
        if(agregarMulta.isHubieronCambios()){
            List<IDatoVisual> multas = multaDAO.listaMultasVisual();
            modelo.setMultaMostrada(multas);
        }

    }
}
