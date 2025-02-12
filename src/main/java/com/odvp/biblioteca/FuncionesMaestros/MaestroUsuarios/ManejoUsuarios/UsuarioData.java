package com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios;

import com.odvp.biblioteca.LibraryApplication;
import javafx.scene.image.Image;

public class UsuarioData {
    private final int id;
    private final String nombre;
    private final Image legenda;

    public UsuarioData(int id, String nombre, boolean bloqueado) {
        this.id = id;
        this.nombre = nombre;
        String nameIcon;
        if(bloqueado) nameIcon = "Icons/UserResources/user-disable.png";
        else nameIcon = "Icons/UserResources/user-enable.png";
        this.legenda = new Image(LibraryApplication.class.getResource(nameIcon).toExternalForm());
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

}
