package com.odvp.biblioteca.objetosVisuales;

import java.util.Date;
import java.util.List;

public class ReservaCardData implements IDatoVisual{
    private int ID;
    private String nombreUsuario;
    private String tituloLibro;
    private Date fecha_reserva;
    private String estado;

    public ReservaCardData(int ID, String nombreUsuario, String tituloLibro, Date fecha_reserva, String estado) {
        this.ID = ID;
        this.nombreUsuario = nombreUsuario;
        this.tituloLibro = tituloLibro;
        this.fecha_reserva = fecha_reserva;
        this.estado = estado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public List<Object> getDatos() {
        return List.of();
    }
}
