/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class TablasController implements Initializable {
    public static JDBC conection;
    static String dataBaseSelected;
    @FXML
    private TabPane TabPane_Tablas;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_modificar_registro;
    @FXML
    private ChoiceBox<String> box_SeleccionTabla;
    @FXML
    private Button btn_borar_registro;
    @FXML
    private Button btn_crear_datos;
    static String GETTABLES = "SHOW TABLES;";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usarInformacion();
    }
 public static void setDataBaseSelected(String DataBaseSelected) {
        dataBaseSelected = DataBaseSelected;
    }
    public void usarInformacion() {
    try {        for (String tabla : TablasController.getConection().getDatafromOneField(GETTABLES, "TABLES_IN_" + dataBaseSelected)) {
        Tab newTab = new Tab(tabla);
        TabPane_Tablas.getTabs().add(newTab);
    }
    } catch (NullPointerException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Conection");
        alert.setHeaderText(null);
        alert.setContentText("Revise la coneccion con la base de datos");
        alert.showAndWait();
    }

    
}

    @FXML
    public void choicebox_action() {
        // Limpia la ChoiceBox
        box_SeleccionTabla.getItems().clear();
        // Agrega cada base de datos a la BOx
        try {
            box_SeleccionTabla.getItems().addAll(
                    TablasController.getConection().getDatafromOneField(GETTABLES, "TABLES_IN_" + dataBaseSelected));

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Revise la coneccion con la base de datos");
            alert.showAndWait();
        }

    }

    public void setConnection(JDBC connection) {
        conection = connection;
    }

    public static JDBC getConection() {
        return conection;
    }

    @FXML
    private void click_modificar(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Modificar_tabla.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_crear(ActionEvent event) {
    }

    @FXML
    private void click_borrar(ActionEvent event) {
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_modificar_registro(ActionEvent event) {
    }

    @FXML
    private void click_borrar_registro(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Eliminar_registro.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_nuevo_dato(ActionEvent event) {
    }

    @FXML
    private void click_buscar(ActionEvent event) {
    }

}
