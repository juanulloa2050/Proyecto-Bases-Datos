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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    @FXML
    private ChoiceBox<?> desp_atributo_relacion;
    @FXML
    private ChoiceBox<?> desp_operador_relacion;
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
    private Button btn_mas1;
    @FXML
    private Button btn_mas2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

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
    private void click_mas1(ActionEvent event) {
    }

    @FXML
    private void click_mas2(ActionEvent event) {
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
