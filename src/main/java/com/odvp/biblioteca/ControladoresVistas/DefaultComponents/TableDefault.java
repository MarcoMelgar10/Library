package com.odvp.biblioteca.ControladoresVistas.DefaultComponents;

import com.odvp.biblioteca.FuncionesMaestros.MaestroLibros.ManejoLibros.IDatoVisual;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public abstract class TableDefault extends VBox {

    private VBox cardsPane;
    private List<Card> cards = new ArrayList<>();
    private List<ColumnConstraints> columnConstraints;

    public TableDefault(List<String> titulos, List<Integer> ancho, List<Boolean> seExpanden, List<Boolean> centrar) {
        this.columnConstraints = new ArrayList<>();
        GridPane headerGrid = new GridPane();
        headerGrid.setPadding(new Insets(0, 18, 0, 8));
        headerGrid.setMaxHeight(20);
        for (int i=0;i<titulos.size(); i++) {
            ColumnConstraints cc = new ColumnConstraints(ancho.get(i));
            cc.setMaxWidth(USE_COMPUTED_SIZE);
            if(seExpanden.get(i)) cc.setPercentWidth(-1);
            else cc.setPercentWidth(0);
            if(centrar.get(i)) cc.setHalignment(HPos.CENTER);
            cc.setHgrow(Priority.SOMETIMES);
            cc.setFillWidth(true);
            headerGrid.getColumnConstraints().add(cc);
            Label label = new Label(titulos.get(i));
            label.getStyleClass().add("negrita");
            headerGrid.add(label, i,0);
            this.columnConstraints.add(cc);
        }
        cardsPane = new VBox(5);
        ScrollPane scrollPane = new ScrollPane(cardsPane);
        scrollPane.getStyleClass().add("scrollPane");
        scrollPane.setFitToWidth(true);
        getChildren().addAll(headerGrid, scrollPane);
    }

    public void addCards(List<IDatoVisual> datos) {
        cardsPane.getChildren().clear();
        cards.clear();
        for(IDatoVisual dato : datos){
            Card card = new Card(dato);
            cardsPane.getChildren().add(card.getVista());
            cards.add(card);
        }
        setCardsAction();
    }

    public List<Card> getCards() {
        return cards;
    }

    public abstract void setCardsAction();


    public class Card{
        private int ID;
        private GridPane vista;
        public Card(IDatoVisual dato){
            ID = dato.getID();
            GridPane bookGrid = new GridPane();
            List<Node> elementos = new ArrayList<>();
            for(Object elemento : dato.getDatos()){
                if(elemento instanceof String){
                    elementos.add(new Label((String) elemento));
                } else if (elemento instanceof  Integer) {
                    elementos.add(new Label(elemento + ""));
                } else if(elemento instanceof Image){
                    ImageView image = new ImageView((Image) elemento);
                    image.setFitHeight(32);
                    image.setFitWidth(32);
                    elementos.add(image);
                }
            }
            int nroElemento = 0;
            for (ColumnConstraints cc : columnConstraints) {
                bookGrid.getColumnConstraints().add(cc);
                bookGrid.add(elementos.get(nroElemento), nroElemento, 0);
                nroElemento++;
            }
            bookGrid.getStyleClass().add("card");
            bookGrid.setPadding(new Insets(8,8,8,8));
            vista = bookGrid;
        }

        public int getID() {
            return ID;
        }

        public GridPane getVista() {
            return vista;
        }
    }
}
