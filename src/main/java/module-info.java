module com.odvp.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.controlsfx.controls;
    requires java.smartcardio;
    requires jbcrypt;
    requires org.apache.logging.log4j;
    requires java.sql;

    opens com.odvp.biblioteca to javafx.fxml;
    exports com.odvp.biblioteca;
    exports com.odvp.biblioteca.ControladoresVistas;
    opens com.odvp.biblioteca.ControladoresVistas to javafx.fxml;
    exports com.odvp.biblioteca.ControladoresVistas.BookScene;
    opens com.odvp.biblioteca.ControladoresVistas.BookScene to javafx.fxml;
    exports com.odvp.biblioteca.ControladoresVistas.DefaultComponents;
    opens com.odvp.biblioteca.ControladoresVistas.DefaultComponents to javafx.fxml;
    opens com.odvp.biblioteca.ControladoresVistas.UsuarioScene to javafx.fxml;
    exports com.odvp.biblioteca.ControladoresVistas.LoginScene;
    opens com.odvp.biblioteca.ControladoresVistas.LoginScene to javafx.fxml;
}