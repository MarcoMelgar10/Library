package com.odvp.biblioteca.ObjetosVistas;

import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.Servicios.ServicioIconos;
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
    private boolean estado;


    public MultaCardData(int Id, String nombreUsuario, int monto, Date fecha, boolean estado, int id_prestamo) {
        this.Id = Id;
        this.nombreUsuario = nombreUsuario;
        this.monto = monto;
        this.fecha = fecha;
        this.id_prestamo = id_prestamo;
        this.estado = estado;
        ServicioIconos icon = new ServicioIconos();
        if (!estado){
            image = new Image(icon.MULTA_SIN_CANCELAR);
        }else{
            image = new Image(icon.MULTA_CANCELADA);
        }

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
