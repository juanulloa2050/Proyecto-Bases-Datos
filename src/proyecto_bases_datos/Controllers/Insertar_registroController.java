/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Insertar_registroController implements Initializable {
    public static JDBC conection;
    @FXML
    private Button btn_insertar;
    @FXML
    private Button btn_refresh;
    @FXML
    private Label txt_titulo;
    @FXML
    private VBox vBoxRegistros;
    @FXML
    private Button btn_volver;
    private static ArrayList<String> atributos;
    private static ArrayList<String> tipos;
    private static ArrayList<TextField> listaTextField=new ArrayList<>();
    private static String tablaSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    public void refrescar(){
        listaTextField.clear();
        vBoxRegistros.getChildren().clear();
        for (int j = 0; j < atributos.size(); j++) {
            Label atributo = new Label("Para "+atributos.get(j));
            vBoxRegistros.getChildren().add(atributo);
            TextField valor =new TextField();
            valor.setPromptText("Ingrese el nuevo valor para "+atributos.get(j)+" de tipo "+ tipos.get(j));
            listaTextField.add(valor);
            vBoxRegistros.getChildren().add(valor);
        } 
    }
    public String queryInsertar(){
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO "+ tablaSelected + " VALUES (");
        for (int i = 0; i < listaTextField.size(); i++) {
            if (listaTextField.get(i).getText()!=null) {
                query.append("'"+listaTextField.get(i).getText()+"'");
            } 
            
            if(i!=listaTextField.size()-1){
                query.append(", ");
            }else {
                query.append(")");
            }            
        }


        return query.toString();
    }    

    @FXML
    private void click_insertar(ActionEvent event) throws IOException{
        try {
            conection.Statment(queryInsertar());
            Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Tablas.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Tablas");
            window.show();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Esta seguro de continuar");
            alert.setHeaderText(null);
            alert.setContentText("Problemas con la base de datos, vuelve a intentar");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Tablas");
        window.show();
    }
    //Setters
    public void setConnection(JDBC connection) {
        conection = connection;
    }
    public void setTiposSeleccionados(ArrayList<String> tiposSelected) {
        tipos=new ArrayList<>(tiposSelected);
        
    }
    public void setColumnasSeleccionadas(ArrayList<String> columnasSelected){
        atributos=new ArrayList<>( columnasSelected);
    }
    public void setTablaName(String tabla){
        tablaSelected=tabla;
    }
    
}
