package com.odvp.biblioteca.FuncionesBarraOpciones;

import com.odvp.biblioteca.ControladoresVistas.MainEscena;
import javafx.scene.Node;

import java.util.*;

/*
    Esta clase se encarga de guardar las opciones establecidas en el OpcionServicio
    y asociarla a el controlador de cada card de opcion que se ve a la izquierda en la app.
    Cuando una card de opcion ese seleccionada se colorea y el resto se pone sin color.
*/

public class ManejadorOpciones {
    private static ManejadorOpciones instance;
    private OpcionButton currentOpcion;
    private List<OpcionButton> opciones;
    private MainEscena escenePrincipal;

    /*
    setOptions(): carga las opciones del OpcionServicio y crea un diccionario donde
    las llabes son las opcioones y el valor ees el controlador de la vista asociada a esa opcion (Maestro)
     */

    private ManejadorOpciones(){

    }

    public static ManejadorOpciones getInstance(){
        if(instance == null){
            instance = new ManejadorOpciones();
        }
        return instance;
    }

    public void setEscenePrincipal(MainEscena escenePrincipal) {
        this.escenePrincipal = escenePrincipal;
    }
    /*
        setCurrentOption() : recibe una opcion como parametro, colorea la opcion nueva en pantalla y
        hace que la clase ManejadorDeMaestros cambia al Maestro asociado a esa opcion.
     */


    public void setCurrentOption(OpcionButton opcionNueva){
        if(opcionNueva.equals(currentOpcion)) return;
        currentOpcion = opcionNueva;
        escenePrincipal.setCenter((Node) opcionNueva.getModulo());
        for(OpcionButton op : opciones){
            op.setSelected(op.equals(currentOpcion));
        }
    }
    public void setOpciones(List<OpcionButton> opciones){
        this.opciones = opciones;
        escenePrincipal.getPanelOpciones().getChildren().clear();
        for(OpcionButton op : this.opciones){
            escenePrincipal.getPanelOpciones().getChildren().add(op);
        }
    }

    public List<OpcionButton> getOpciones() {
        return opciones;
    }
}
