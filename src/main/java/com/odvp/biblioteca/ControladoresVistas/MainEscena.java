package com.odvp.biblioteca.ControladoresVistas;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModeloAutores;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModuloAutores;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModuloLibros;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModuloMulta;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModuloUsuarios;
import com.odvp.biblioteca.FuncionesBarraOpciones.ManejadorOpciones;
import com.odvp.biblioteca.FuncionesBarraOpciones.OpcionButton;
import com.odvp.biblioteca.Servicios.ServicioIconos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

/*
    Controlador de la escena principal, donde a la izquiera se carga la barra de opciones
    y a la derecha la vista del Maestro que haya sido establecido en la clase ManejadorDeMaestros
 */

public class MainEscena extends BorderPane{

    ModeloLibros modeloLibros = new ModeloLibros();
    ModeloUsuarios modeloUsuarios = new ModeloUsuarios();
    ModeloMulta modeloMulta = new ModeloMulta();
    ModeloAutores modeloAutores = new ModeloAutores();
    
    VBox panelOpciones;

    OpcionButton opcionLibros = new OpcionButton("Libros",new ModuloLibros(modeloLibros),ServicioIconos.OPCION_MODULO_LIBROS);
    OpcionButton opcionAutores = new OpcionButton("Autores",new ModuloAutores(modeloAutores),ServicioIconos.OPCION_MODULO_AUTORES);
    OpcionButton opcionPrestamos = new OpcionButton("Prestamos", null, ServicioIconos.OPCION_MODULO_PRESTAMOS);
    OpcionButton opcionReservas = new OpcionButton("Reservas", null, ServicioIconos.OPCION_MODULO_RESERVAS);
    OpcionButton opcionDeudas = new OpcionButton("Multas", new ModuloMulta(modeloMulta), ServicioIconos.OPCION_MODULO_DEUDAS);
    OpcionButton opcionUsuarios = new OpcionButton("Usuarios", new ModuloUsuarios(modeloUsuarios), ServicioIconos.OPCION_MODULO_USUARIOS);

    ManejadorOpciones manejadorOpciones = ManejadorOpciones.getInstance();


    
    public MainEscena(){
        init();
        manejadorOpciones.setEscenePrincipal(this);
        manejadorOpciones.setOpciones(List.of(opcionLibros, opcionPrestamos, opcionReservas, opcionDeudas, opcionAutores, opcionUsuarios));
        manejadorOpciones.setCurrentOption(opcionLibros);
    }

    private void init(){
        this.setPrefSize(1080, 720);
        panelOpciones = new VBox();
        setLeft(panelOpciones);
        panelOpciones.setPrefWidth(120);
        panelOpciones.setMinWidth(100);
        panelOpciones.setPadding(new Insets(10));
        panelOpciones.setAlignment(Pos.CENTER);
    }

    public VBox getPanelOpciones() {
        return panelOpciones;
    }
}
