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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView tabPane_Tablaresultado =null;
    private static String queriee;
    private String TablaName;
    public static JDBC conection;
    private ArrayList<String> columnasSeleccionadas = new ArrayList<>();
    


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
    public void setQuerie(String querie) {
        queriee = querie;
    }
    public void setNombreTabla(String nombre) {
        TablaName = nombre;
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Condiciones_busqueda.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Condiciones Busqueda");
        window.show();
    }

    @FXML
    private void click_crear_vista(ActionEvent event){
        //Pedir al usuario el nombre para la vista
        String nombreVista = JOptionPane.showInputDialog("Ingrese el nombre de la vista");
        // Crear la consulta para crear la vista
        String queryCrearVista = "CREATE VIEW " + nombreVista + TablaName+" AS " + queriee+";";
        // Ejecutar la consulta para crear la vista
        try {
            conection.Statment(queryCrearVista);
            JOptionPane.showMessageDialog(null, "Vista creada con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear la vista");
        }

    }   
    //Lo que debe estar despues de lo del click derecho encima de la tabla
    @FXML
    private void click_borrar(ActionEvent event) {
        System.out.println(columnasSeleccionadas);
        System.out.println(informacionFila);
        
        StringBuilder queryBorrarRegistro = new StringBuilder();
        queryBorrarRegistro.append("DELETE FROM "+TablaName+" WHERE ");

        for (int i = 0; i < columnasSeleccionadas.size(); i++) {
            queryBorrarRegistro.append(columnasSeleccionadas.get(i));
            if (informacionFila.get(i) == null) {
                queryBorrarRegistro.append(" IS NULL");
            } else {
                queryBorrarRegistro.append(" = '").append(informacionFila.get(i)).append("'");
            }
            if (i != columnasSeleccionadas.size() - 1) {
                queryBorrarRegistro.append(" AND ");
            }
        }

        queryBorrarRegistro.append(";");
        System.out.println(queryBorrarRegistro.toString());
        // Ejecutar la consulta DELETE
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Estás a punto de eliminar un registro");
        alert.setContentText("¿Estás seguro de que quieres continuar? Esta acción es irreversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
            conection.Statment(queryBorrarRegistro.toString());
            //actualizar tableview
            tabPane_Tablaresultado.getItems().clear();
            tabPane_Tablaresultado.getColumns().clear();
            usarInformacion(); 
            } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al borrar el registro"+e.getMessage());
            }
        }
    }
    ObservableList<String> informacionFila;
    @FXML
    private void click_izquierdo() {
        // Obtener la fila seleccionada
        if (tabPane_Tablaresultado.getSelectionModel().getSelectedItem() != null) {
                    informacionFila = (ObservableList<String>) tabPane_Tablaresultado.getSelectionModel().getSelectedItem();
            System.out.println(informacionFila);
        }
    }
    @FXML
    private void click_modificar(ActionEvent event) throws IOException {
        //enviar datos
        FXMLLoader lod=new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Modificar_registro.fxml"));
        lod.load();
        //cambio slide
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Modificar_registro.fxml"));
        if (informacionFila == null) {
            System.out.println("No se ha seleccionado ninguna fila");
        }else{
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Modificar_registroController modRegistro=lod.getController();
        modRegistro.setConnection(conection);
        modRegistro.setQuerie(queriee);
        modRegistro.setNombreTabla(TablaName);
        modRegistro.setInformacionFila(informacionFila);
        modRegistro.setColumnasSeleccionadas(columnasSeleccionadas);
        modRegistro.refrescar();
        window.setScene(MostrarScene);
        
        window.setTitle("Modificar registro");
        window.show();}

    }
    public void usarInformacion() throws SQLException {
        // Ejecutar la consulta SQL y obtener el ResultSet
        ResultSet rs = conection.getRs(queriee);
        // Obtener los metadatos del ResultSet
        ResultSetMetaData metaData = rs.getMetaData();
        // Obtener el número de columnas
        int columnCount = metaData.getColumnCount();
    
        // Crear una columna para cada columna en el ResultSet
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
            final int columnIndex = i - 1;
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(columnIndex)));
            tabPane_Tablaresultado.getColumns().add(column);
            columnasSeleccionadas.add(columnName);
        }
    
        // Cargar los datos en la tabla
        while (rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getString(i));
            }
            tabPane_Tablaresultado.getItems().add(row);
        }
    }


    
}

