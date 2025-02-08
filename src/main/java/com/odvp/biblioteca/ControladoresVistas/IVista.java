package com.odvp.biblioteca.ControladoresVistas;

import javafx.scene.Parent;
/*
    Interfaz que obliga a tener un metodo getContainer, debe ser usada
    por todos los controladores de vistas de cada Maestro
 */
public interface IVista {
    Parent getContainer();
}
