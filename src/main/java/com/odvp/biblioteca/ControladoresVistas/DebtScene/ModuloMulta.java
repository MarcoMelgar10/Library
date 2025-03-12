package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.ObjetosVistas.IDatoVisual;
import com.odvp.biblioteca.ObjetosVistas.MultaCardData;
import com.odvp.biblioteca.postgresql.CRUD.MultaDAO;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class ModuloMulta extends BorderPane implements IModulo {
    private ModeloMulta modeloMulta;
    private HeaderMultas header;
    private TablaMulta table;
    private ModeloMulta modelo;

    public ModuloMulta(ModeloMulta modelo) {
        this.modeloMulta =  new ModeloMulta();
        this.modelo = modelo;
        header = new HeaderMultas(modelo);
        table = new TablaMulta(modelo);
        ServicioBusquedaMulta busquedaMulta = new ServicioBusquedaMulta(modelo);
        setTop(header);
        setCenter(table);
        simularDatos();
    }

    private void simularDatos() {
        new Thread(new Task<>() {
            @Override
            protected Object call() throws Exception {
                List<IDatoVisual> datoDeudas = new ArrayList<>();
                MultaDAO multaDAO = new MultaDAO();
                List<MultaCardData> multas = multaDAO.listaMultas();
                for (MultaCardData multa : multas) {
                    datoDeudas.add(new MultaCardData(multa.getID(), multa.getNombreUsuario(), multa.getMonto(),multa.getFecha(), multa.isEstado(), multa.getId_prestamo()));
                }
               modelo.setMultaMostrada(datoDeudas);
                return null;
            }
        }).start();
    }
}
