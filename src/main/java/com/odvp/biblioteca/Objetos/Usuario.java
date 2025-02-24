package com.odvp.biblioteca.Objetos;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    private int multa;
    private boolean estadoBloqueo;


    public Usuario(Builder builder) {
        this.id = builder.idUsuario;
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
        this.multa = builder.multa;
        this.estadoBloqueo = builder.estadoBloqueo;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getMulta() {
        return multa;
    }

    public boolean isEstadoBloqueo() {
        return estadoBloqueo;
    }

    // Clase Builder
    public static class Builder {
        private int idUsuario;
        private String nombre;
        private String apellidos;
        private String telefono;
        private String direccion;
        private int multa;
        private boolean estadoBloqueo;

        // Métodos de configuración para cada campo
        public Builder idUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellidos(String paterno, String materno) {
            this.apellidos = paterno + " " + materno;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder multa(int multa) {
            this.multa = multa;
            return this;
        }

        public Builder estadoBloqueo(boolean estadoBloqueo) {
            this.estadoBloqueo = estadoBloqueo;
            return this;
        }

        // Método para construir el objeto Usuario
        public Usuario build() {
            return new Usuario(this);
        }
    }
}
