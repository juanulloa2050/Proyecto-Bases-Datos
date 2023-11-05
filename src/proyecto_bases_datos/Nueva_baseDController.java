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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Nueva_baseDController implements Initializable {

    @FXML
    private TextField txt_nombre_baseD;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_volver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_crear(ActionEvent event) throws IOException {
        
        String nombre_base = txt_nombre_baseD.getText();
        
        //crear base de datos
        
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Menu");
            window.show();
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Menu");
            window.show();
    }
    
}
