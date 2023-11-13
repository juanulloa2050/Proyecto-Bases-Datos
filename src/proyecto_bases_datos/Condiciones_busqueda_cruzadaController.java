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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Condiciones_busqueda_cruzadaController implements Initializable {
    public static JDBC conection;
    static String tablaSelected1;
    static String tablaSelected2;
    private ArrayList<ChoiceBox<String>> choiceBoxAtributosTabla1 =new ArrayList<>();
    private ArrayList<ChoiceBox<String>> choiceBoxAtributosTabla2 =new ArrayList<>();
    @FXML
    private ChoiceBox<String> desp_atributo_relacionTabla1;
    @FXML
    private ChoiceBox<String> desp_atributo_relacionTabla2;
    @FXML
    private ChoiceBox<String> desp_operador_relacion;
    @FXML
    private TextField txt_valor_relacion;
    @FXML
    private ChoiceBox<?> dep_atributo1;
    @FXML
    private ChoiceBox<?> desp_operador1;
    @FXML
    private ChoiceBox<?> desp_atributo2;
    @FXML
    private ChoiceBox<?> desp_operador2;
    @FXML
    private Button btn_continuar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_masTabla1;
    @FXML
    private Button btn_menosTabla1;
    @FXML
    private Button btn_masTabla2;
    @FXML
    private Button btn_menosTabla2;
    @FXML
    private VBox vBoxAddAtributosTabla1;
    @FXML
    private VBox vBoxAddAtributosTabla2;
    private int contadorAtributosTabla1 = 1;
    private int contadorAtributosTabla2 = 1;
    String[] operadores = {"<", ">", "<=", ">=", "=", "<>", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    //Tabla 1
    @FXML
    private void click_masTabla1(ActionEvent event) {
        //creo el label y el choice box
        Label numeroAtributo = new Label("Atributo #" + contadorAtributosTabla1);
        ChoiceBox<String> nuevoAtributo = new ChoiceBox<>();
        nuevoAtributo.getItems().clear();
        nuevoAtributo.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected1+";","Field"));
        //Agregarlo a la lista local
        choiceBoxAtributosTabla1.add(nuevoAtributo);
        //Agregarlos al drame
        vBoxAddAtributosTabla1.getChildren().add(numeroAtributo);
        vBoxAddAtributosTabla1.getChildren().add(nuevoAtributo);

    // Incrementar el contador de atributos
    contadorAtributosTabla1++;
    }
    @FXML
    private void click_menusTabla1(ActionEvent event) {
        // Verificar si hay elementos para eliminar
        if (!vBoxAddAtributosTabla1.getChildren().isEmpty()) {
            // Eliminar el último elemento (Label o ChoiceBox)
            vBoxAddAtributosTabla1.getChildren().remove(vBoxAddAtributosTabla1.getChildren().size() - 1);
            //Eliminar el label
            vBoxAddAtributosTabla1.getChildren().remove(vBoxAddAtributosTabla1.getChildren().size() - 1);
            //ELiminarlo del array de choice box
            contadorAtributosTabla1--;
            choiceBoxAtributosTabla1.remove(contadorAtributosTabla1-1);
        } else {
            System.out.println("No hay elementos para eliminar.");
        }
    }
    
    //Tabla 2
    @FXML
    private void click_masTabla2(ActionEvent event) {
        //creo el label y el choice box
        Label numeroAtributo = new Label("Atributo #" + contadorAtributosTabla2);
        ChoiceBox<String> nuevoAtributo = new ChoiceBox<>();
        nuevoAtributo.getItems().clear();
        nuevoAtributo.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected2+";","Field"));
        //Agregarlo a la lista local
        choiceBoxAtributosTabla2.add(nuevoAtributo);
        //Agregarlos al drame
        vBoxAddAtributosTabla2.getChildren().add(numeroAtributo);
        vBoxAddAtributosTabla2.getChildren().add(nuevoAtributo);

    // Incrementar el contador de atributos
    contadorAtributosTabla2++;
    }
    @FXML
    private void click_menusTabla2(ActionEvent event) {
        // Verificar si hay elementos para eliminar
        if (!vBoxAddAtributosTabla2.getChildren().isEmpty()) {
            // Eliminar el último elemento (Label o ChoiceBox)
            vBoxAddAtributosTabla2.getChildren().remove(vBoxAddAtributosTabla2.getChildren().size() - 1);
            //Eliminar el label
            vBoxAddAtributosTabla2.getChildren().remove(vBoxAddAtributosTabla2.getChildren().size() - 1);
            //ELiminarlo del array de choice box
            contadorAtributosTabla2--;
            choiceBoxAtributosTabla2.remove(contadorAtributosTabla2-1);
        } else {
            System.out.println("No hay elementos para eliminar.");
        }
    }
    




    //RElacion
    @FXML
    private void operadorRelacion(){
        desp_operador_relacion.getItems().clear();
        // Agregar operadores al ChoiceBox desp_operador1
        desp_operador_relacion.getItems().addAll(operadores);
    }
    //Atributos
    @FXML
    private void atributoTabla1Relacion(){
        desp_atributo_relacionTabla1.getItems().clear();
        desp_atributo_relacionTabla1.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected1+";","Field"));
    }
    @FXML
    private void atributoTabla2Relacion(){
        desp_atributo_relacionTabla2.getItems().clear();
        desp_atributo_relacionTabla2.getItems().addAll(conection.getDatafromOneField("Describe "+tablaSelected2+";","Field"));
    }
    

    //Metodos funcionales
    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Busquedas_dos_tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Busqueda dos tabla");
        window.show();
    }
    @FXML
    private void click_continuar(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Resultado_busquedas2.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Resultado Busqueda");
        window.show();
    }
    public void setConnection(JDBC connection) {
        conection = connection;
    }

    public static JDBC getConection() {
        return conection;
    }
    public void setTablaSelected(String tablaSelecteds1,String tablaSelecteds2 ) {
        tablaSelected1=tablaSelecteds1;
        tablaSelected2=tablaSelecteds2;
    }
    
}
