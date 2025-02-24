package com.odvp.biblioteca.ControladoresVistas.UsuarioScene;

import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.Objetos.IDatoVisual;
import com.odvp.biblioteca.Objetos.UsuarioData;
import javafx.concurrent.Task;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class ModuloUsuarios extends BorderPane implements IModulo {
    private HeaderUsuarios header;
    private TableUsuarios table;

    private ModeloUsuarios modelo;

    public ModuloUsuarios(ModeloUsuarios modelo){
        this.modelo = modelo;

        header = new HeaderUsuarios(this.modelo);
        table = new TableUsuarios(this.modelo);

        setTop(header);
        setCenter(table);
        simularDatos();
    }


    public void simularDatos(){
        new Thread(new Task<>() {
            @Override
            protected Object call() throws Exception {
                List<IDatoVisual> usuarios = new ArrayList<>();
                for(int i=1; i<=20; i++){
                    usuarios.add(new UsuarioData(i + 12402330, "Marco Antonio Melgar Parada", false));
                }
                modelo.setUsuariosMostrados(usuarios);
                return null;
            }
        }).start();


    }
}
