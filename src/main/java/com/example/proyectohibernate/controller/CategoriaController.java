package com.example.proyectohibernate.controller;

import com.example.proyectohibernate.dao.CategoriaDAO;
import com.example.proyectohibernate.model.Categoria;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class CategoriaController {
    @FXML
    private Button btModificar;

    @FXML
    private Button btnCargarCategorias;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private TableColumn<Categoria, String> colDescripcion;

    @FXML
    private TableColumn<Categoria, Long> colId;

    @FXML
    private TableColumn<Categoria, String> colNombre;

    @FXML
    private TableView<Categoria> tblCategorias;

    @FXML
    private TextField txtDescripcionCategoria;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private void initialize() {
        cargarTabla();
    }

    private void cargarTabla() {
        colId.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getId()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colDescripcion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescripcion()));
        cargar();
    }

    @FXML
    void cargar() {
        try {
            List<Categoria> categorias = CategoriaDAO.listar();
            tblCategorias.setItems(FXCollections.observableArrayList(categorias));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        try {
            String nombre = txtNombreCategoria.getText();
            String descripcion = txtDescripcionCategoria.getText();

            Categoria categoria = new Categoria(nombre, descripcion);
            CategoriaDAO.guardar(categoria);
            cargar();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void limpiar(ActionEvent event) {
        txtNombreCategoria.clear();
        txtDescripcionCategoria.clear();
        txtNombreCategoria.requestFocus();
    }
}
