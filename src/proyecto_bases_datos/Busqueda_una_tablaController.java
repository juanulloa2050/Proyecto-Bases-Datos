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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Busqueda_una_tablaController implements Initializable {
    public static JDBC conection;
    @FXML
    private Button btn_continuar;
    @FXML
    private Button btn_volver;
    @FXML
    private ChoiceBox<String> desp_tabla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     
     
    public void choicebox_action() {
        // Limpia la ChoiceBox
        desp_tabla.getItems().clear();
        // Agrega cada base de datos a la BOx
        try {
            desp_tabla.getItems().addAll(
                    conection.getDatafromOneField("SHOW TABLES;", "TABLES_IN_" + conection.getBaseDatos()));

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Revise la conexión con la base de datos");
            alert.showAndWait();
        }
    }   

    @FXML
    private void click_continuar(ActionEvent event) throws IOException {
        try{
            if (desp_tabla.getSelectionModel().getSelectedItem() == null) {
                throw new NullPointerException("");
            } 
            //envio de datos al siguiente controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Condiciones_busqueda.fxml"));
            loader.load();
            Condiciones_busquedaController condBusquedaCon=loader.getController();
            condBusquedaCon.setConnection(conection);
            condBusquedaCon.setTablaSelected(desp_tabla.getSelectionModel().getSelectedItem());
            //Cambiar de slide
            Parent MostrarParent = FXMLLoader.load(getClass().getResource("Condiciones_busqueda.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Tabla: "+desp_tabla.getSelectionModel().getSelectedItem());
            window.show();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("No seleccionó ninguna tabla, vuelva a intentarlo");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Busquedas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Busquedas");
        window.show();
    }
    public void setConnection(JDBC connection) {
        conection = connection;
    }

    public static JDBC getConection() {
        return conection;
    }
}
