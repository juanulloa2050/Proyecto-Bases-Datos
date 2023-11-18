/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class Busquedas_dos_tablasController implements Initializable {
    public static JDBC conection;
    @FXML
    private Button btn_continuar;
    @FXML
    private Button btn_volver;
    @FXML
    private ChoiceBox<String> desp_tabla2;
    @FXML
    private ChoiceBox<String> desp_tabla1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void choicebox1_action() {
        // Limpia la ChoiceBox
        desp_tabla1.getItems().clear();
        // Agrega cada base de datos a la BOx
        try {
            desp_tabla1.getItems().addAll(conection.getDatafromOneField("SHOW TABLES;", "TABLES_IN_" + conection.getBaseDatos()));
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Revise la coneccion con la base de datos");
            alert.showAndWait();
        }
    }
    public void choicebox2_action() {
    // Limpia la ChoiceBox
            desp_tabla2.getItems().clear();
            // Agrega cada base de datos a la BOx
            try {
                desp_tabla2.getItems().addAll(conection.getDatafromOneField("SHOW TABLES;", "TABLES_IN_" + conection.getBaseDatos()));
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Conection");
                alert.setHeaderText(null);
                alert.setContentText("Revise la coneccion con la base de datos");
                alert.showAndWait();
            }
    }     

    @FXML
    private void click_continuar(ActionEvent event) throws IOException {
        try{
            if (desp_tabla1.getSelectionModel().getSelectedItem() == null || desp_tabla2.getSelectionModel().getSelectedItem() == null) {
                throw new NullPointerException("");
            }
            else if (desp_tabla1.getSelectionModel().getSelectedItem().equalsIgnoreCase(desp_tabla2.getSelectionModel().getSelectedItem())){
                throw new SQLException("");
            }
            // envio de datos al frame condiciones busqueda cruzada
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Condiciones_busqueda_cruzada.fxml"));
            loader.load();
            Condiciones_busqueda_cruzadaController condBusquedaCruzada=loader.getController();
            condBusquedaCruzada.setConnection(conection);
            condBusquedaCruzada.setTablaSelected(desp_tabla1.getSelectionModel().getSelectedItem(),desp_tabla2.getSelectionModel().getSelectedItem() );
            //cambio de frame
            Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Condiciones_busqueda_cruzada.fxml"));
            Scene MostrarScene = new Scene(MostrarParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MostrarScene);
            window.setTitle("Tabla 1::"+ desp_tabla1.getSelectionModel().getSelectedItem()
                            +" Tabla 2::" +desp_tabla2.getSelectionModel().getSelectedItem());
            window.show();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Llene todos los espacios, por favor vuelva a intentarlo");
            alert.showAndWait();
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("No puede hacer una busqueda cruzada de dos tablas con el mismo nombre, por favor vuelva a intentarlo");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Busquedas.fxml"));
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
