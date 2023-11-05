/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class InterfazController implements Initializable {
    JDBC connection = new JDBC();
    @FXML
    private AnchorPane T;
    @FXML
    private TextField txt_usr;
    @FXML
    private TextField txt_puerto;
    @FXML
    private TextField txt_maquina;
    @FXML
    private PasswordField txt_clave;
    @FXML
    private Button btn_acceder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void click_acceder(ActionEvent event) throws IOException {
        String usuario = txt_usr.getText();
        String puerto = txt_puerto.getText();
        String maquina = txt_maquina.getText();
        String clave = txt_clave.getText();
        if (usuario.isEmpty() || puerto.isEmpty() || maquina.isEmpty() || clave.isEmpty()) {
            // Mostrar un mensaje de error
            JOptionPane.showMessageDialog(null,"Por favor, rellene todos los campos.");
        } else {
            // Aquí puedes continuar con tu lógica de negocio
            String url = "jdbc:mysql://localhost:"+puerto;
            connection.conectarBase(url, usuario, clave);
            //toca poner un if para saber si si tiene acceso o no al sistema, si si entonces se muestra lo de abajo
            
            Parent MostrarParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Menu");
            window.show();
        }
    }
}
