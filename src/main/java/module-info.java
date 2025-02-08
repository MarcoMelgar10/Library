module com.odvp.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;

    requires org.controlsfx.controls;

    opens com.odvp.biblioteca to javafx.fxml;
    exports com.odvp.biblioteca;
    exports com.odvp.biblioteca.ControladoresVistas;
    opens com.odvp.biblioteca.ControladoresVistas to javafx.fxml;
    exports com.odvp.biblioteca.ControladoresVistas.BookScene;
    opens com.odvp.biblioteca.ControladoresVistas.BookScene to javafx.fxml;
}