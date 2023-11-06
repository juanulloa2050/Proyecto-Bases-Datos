/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Modificar_tablaController implements Initializable {
    static JDBC conection;
    static String tableSelected;
    @FXML
    private ChoiceBox<?> desp_campos_modificables;
    @FXML
    private Label lbl_nombretabla; //Cambiar el valor de este label, debera tener un texto asi: ¿Cual campo desea modificar?
    @FXML
    private ChoiceBox<?> desp_tipo_dato;
    @FXML
    private TextField txt_nuevo_nombre;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_añadir_campo;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_añadir_key;
    @FXML
    private Button btn_volver;

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
    
    public void setTableSelected(String tableSelectedd ){
        tableSelected=tableSelectedd;
    }
    public static JDBC getConection(){
        return conection;
    }    

    @FXML
    private void click_modificar(ActionEvent event) {
        JOptionPane.showMessageDialog(null, tableSelected);
    }

    @FXML
    private void click_campo(ActionEvent event) {
    }

    @FXML
    private void click_atributo(ActionEvent event) {
    }

    @FXML
    private void click_key(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Añadir_key.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }
    
}
