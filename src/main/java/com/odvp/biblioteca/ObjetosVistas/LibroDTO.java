package com.odvp.biblioteca.ObjetosVistas;

import java.sql.Date;

public class LibroDTO {
    private int idLibro;
    private String titulo;
    private String observacion;
    private Date fechaPublicacion;
    private int stock;
    private int stockDisponible;
    private String nombreAutor;         // Dato foráneo desde "autor"
    private String nombreCategoria;     // Dato foráneo desde "categoria"
    private String nombreSubCategoria;  // Dato foráneo desde "sub_categoria"

    public LibroDTO(int idLibro, String titulo, String observacion, Date fechaPublicacion, int stock, int stockDisponible,
                    String nombreAutor, String nombreCategoria, String nombreSubCategoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.observacion = observacion;
        this.fechaPublicacion = fechaPublicacion;
        this.stock = stock;
        this.stockDisponible = stockDisponible;
        this.nombreAutor = nombreAutor;
        this.nombreCategoria = nombreCategoria;
        this.nombreSubCategoria = nombreSubCategoria;
    }

    // Getters y Setters
    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public Date getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(Date fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int stockDisponible) { this.stockDisponible = stockDisponible; }

    public String getNombreAutor() { return nombreAutor; }
    public void setNombreAutor(String nombreAutor) { this.nombreAutor = nombreAutor; }

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public String getNombreSubCategoria() { return nombreSubCategoria; }
    public void setNombreSubCategoria(String nombreSubCategoria) { this.nombreSubCategoria = nombreSubCategoria; }
}
