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
public class Modificar_tablaController implements Initializable {

    @FXML
    private ChoiceBox<?> desp_campos_modificables;
    @FXML
    private Label lbl_nombretabla;
    @FXML
    private ChoiceBox<?> desp_tipo_dato;
    @FXML
    private TextField txt_nuevo_nombre;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_añadir_campo;
    @FXML
    private Button btn_borrar;
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
