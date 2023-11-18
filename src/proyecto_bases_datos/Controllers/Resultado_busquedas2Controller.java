/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Resultado_busquedas2Controller implements Initializable {

    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_crear_vista;
    private JDBC conection;
    private String queriee;
    @FXML
    private TabPane tabPane_Tablaresultado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Condiciones_busqueda_cruzada.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Condiciones Busqueda");
        window.show();
    }

    @FXML
    private void click_crear_vista(ActionEvent event) {
    }
          public void setConnection(JDBC connection) {
        conection = connection;
    }
    public void setQuerie(String querie) {
        queriee = querie;
    }
    public void usarInformacion() throws SQLException {
        // Ejecutar la consulta SQL y obtener el ResultSet
        ResultSet rs = conection.getRs(queriee);
        // Obtener los metadatos del ResultSet
        ResultSetMetaData metaData = rs.getMetaData();
        // Obtener el número de columnas
        int columnCount = metaData.getColumnCount();
    
        // Crear una nueva TableView
        TableView<ObservableList<String>> tableView = new TableView<>();
    
        // Crear una columna para cada columna en el ResultSet
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
            final int columnIndex = i - 1;
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(columnIndex)));
            tableView.getColumns().add(column);
        }
    
        // Cargar los datos en la tabla
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getString(i));
            }
            tableView.getItems().add(row);
        }
    
        // Añadir la TableView a la primera pestaña
        if (tabPane_Tablaresultado.getTabs().isEmpty()) {
            Tab newTab = new Tab();
            tabPane_Tablaresultado.getTabs().add(newTab);
        }
        Tab tab = tabPane_Tablaresultado.getTabs().get(0);
        tab.setContent(tableView);
    }
    
}
