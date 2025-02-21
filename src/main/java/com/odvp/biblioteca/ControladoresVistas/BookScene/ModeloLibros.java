package com.odvp.biblioteca.ControladoresVistas.BookScene;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoCategorias.CategoryData;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.LibroCardData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ModeloLibros {
    private static final Logger log = LogManager.getLogger(ModeloLibros.class);
    private List<CategoryData> categorias;
    private List<IFiltro> filtros;
    private String textoBusqueda;
    private IDatoVisual libroSeleccionado;
    private List<IDatoVisual> librosMostrados;
    private List<CategoryData> categoriasSeleccionadas;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public static final String OBS_CATEGORIAS_SELECCIONADAS = "OBS_CATEGORIAS_SELECCIONADAS";
    public static final String OBS_CATEGORIAS_MOSTRADAS = "OBS_CATEGORIAS_MOSTRADAS";
    public static final String OBS_FILTROS_SELECCIONADOS = "OBS_FILTROS_SELECCIONADOS";
    public static final String OBS_TEXTO_BUSCADOR = "OBS_TEXTO_BUSCADOR";
    public static final String OBS_LIBROS_MOSTRADOS = "OBS_LIBROS_MOSTRADOS";
    public static final String OBS_LIBRO_SELECCIONADO = "OBS_LIBRO_SELECCIONADO";

    public ModeloLibros(){
        categorias = new ArrayList<>();
        categoriasSeleccionadas = new ArrayList<>();
        filtros = new ArrayList<>();
        librosMostrados = new ArrayList<>();
        textoBusqueda = "";
        libroSeleccionado = null;
    }

    public void setCategoriaSelected(CategoryData categoria, boolean selected){
        List<CategoryData> oldCategorias = List.copyOf(categoriasSeleccionadas);
        if(selected) categoriasSeleccionadas.add(categoria);
        else categoriasSeleccionadas.remove(categoria);
        support.firePropertyChange(OBS_CATEGORIAS_SELECCIONADAS, oldCategorias, this.categoriasSeleccionadas);
    }

    public void setLibrosMostrados(List<IDatoVisual> libros){
        List<IDatoVisual> oldLibros = List.copyOf(this.librosMostrados);
        librosMostrados = libros;
        support.firePropertyChange(OBS_LIBROS_MOSTRADOS, oldLibros, this.librosMostrados);
    }

    public void setCategoriasMostradas(List<CategoryData> categorias){
        List<CategoryData> oldCategorias = List.copyOf(this.categorias);
        this.categorias = categorias;
        support.firePropertyChange(OBS_CATEGORIAS_MOSTRADAS, oldCategorias, this.categorias);
    }

    public void setFiltroSelected(IFiltro filtro, boolean selected){
        List<IFiltro> oldFiltros = List.copyOf(filtros);
        if(selected) filtros.add(filtro);
        else filtros.remove(filtro);
        support.firePropertyChange(OBS_FILTROS_SELECCIONADOS, oldFiltros, this.filtros);
    }

    public void setTextoBusqueda(String textoBusqueda){
        String oldTexto = this.textoBusqueda;
        this.textoBusqueda = textoBusqueda;
        support.firePropertyChange(OBS_TEXTO_BUSCADOR, oldTexto, this.textoBusqueda);
    }

    public void setLibroSeleccionado(IDatoVisual libroSeleccionado){
        if(this.libroSeleccionado == libroSeleccionado) libroSeleccionado = null;
        IDatoVisual oldLibro = this.libroSeleccionado;
        this.libroSeleccionado = libroSeleccionado;
        support.firePropertyChange(OBS_LIBRO_SELECCIONADO, oldLibro, this.libroSeleccionado);
    }

    public void addObserver(PropertyChangeListener observer){
        support.addPropertyChangeListener(observer);
    }

    public LibroCardData getLibroSeleccionado() {
        return (LibroCardData) libroSeleccionado;
    }

    public List<IDatoVisual> getLibrosMostrados() {
        return librosMostrados;
    }

    public List<CategoryData> getCategoriasSeleccionadas() {
        return categoriasSeleccionadas;
    }

    public List<CategoryData> getCategoriasMostradas() {
        return categorias;
    }

}
