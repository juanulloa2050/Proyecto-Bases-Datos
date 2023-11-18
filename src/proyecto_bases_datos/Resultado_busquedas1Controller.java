/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Resultado_busquedas1Controller implements Initializable {

    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_crear_vista;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_modificar;
    @FXML
    private TabPane tabPane_Tablaresultado;
    private String tablaSelected;
    private String queriee;
    public static JDBC conection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
    }    
      public void setConnection(JDBC connection) {
        conection = connection;
    }
    public void setTablaSelected(String tablaSelecteds) {
        tablaSelected = tablaSelecteds;
    }
    public void setQuerie(String querie) {
        queriee = querie;
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Condiciones_busqueda.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Condiciones Busqueda");
        window.show();
    }

    @FXML
    private void click_crear_vista(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Crear_vista.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Crear Vista");
        window.show();
    }

    @FXML
    private void click_borrar(ActionEvent event) {
    }

    @FXML
    private void click_modificar(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Modificar_registro.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Modificar registro");
        window.show();
    }

    public void usarInformacion() throws SQLException {
            // Ejecutar la consulta SQL y obtener el ResultSet
            ResultSet rs = conection.getRs(queriee);
            // Obtener los metadatos del ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println(metaData);
            // Obtener el número de columnas
            int columnCount = metaData.getColumnCount();
            // Añadir una pestaña al TabPane para cada columna en el resultado
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Tab newTab = new Tab(columnName);
                tabPane_Tablaresultado.getTabs().add(newTab);
            }
    }
}
