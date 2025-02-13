package com.odvp.biblioteca.FuncionesMaestros.MaestroReserva;

import java.util.Date;

public class Reserva {
    private Date fecha;
    private boolean estado;
    private String libro;
    private String usuario;

    public Reserva(Date fecha, boolean estado, String libro, String usuario) {
        this.fecha = fecha;
        this.estado = estado;
        this.libro = libro;
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
