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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class TablasController implements Initializable {

    @FXML
    private ChoiceBox<?> desp_tablas;
    @FXML
    private TableView<?> tablas;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_borar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_modificar1;
    @FXML
    private Button btn_crear1;
    @FXML
    private Button btn_borar1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
