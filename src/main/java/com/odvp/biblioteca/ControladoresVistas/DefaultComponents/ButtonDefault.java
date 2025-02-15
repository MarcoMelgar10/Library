package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.LibraryApplication;
import com.odvp.biblioteca.Servicios.ServicioIconos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class ButtonDefault extends StackPane {

    public static final String BUTTON_NEW = "BUTTON NEW";
    public static final String BUTTON_EDIT = "BUTTON EDIT";
    public static final String BUTTON_DELETE = "BUTTON DELETE";
    public static final String BUTTON_VIEW = "BUTTON VIEW";

    private ButtonDefault(String tipo){
        setPrefWidth(50);
        setAlignment(Pos.CENTER);
        String urlIcon = "";
        String styleclass = "";

        switch (tipo){
            case BUTTON_NEW:
                urlIcon = ServicioIconos.AGREGAR_BUTTON;
                styleclass = "button-yellow";
                break;
            case BUTTON_EDIT:
                urlIcon = ServicioIconos.EDITAR_BUTTON;
                styleclass = "button-blue";
                break;
            case BUTTON_DELETE:
                urlIcon = ServicioIconos.ELIMINAR_BUTTON;
                styleclass = "button-red";
                break;
            case BUTTON_VIEW:
                urlIcon = ServicioIconos.VISUALIZAR_BUTTON;
                styleclass = "button-green";
                break;
        }

        ImageView imageView = new ImageView(new Image(urlIcon));
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        getChildren().add(imageView);
        getStylesheets().add(LibraryApplication.class.getResource("Styles/Styles.css").toExternalForm());
        getStyleClass().addAll("button-shape");
        getStyleClass().add(styleclass);
    }

    public static ButtonDefault getButtonNew(){
        return new ButtonDefault(BUTTON_NEW);
    }
    public static ButtonDefault getButtonEdit(){
        return new ButtonDefault(BUTTON_EDIT);
    }
    public static ButtonDefault getButtonDelete(){
        return new ButtonDefault(BUTTON_DELETE);
    }
    public static ButtonDefault getButtonView(){
        return new ButtonDefault(BUTTON_VIEW);
    }

    public void desactivar(boolean deshabilitar){
        setDisable(deshabilitar);
        setDisable(deshabilitar);
        setDisable(deshabilitar);

        if(deshabilitar){
            getStyleClass().add("button-disable");
            getStyleClass().add("button-disable");
            getStyleClass().add("button-disable");
        }
        else {
            getStyleClass().remove("button-disable");
            getStyleClass().remove("button-disable");
            getStyleClass().remove("button-disable");
        }
    }

}
