package com.example.proyectohibernate.controller;

import com.example.proyectohibernate.dao.LibroDAO;
import com.example.proyectohibernate.model.Libro;
import com.sun.jdi.IntegerValue;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
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
    private TableColumn<Libro, BigDecimal> colPrecio;

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

    private final LibroDAO libroDAO = new LibroDAO();
    private Long idSeleccionado = null;


    @FXML
    private void initialize() {
        cargarTabla();
    }

    private void cargarTabla() {
        colId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getId()));
        colTitulo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitulo()));
        colAutor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutor()));
        colAnio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAnio_publicacion()));
        colPrecio.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPrecio()));
        cargar();
    }

    private void mostrarLibro() {
    }

    private void actualizarBotones(){
        boolean sinSeleccion = idSeleccionado == null;
        btnModificarLibro.setDisable(sinSeleccion);
        btnEliminarLibro.setDisable(sinSeleccion);
    }

    private Libro obtenerLibro(){
        String titulo = txtLibro.getText();
        String autor = txtAutor.getText();
        int anio = Integer.valueOf(txtPublicacion.getText());
        BigDecimal precio = new BigDecimal(txtPrecio.getText());
        return new  Libro(titulo, autor, anio, precio);
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
    void eliminar() {
        try {
            tblLibro.getSelectionModel().clearSelection();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion");
            alert.setHeaderText("Estas seguro de eliminar el libro?");

            ButtonType si = new ButtonType("Si");
            ButtonType no = new ButtonType("no");

            alert.getButtonTypes().setAll(si, no);
            alert.showAndWait();
        } finally {
            cargar();
        }

    }

    @FXML
    void guardar() {
        try {
            String titulo = txtLibro.getText();
            String autor = txtAutor.getText();
            int anio = Integer.valueOf(txtPublicacion.getText());
            BigDecimal precio = new BigDecimal(txtPrecio.getText().trim());

            Libro libro= new Libro(titulo, autor, anio, precio);
            LibroDAO.guardar(libro);
            cargar();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void limpiarCampos() {
        txtAutor.clear();
        txtLibro.clear();
        txtPrecio.clear();
        txtPublicacion.clear();
        txtAutor.requestFocus();
    }

    @FXML
    void modificar() {
        if(idSeleccionado == null){
            return;
        }

        try {
            Libro libro = obtenerLibro();
            libro.setId(idSeleccionado);
            boolean modificado = libroDAO.modificar(libro);
            if(!modificado){
                return;

            }
            cargarTabla();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
