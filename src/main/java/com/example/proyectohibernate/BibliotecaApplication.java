package com.example.proyectohibernate;

import com.example.proyectohibernate.database.JPAUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BibliotecaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BibliotecaApplication.class.getResource("libro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestión de libros");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        JPAUtil.cerrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
