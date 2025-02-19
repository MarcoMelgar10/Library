package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.LibraryApplication;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ParametersDefault extends VBox {

    List<VBox> subwindows = new ArrayList<>();

    public ParametersDefault() {
        this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
    }

    public VBox addSubWindow(String title, List<Parent> params){
        getStylesheets().add(LibraryApplication.class.getResource("Styles/Styles.css").toExternalForm());
        VBox window = new VBox();
        VBox.setVgrow(window, Priority.ALWAYS);

        StackPane titlePane = new StackPane();
        titlePane.setStyle("-fx-background-color: #dfdfdf;");
        titlePane.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        titlePane.setPrefHeight(20);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold;");
        titlePane.getChildren().add(titleLabel);
        StackPane.setMargin(titleLabel, new Insets(0, 0, 0, 6));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(200, USE_COMPUTED_SIZE);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox bodyScroll = new VBox();
        bodyScroll.setPadding(new Insets(12,0,12,0));
        bodyScroll.setSpacing(12);
        bodyScroll.setPrefSize(200, USE_COMPUTED_SIZE);
        for(Parent param : params){
            bodyScroll.getChildren().add(param);
        }
        scrollPane.setContent(bodyScroll);
        window.getChildren().addAll(titlePane, scrollPane);
        getChildren().add(window);

        subwindows.add(window);
        return bodyScroll;
    }

    public static CheckBox createSimpleParam(String text){
        CheckBox simpleParam = new CheckBox(text);
        simpleParam.setStyle("-fx-font-style: italic;");
        simpleParam.setPadding(new Insets(0,0,0,6));
        return simpleParam;
    }

    public static VBox createDataParam(String text){
        VBox dateContainer = new VBox();
        CheckBox parametroFecha = new CheckBox(text);
        parametroFecha.setStyle("-fx-font-style: italic;");
        StackPane dateFilterPane = new StackPane(parametroFecha);
        dateFilterPane.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        StackPane.setMargin(parametroFecha, new Insets(0, 0, 0, 6));

        GridPane gridFecha = new GridPane();
        gridFecha.setDisable(true);
        gridFecha.setPadding(new Insets(3, 0, 0, 30));
        parametroFecha.setOnAction(event -> gridFecha.setDisable(!parametroFecha.isSelected()));

        Label desdeLabel = new Label("Desde");
        desdeLabel.setStyle("-fx-font-size: 10;");
        GridPane.setColumnIndex(desdeLabel, 0);
        GridPane.setRowIndex(desdeLabel, 0);

        TextField fieldDesdeFecha = new TextField();
        fieldDesdeFecha.setPrefSize(50, 6);
        fieldDesdeFecha.setScaleY(0.7);
        GridPane.setColumnIndex(fieldDesdeFecha, 1);
        GridPane.setRowIndex(fieldDesdeFecha, 0);

        Label hastaLabel = new Label("Hasta");
        hastaLabel.setStyle("-fx-font-size: 10;");
        GridPane.setColumnIndex(hastaLabel, 0);
        GridPane.setRowIndex(hastaLabel, 1);

        TextField fieldHastaFecha = new TextField("2025");
        fieldHastaFecha.setPrefSize(50, 6);
        fieldHastaFecha.setScaleY(0.7);
        GridPane.setColumnIndex(fieldHastaFecha, 1);
        GridPane.setRowIndex(fieldHastaFecha, 1);

        gridFecha.getChildren().addAll(desdeLabel, fieldDesdeFecha, hastaLabel, fieldHastaFecha);

        dateContainer.getChildren().addAll(dateFilterPane, gridFecha);
        return dateContainer;
    }
}
