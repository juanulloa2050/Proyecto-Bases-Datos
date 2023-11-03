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

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Añadir_keyController implements Initializable {

    @FXML
    private Label txt_nombre_tabla;
    @FXML
    private ChoiceBox<?> desp_campos_modificar;
    @FXML
    private ChoiceBox<?> desp_tipo_key;
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
    
}
