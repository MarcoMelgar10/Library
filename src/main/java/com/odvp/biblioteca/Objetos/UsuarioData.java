package com.odvp.biblioteca.Objetos;

import com.odvp.biblioteca.Servicios.ServicioIconos;
import javafx.scene.image.Image;

import java.util.List;

public class UsuarioData implements IDatoVisual {
    private final int id;
    private final String nombre;
    private final Image legenda;

    public UsuarioData(int id, String nombre, boolean bloqueado) {
        this.id = id;
        this.nombre = nombre;
        String nameIcon;
        if(bloqueado) this.legenda = new Image(ServicioIconos.USUARIO_NO_DISPONIBLE);
        else this.legenda = new Image(ServicioIconos.USUARIO_DISPONIBLE);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Image getLegenda() {
        return legenda;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public List<Object> getDatos() {
        return List.of(legenda,id, nombre);
    }
}
