package com.example.proyectohibernate.controller;

import com.example.proyectohibernate.dao.LibroDAO;
import com.example.proyectohibernate.model.Libro;
import com.sun.jdi.IntegerValue;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class LibroController {

    @FXML
    private Button btnCargarLibros;

    @FXML
    private Button btnEliminarLibro;

    @FXML
    private Button btnGuardarLibro;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private Button btnModificarLibro;

    @FXML
    private TableColumn<Libro, Integer> colAnio;

    @FXML
    private TableColumn<Libro, String> colAutor;

    @FXML
    private TableColumn<Libro, Long> colId;

    @FXML
    private TableColumn<Libro, Double> colPrecio;

    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    private TableView<Libro> tblLibro;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtLibro;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtPublicacion;

    @FXML
    private Label welcomeText;

    @FXML
    private void initialize() {
        colId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getId()));
        colTitulo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitulo()));
        colAutor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutor()));
        colAnio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAnio_publicacion()));
        colPrecio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrecio()));
        cargarTabla();
    }

    private void cargarTabla() {
    }

    private void mostrarLibro() {
    }


    @FXML
    void cargar() {
        try {
            List<Libro> libros = LibroDAO.listar();
            tblLibro.setItems(FXCollections.observableArrayList(libros));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void guardar() {
        try {
            String titulo = txtLibro.getText();
            String autor = txtAutor.getText();
            int anio = Integer.valueOf(txtPublicacion.getText());
            double precio = Double.valueOf(txtPrecio.getText());

            Libro libro= new Libro(titulo, autor, anio, precio);
            LibroDAO.guardar(libro);
            cargar();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void limpiarCampos(ActionEvent event) {

    }

    @FXML
    void modificar(ActionEvent event) {

    }
}
