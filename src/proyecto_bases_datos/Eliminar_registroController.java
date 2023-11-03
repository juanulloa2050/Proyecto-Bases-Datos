/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Eliminar_registroController implements Initializable {

    @FXML
    private Label txt_nombre_tabla;
    @FXML
    private ChoiceBox<?> desp_campos_tablas;
    @FXML
    private ChoiceBox<?> desp_operadores;
    @FXML
    private TextField txt_condicion;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_volver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
