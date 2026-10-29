module com.example.proyectohibernate {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectohibernate to javafx.fxml;
    exports com.example.proyectohibernate;
}