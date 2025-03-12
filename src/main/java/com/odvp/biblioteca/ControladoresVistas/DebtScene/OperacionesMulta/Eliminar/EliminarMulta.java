package com.odvp.biblioteca.ControladoresVistas.DebtScene.OperacionesMulta.Eliminar;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;

import java.util.List;

public class EliminarMulta {
    private final MultaDAO multaDAO = new MultaDAO();
    public EliminarMulta(ModeloMulta modelo) {
        EliminarMultaVentana eliminarMultaVentana = new EliminarMultaVentana(modelo.multaSeleccionada().getID(),multaDAO );
        if (eliminarMultaVentana.isEliminar()) {
            List<IDatoVisual> multas = multaDAO.listaMultasVisual();
            modelo.setMultaMostrada(multas);
        }
    }
}
