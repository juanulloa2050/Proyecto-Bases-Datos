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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Button btn_modificar;
    @FXML
    private Button btn_refrescar;
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
    private TextField valorCondicionPrimaria=new TextField("dsad");
    private static String queriee;
    private static String TablaName;
    public static JDBC conection;
    private static ObservableList<String> informacionFila;
    private static ArrayList<String> columnasSeleccionadas=new ArrayList<>();
    private ArrayList<TextField> valores=new ArrayList<>();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void refrescar(){


        if (columnasSeleccionadas == null || columnasSeleccionadas.isEmpty()) {
            System.out.println("columnasSeleccionadas está vacía");
            return;
        }

        if (informacionFila == null || informacionFila.isEmpty()) {
            System.out.println("informacionFila está vacía");
            return;
        }

        if (AtributosxActualizar == null) {
            System.out.println("AtributosxActualizar es null");
            return;
        }
        valores.clear();
        AtributosxActualizar.getChildren().clear();
        for (int j = 0; j < columnasSeleccionadas.size(); j++) {
            Label atributo = new Label("Para "+columnasSeleccionadas.get(j));
            AtributosxActualizar.getChildren().add(atributo);
            TextField valor =new TextField(informacionFila.get(j));
            valores.add(valor);
            AtributosxActualizar.getChildren().add(valor);
        } 
    }
    public String queryUpdate(){
        StringBuilder queryUpdate=new StringBuilder();
        queryUpdate.append("UPDATE "+ TablaName+" SET ");
        for (int i = 0; i < columnasSeleccionadas.size(); i++) {
            queryUpdate.append(columnasSeleccionadas.get(i)+" = ");

            if (isNumeric(valores.get(i).getText())) {
                queryUpdate.append(valores.get(i).getText());
            }else{
                queryUpdate.append("'"+valores.get(i).getText()+"'");
            }
            if (i<columnasSeleccionadas.size()-1){
                queryUpdate.append(", ");
            }
        }
        queryUpdate.append(" where "+ primaryKey.getSelectionModel().getSelectedItem()+" "
                    +OperadorprimaryKey.getSelectionModel().getSelectedItem()+valorCondicionPrimaria.getText()+";");
        return queryUpdate.toString();
    }
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // Acepta números enteros o decimales
    }
    public void primaryKey(){
        primaryKey.getItems().clear();
        String query="SELECT COLUMN_NAME, COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '"+conection.getBaseDatos()+"' AND TABLE_NAME = '"+TablaName+"' and COLUMN_KEY IN('PRI', 'UNI');";
        primaryKey.getItems().addAll(conection.getDatafromOneField(query,"COLUMN_NAME"));
    }
    public void operadores(){
        String[] operadores = { "<", ">", "<=", ">=", "=", "<>", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL" };
        OperadorprimaryKey.getItems().clear();
        OperadorprimaryKey.getItems().addAll(operadores);
    }
    @FXML
    private void click_modificar(ActionEvent event) {
        //Hacer lo de la query
        System.out.println(queryUpdate());
        try {
            conection.Statment(queryUpdate());
            click_volver(event);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Esta seguro de continuar");
            alert.setHeaderText(null);
            alert.setContentText("Error con la base de datos, vuelve a intentarlo");
            alert.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Resultado_busquedas1.fxml"));
            Parent MostrarParent = loader.load();
            Scene MostrarScene = new Scene(MostrarParent);
            Resultado_busquedas1Controller rBusquedas1Controller = loader.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            rBusquedas1Controller.setConnection(conection);
            rBusquedas1Controller.setQuerie(queriee);
            rBusquedas1Controller.usarInformacion();
            rBusquedas1Controller.setNombreTabla(TablaName);
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
    public void setInformacionFila(ObservableList<String> inforow) {
        if (inforow != null) {
            informacionFila = inforow;
        }
    }
    public void setColumnasSeleccionadas(ArrayList<String> columnasSelected){
        columnasSeleccionadas=new ArrayList<>( columnasSelected);
    }
    
}
