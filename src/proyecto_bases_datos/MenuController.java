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

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class MenuController implements Initializable {

    @FXML
    private ChoiceBox<?> desp_bases;
    @FXML
    private Button btn_acceder;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
