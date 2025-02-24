package com.odvp.biblioteca.ControladoresVistas.DebtScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.Objetos.IDatoVisual;
import com.odvp.biblioteca.Objetos.Multa;
import com.odvp.biblioteca.Objetos.MultaCardData;
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
                List<Multa> multas = multaDAO.listaMultas();
                for (Multa multa : multas) {
                    datoDeudas.add(new MultaCardData(multa.getIdMulta(), "Jorge", multa.getMonto(),multa.getFechaMulta(), multa.getIdPrestamo()));
                }
               modelo.setMultaMostrada(datoDeudas);
                return null;
            }
        }).start();
    }
}
