module com.karp.javastroids_v2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires ucanaccess;

    opens com.karp.javastroids_v2 to javafx.fxml, com.google.gson;
    exports com.karp.javastroids_v2;
}
