package com.odvp.biblioteca.FuncionesMaestros.MaestroMulta;

import java.sql.Date;


public class Multa {
    private int idMulta;
    private String descripcion;
    private int monto;
    private Date fechaMulta;
    private boolean estado;
    private Date fechaEliminacion;
    private int idUsuario;
    private int idPrestamo;

    // Constructor
    public Multa(int idMulta, String descripcion, int monto, Date fechaMulta, boolean estado, Date fechaEliminacion, int idUsuario, int idPrestamo) {
        this.idMulta = idMulta;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaMulta = fechaMulta;
        this.estado = estado;
        this.fechaEliminacion = fechaEliminacion;
        this.idUsuario = idUsuario;
        this.idPrestamo = idPrestamo;
    }

    // Getters y Setters
    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFechaMulta() {
        return fechaMulta;
    }

    public void setFechaMulta(Date fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "idMulta=" + idMulta +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", fechaMulta=" + fechaMulta +
                ", estado=" + estado +
                ", fechaEliminacion=" + fechaEliminacion +
                ", idUsuario=" + idUsuario +
                ", idPrestamo=" + idPrestamo +
                '}';
    }
}
