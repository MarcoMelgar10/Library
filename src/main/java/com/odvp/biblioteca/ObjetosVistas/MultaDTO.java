package com.odvp.biblioteca.ObjetosVistas;

import java.sql.Date;

public class MultaDTO {
    private int idMulta;
    private String descripcion;
    private int monto;
    private Date fechaMulta;
    private boolean estado;
    private Date fechaEliminacion;
    private String nombreUsuario; // Dato foráneo desde "usuario"
    private int idPrestamo;

    public MultaDTO(int idMulta, String descripcion, int monto, Date fechaMulta, boolean estado, Date fechaEliminacion, String nombreUsuario, int idPrestamo) {
        this.idMulta = idMulta;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaMulta = fechaMulta;
        this.estado = estado;
        this.fechaEliminacion = fechaEliminacion;
        this.nombreUsuario = nombreUsuario;
        this.idPrestamo = idPrestamo;
    }

    // Getters y Setters
    public int getIdMulta() { return idMulta; }
    public void setIdMulta(int idMulta) { this.idMulta = idMulta; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getMonto() { return monto; }
    public void setMonto(int monto) { this.monto = monto; }

    public Date getFechaMulta() { return fechaMulta; }
    public void setFechaMulta(Date fechaMulta) { this.fechaMulta = fechaMulta; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public Date getFechaEliminacion() { return fechaEliminacion; }
    public void setFechaEliminacion(Date fechaEliminacion) { this.fechaEliminacion = fechaEliminacion; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public int getIdPrestamo() {
        return idPrestamo;
    }
}
