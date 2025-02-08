package com.odvp.biblioteca.LibrosClasses;

import java.time.LocalDate;

public class Libro {
    private int ID;
    private String titulo;
    private String observacion;
    private LocalDate publicacion;
    private int stock;
    private int stockDisponible;
    private int idCategoria;
    private int idAutor;

    public Libro(Builder builder){
        this.ID = builder.ID;
        this.titulo = builder.titulo;
        this.observacion = builder.observacion;
        this.publicacion = builder.publicacion;
        this.stock = builder.stock;
        this.stockDisponible = builder.stockDisponible;
        this.idCategoria = builder.idCategoria;
        this.idAutor = builder.idAutor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LocalDate getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(LocalDate publicacion) {
        this.publicacion = publicacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public static class Builder {
        private int ID;
        private String titulo;
        private String observacion;
        private LocalDate publicacion;
        private int stock;
        private int stockDisponible;
        private int idCategoria;
        private int idAutor;

        public Builder ID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder observacion(String observacion) {
            this.observacion = observacion;
            return this;
        }

        public Builder publicacion(LocalDate publicacion) {
            this.publicacion = publicacion;
            return this;
        }

        public Builder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder stockDisponible(int stockDisponible) {
            this.stockDisponible = stockDisponible;
            return this;
        }

        public Builder idCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
            return this;
        }

        public Builder idAutor(int idAutor) {
            this.idAutor = idAutor;
            return this;
        }

        public Libro build() {
            return new Libro(this);
        }


    }
}
