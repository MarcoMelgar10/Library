package com.odvp.biblioteca.FuncionesMaestros.MaestroReserva;

import java.util.Date;

import java.util.Date;

public class Reserva {
    private int idReserva;
    private Date fechaReserva;
    private boolean estado;
    private int idUsuario;
    private int idLibro;

    // Constructor
    public Reserva(int idReserva, Date fechaReserva, boolean estado, int idUsuario, int idLibro) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", fechaReserva=" + fechaReserva +
                ", estado=" + estado +
                ", idUsuario=" + idUsuario +
                ", idLibro=" + idLibro +
                '}';
    }
}

