package com.odvp.biblioteca.FuncionesMaestros.MaestroPrestamo;

import java.sql.Date;

import java.sql.Date;

public class Prestamo {
    private Date fecha;
    private Date fecha_vencimiento;
    private Date fecha_devolucion;
    private String usuario;
    private String libro;
    private String estado;

    // Constructor
    public Prestamo(Date fecha, Date fecha_vencimiento, Date fecha_devolucion, String usuario, String libro, String estado) {
        this.fecha = fecha;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_devolucion = fecha_devolucion;
        this.usuario = usuario;
        this.libro = libro;
        this.estado = estado;
    }

    // Getters y Setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaVencimiento() {
        return fecha_vencimiento;
    }

    public void setFechaVencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Date getFechaDevolucion() {
        return fecha_devolucion;
    }

    public void setFechaDevolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "fecha=" + fecha +
                ", fecha_vencimiento=" + fecha_vencimiento +
                ", fecha_devolucion=" + fecha_devolucion +
                ", usuario='" + usuario + '\'' +
                ", libro='" + libro + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

