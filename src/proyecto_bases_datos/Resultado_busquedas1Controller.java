/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Resultado_busquedas1Controller implements Initializable {

    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_crear_vista;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_modificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Condiciones_busqueda.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Condiciones Busqueda");
        window.show();
    }

    @FXML
    private void click_crear_vista(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Crear_vista.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Crear Vista");
        window.show();
    }

    @FXML
    private void click_borrar(ActionEvent event) {
    }

    @FXML
    private void click_modificar(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Modificar_registro.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Modificar registro");
        window.show();
    }
    
}