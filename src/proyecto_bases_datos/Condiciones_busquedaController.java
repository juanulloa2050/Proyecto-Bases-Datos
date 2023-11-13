/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Condiciones_busquedaController implements Initializable {
    public static JDBC conection;
    static String tablaSelected;
    private ArrayList<ChoiceBox<String>> choiceBoxAtributos =new ArrayList<>();
    @FXML
    private ChoiceBox<String> dep_atributo1;
    @FXML
    private ChoiceBox<String> desp_operador1;
    @FXML
    private ChoiceBox<String> desp_atributo2;
    @FXML
    private ChoiceBox<String> desp_operador2;
    @FXML
    private Button btn_continuar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_mas1;
    @FXML
    private Button btn_menus1;
    @FXML
    private ScrollPane scrollPane;
     @FXML
    private VBox vBoxAddAtributos;
    private int contadorAtributos = 1;
    String[] operadores = {"<", ">", "<=", ">=", "=", "<>", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL"};


    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
   
    @FXML //Aca toca hacer la busqueda sql.. Feo :(
    private void click_continuar(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Resultado_busquedas1.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Resultado Busqueda");
        window.show();
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Busqueda_una_tabla.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Busqueda dos tabla");
        window.show();
    }
    @FXML
    private void click_mas1(ActionEvent event) {
        //creo el label y el choice box
        Label numeroAtributo = new Label("Atributo #" + contadorAtributos);
        ChoiceBox<String> nuevoAtributo = new ChoiceBox<>();
        nuevoAtributo.getItems().clear();
        nuevoAtributo.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected+";","Field"));
        //Agregarlo a la lista local
        choiceBoxAtributos.add(nuevoAtributo);
        //Agregarlos al drame
        vBoxAddAtributos.getChildren().add(numeroAtributo);
        vBoxAddAtributos.getChildren().add(nuevoAtributo);

    // Incrementar el contador de atributos
    contadorAtributos++;
    }
    @FXML
    private void click_menus1(ActionEvent event) {
    // Verificar si hay elementos para eliminar
    if (!vBoxAddAtributos.getChildren().isEmpty()) {
        // Eliminar el último elemento (Label o ChoiceBox)
        vBoxAddAtributos.getChildren().remove(vBoxAddAtributos.getChildren().size() - 1);
        //Eliminar el label
        vBoxAddAtributos.getChildren().remove(vBoxAddAtributos.getChildren().size() - 1);
        //ELiminarlo del array de choice box
        contadorAtributos--;
        choiceBoxAtributos.remove(contadorAtributos-1);
     } else {
        System.out.println("No hay elementos para eliminar.");
    }
}



    public void setConnection(JDBC connection) {
        conection = connection;
    }
    public static JDBC getConection() {
        return conection;
    }
    public void setTablaSelected(String tablaSelecteds) {
        tablaSelected=tablaSelecteds;
    }
    //Operadores 
    @FXML
    private void operadore2(){
        desp_operador2.getItems().clear();
        // Agregar operadores al ChoiceBox desp_operador2
        desp_operador2.getItems().addAll(operadores);
    }
    @FXML
    private void operadore1(){
        desp_operador1.getItems().clear();
        // Agregar operadores al ChoiceBox desp_operador1
        desp_operador1.getItems().addAll(operadores);
    }
    //Atributos
    @FXML
    private void atributo1(){
        dep_atributo1.getItems().clear();
        dep_atributo1.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected+";","Field"));

    }
    @FXML
    private void atributo2(){
        desp_atributo2.getItems().clear();
        desp_atributo2.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected+";","Field"));
    }
    
}
