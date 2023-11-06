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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    String dataBaseSelected;

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
    private Button btn_borar1;
    @FXML
    private Button btn_crear1;
    @FXML
    private ChoiceBox<?> box_SeleccionTabla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setConnection(JDBC connection) {
        conection=connection;
    }
    public void setDataBaseSelected(String DataBaseSelected ){
        dataBaseSelected=DataBaseSelected;
    }
    public static JDBC getConection(){
        return conection;
    }

    @FXML
    private void click_modificar(ActionEvent event) throws IOException{
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
    private void click_borrar_registro(ActionEvent event)  throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Eliminar_registro.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_nuevo_dato(ActionEvent event) {
    }
    
}
