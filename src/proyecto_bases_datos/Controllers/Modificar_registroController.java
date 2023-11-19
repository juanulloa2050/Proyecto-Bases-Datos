/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Modificar_registroController implements Initializable {

    @FXML
    private Button btn_añadir;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_volver;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox AtributosxActualizar;
    @FXML
    private ChoiceBox<String> primaryKey;
    @FXML
    private ChoiceBox<String> OperadorprimaryKey;
    @FXML 
    private TextField valorCondicionPrimaria;
    private String queriee;
    private String TablaName;
    public static JDBC conection;
    private List<String> informacionFila;
    private List<String> columnasSeleccionadas = new ArrayList<>();
    private ArrayList<TextField> valores=new ArrayList<>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void todosLosCamposxModificar(){
        
        for (int j = 0; j < columnasSeleccionadas.size(); j++) {
            Label atributo = new Label("Para "+columnasSeleccionadas.get(j));
            TextField valor =new TextField(informacionFila.get(j));
            valores.add(valor);
            AtributosxActualizar.getChildren().add(atributo);
            AtributosxActualizar.getChildren().add(valor);
        }
    }    

    @FXML
    private void click_añadir(ActionEvent event) {
    }

    @FXML
    private void click_buscar(ActionEvent event) {
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Resultado_busquedas1.fxml"));
            loader.load();
            Resultado_busquedas1Controller rBusquedas1Controller = loader.getController();
            rBusquedas1Controller.setConnection(conection);
            rBusquedas1Controller.setQuerie(queriee);
            rBusquedas1Controller.usarInformacion();
            rBusquedas1Controller.setNombreTabla(TablaName);
            Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Resultado_busquedas1.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Resultado Busqueda");
            window.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Metodos setters 
    public void setConnection(JDBC connection) {
        conection = connection;
    }
    public void setQuerie(String querie) {
        queriee = querie;
    }
    public void setNombreTabla(String nombre) {
        TablaName = nombre;
    }
    public void setInformacionFila(List<String> inforow){
        informacionFila=inforow;
    }
    public void setColumnasSeleccionadas(List<String> columnasSelected){
        columnasSeleccionadas=columnasSelected;
    }
    
}
