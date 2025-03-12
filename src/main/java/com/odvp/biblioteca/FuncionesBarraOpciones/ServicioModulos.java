package com.odvp.biblioteca.FuncionesBarraOpciones;

import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModeloAutores;
import com.odvp.biblioteca.ControladoresVistas.AutorScene.ModuloAutores;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModeloLibros;
import com.odvp.biblioteca.ControladoresVistas.BookScene.ModuloLibros;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModeloMulta;
import com.odvp.biblioteca.ControladoresVistas.DebtScene.ModuloMulta;
import com.odvp.biblioteca.ControladoresVistas.IModulo;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModeloUsuarios;
import com.odvp.biblioteca.ControladoresVistas.UsuarioScene.ModuloUsuarios;

public class ServicioModulos {

    private static final ModuloLibros moduloLibros = new ModuloLibros(new ModeloLibros());
    private static final ModuloAutores moduloAutores = new ModuloAutores(new ModeloAutores());
    private static final ModuloMulta moduloMulta = new ModuloMulta(new ModeloMulta());
    private static final ModuloUsuarios moduloUsuarios = new ModuloUsuarios(new ModeloUsuarios());

    public static ModuloLibros getModuloLibros() {
        return moduloLibros;
    }

    public static ModuloAutores getModuloAutores() {
        return moduloAutores;
    }

    public static ModuloMulta getModuloMulta() {
        return moduloMulta;
    }

    public static ModuloUsuarios getModuloUsuarios() {
        return moduloUsuarios;
    }

    public static void recargarVista(IModulo modulo){
        modulo.cargarDatosIniciales();
    }

}
