package com.odvp.biblioteca.FuncionesMaestros.MaestroPrestamo;

import java.sql.Date;

public class Prestamo {
  private Date fecha;
  private Date fecha_vencimiento;
  private Date fecha_devolucion;
  private String usuario;
  private String libro;
  private String estado;

    public Prestamo(Date fecha, Date fecha_vencimiento, Date fecha_devolucion, String usuario, String libro, String estado) {
        this.fecha = fecha;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_devolucion = fecha_devolucion;
        this.usuario = usuario;
        this.libro = libro;
        this.estado = estado;
    }
}
