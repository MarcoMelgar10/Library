package com.odvp.biblioteca.Objetos;

import com.odvp.biblioteca.LibraryApplication;
import javafx.scene.image.Image;

import java.sql.Date;
import java.util.List;

/*
Representa la informacion de las multas que se veran.
 */


public class MultaCardData implements IDatoVisual {
    private int Id;
    private String nombreUsuario;
    private int monto;
    private Date fecha;
    private int id_prestamo;
    private Image image;

    public MultaCardData(int Id, String nombreUsuario, int monto, Date fecha, int id_prestamo) {
        this.Id = Id;
        this.nombreUsuario = nombreUsuario;
        this.monto = monto;
        this.fecha = fecha;
        this.id_prestamo = id_prestamo;
        image = new Image(LibraryApplication.class.getResource("/com/odvp/biblioteca/Icons/DebtResources/debt.png").toExternalForm());
    }

    @Override
    public int getID() {
        return this.Id;
    }

    @Override
    public List<Object> getDatos() {
        return List.of(image,Id, nombreUsuario, monto, fecha, id_prestamo);
    }
}
