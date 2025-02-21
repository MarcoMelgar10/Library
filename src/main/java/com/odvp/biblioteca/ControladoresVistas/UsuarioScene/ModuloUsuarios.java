package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.ManejadorTableDefault;
import com.odvp.biblioteca.FuncionesMaestros.MaestroUsuarios.ManejoUsuarios.UsuarioData;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class ModuloUsuarios extends BorderPane implements IModulo {
    private final HeaderUsuarios header = new HeaderUsuarios();
    private final TableUsuarios table = new TableUsuarios();

    private ManejadorTableDefault manejadorTableDefault;

    public ModuloUsuarios(){
        setTop(header);
        setCenter(table);
        manejadorTableDefault = new ManejadorTableDefault(table);
        simularDatos();
    }


    public void simularDatos(){
        List<IDatoVisual> usuarios = new ArrayList<>();
        for(int i=1; i<=20; i++){
            usuarios.add(new UsuarioData(i + 12402330, "Marco Antonio Melgar Parada", false));
        }

    }
}
