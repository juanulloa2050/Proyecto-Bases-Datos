/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class TablasController implements Initializable {
    public static JDBC conection;
    static String dataBaseSelected;
    @FXML
    private TabPane TabPane_Tablas;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_borrar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_modificar_registro;
    @FXML
    private ChoiceBox<String> box_SeleccionTabla;
    @FXML
    private Button btn_borar_registro;
    @FXML
    private Button btn_crear_datos;
    static String GETTABLES = "SHOW TABLES;";

    /**
     * Initializes the controller class.
     */
    public static void setDataBaseSelected(String DataBaseSelected) {
        dataBaseSelected = DataBaseSelected;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usarInformacion();
    }
 
public void usarInformacion() {
    try {

        for (String tabla : conection.getDatafromOneField(GETTABLES, "TABLES_IN_" + dataBaseSelected)) {
            Tab newTab = new Tab(tabla);

            // Crear un TableView para mostrar la información de la tabla
            TableView<Map<String, String>> tableView = new TableView<>();

            // Obtener la estructura de la tabla usando el comando DESCRIBE
            String DESCRIBE_TABLE = "DESCRIBE " + tabla;
            ArrayList<Map<String, String>> estructuraTabla = conection.describeTable(DESCRIBE_TABLE);

            // Añadir una columna al TableView para cada campo de la tabla
            String[] campos = {"Field", "Type", "Key", "Default", "Extra"};
            for (String campo : campos) {
                TableColumn<Map<String, String>, String> column = new TableColumn<>(campo);
                column.setCellValueFactory(new MapValueFactory(campo));
                tableView.getColumns().add(column);
            }

            // Añadir los datos de la tabla al TableView
            ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
            data.addAll(estructuraTabla);
            tableView.setItems(data);

            // Añadir el TableView al Tab
            newTab.setContent(tableView);

            // Añadir el Tab al TabPane
            TabPane_Tablas.getTabs().add(newTab);
        }
    } catch (NullPointerException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Conection");
        alert.setHeaderText(null);
        alert.setContentText("Revise la coneccion con la base de datos Map");
        alert.showAndWait();
    }
}


    @FXML
    public void choicebox_action() {
        // Limpia la ChoiceBox
        box_SeleccionTabla.getItems().clear();
        // Agrega cada base de datos a la BOx
        try {
            box_SeleccionTabla.getItems().addAll(
                    TablasController.getConection().getDatafromOneField(GETTABLES, "TABLES_IN_" + dataBaseSelected));

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Revise la coneccion con la base de datos");
            alert.showAndWait();
        }

    }

    public void setConnection(JDBC connection) {
        conection = connection;
    }

    public static JDBC getConection() {
        return conection;
    }

    @FXML
    private void click_modificar(ActionEvent event) throws IOException {
        //Pasar informacion controller
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Modificar_tabla.fxml"));
        Parent root =loader.load();
        TablasController.getConection();
        Modificar_tablaController modtablasController= loader.getController();
        modtablasController.setConnection(conection);
        //TODO Añadir settablaselected.!!!!
        modtablasController.setTableSelected(box_SeleccionTabla.getValue());        

        //Change the slide
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Modificar_tabla.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Modificar tabla: "+"ENTABLA:::   "+conection.getBaseDatos()
        );
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_crear(ActionEvent event) {
    }

    @FXML
    private void click_borrar(ActionEvent event) {
        String nombreCompleto = TabPane_Tablas.getSelectionModel().getSelectedItem().getText();
    String[] partes = nombreCompleto.split("\\.");
    String nombreTabla = partes[partes.length - 1];
        Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmación");
    alert.setHeaderText("Estás a punto de eliminar "+ nombreTabla);
    alert.setContentText("¿Estás seguro de que quieres continuar? Esta acción es irreversible.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        String DROPTABLE="Drop table "+nombreTabla;
        conection.Statment("Use "+dataBaseSelected);
        conection.Statment(DROPTABLE);   
        //Actualiza la tabla
        TabPane_Tablas.getTabs().remove(TabPane_Tablas.getSelectionModel().getSelectedItem());  
    } 
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_modificar_registro(ActionEvent event) {
    }

    @FXML
    private void click_borrar_registro(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Eliminar_registro.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();

    }

    @FXML
    private void click_nuevo_dato(ActionEvent event) {
    }

    @FXML
    private void click_buscar(ActionEvent event) {
    }

}
