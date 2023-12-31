/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos.Controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Modificar_tablaController implements Initializable {
    static JDBC conection;
    static String tableSelected;
    @FXML
    private ComboBox<String> desp_campos_modificables;
    @FXML
    private ComboBox<String> desp_campos_key;
    @FXML
    private Label lbl_nombretabla; // Cambiar el valor de este label, debera tener un texto asi: ¿Cual campo desea
                                   // modificar?
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
        cambioLBL();
        if (conection != null) {
         choicebox_action();
        }
        llenarTiposDatos();
    }

    public void setConnection(JDBC connection) {
        conection = connection;
    }

    // ...

    @FXML
    private ComboBox<String> desp_tipo_dato;

    // ...

    private void llenarTiposDatos() {
        desp_tipo_dato.getItems().addAll(
            "INT", "TINYINT", "SMALLINT", "MEDIUMINT", "BIGINT", "DECIMAL", "NUMERIC",
            "FLOAT", "DOUBLE", "BIT", "DATE", "DATETIME", "TIMESTAMP", "TIME", "YEAR",
            "CHAR", "VARCHAR", "BINARY", "VARBINARY", "TINYBLOB", "BLOB", "MEDIUMBLOB",
            "LONGBLOB", "TINYTEXT", "TEXT", "MEDIUMTEXT", "LONGTEXT", "ENUM", "SET"
        );
        // Limitar el número de cosas visibles
        desp_tipo_dato.setVisibleRowCount(5);
    }
    @FXML
    public void choicebox_action() {
        // Limpia la ChoiceBox
        desp_campos_modificables.getItems().clear();
        // Crea el querie para la tabla anteriormente seleccionada
        String DESCRIBE_TABLE = "DESCRIBE " + tableSelected;
        // Ingresa a el choicebox los valores de FIELD de la tabla
        try {
            desp_campos_modificables.getItems()
                    .addAll(TablasController.getConection().getDatafromOneField(DESCRIBE_TABLE, "FIELD"));
                    desp_campos_modificables.setVisibleRowCount(5);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Revise la coneccion con la base de datos");
            alert.showAndWait();
        }
    }
    @FXML
    public void choiceboxkey_action() {
        // Limpia la ChoiceBox
        desp_campos_key.getItems().clear();
        //Añade los valores de key para una base de datos
        desp_campos_key.getItems().addAll("Primary Key", "Foreign Key", "Unique Key", "Candidate Key","Alternate Key","Composite key");
        desp_campos_key.setVisibleRowCount(5);
        
    }

    public void setTableSelected(String tableSelectedd) {
        tableSelected = tableSelectedd;
    }
    public void cambioLBL() {
        lbl_nombretabla.setText("Estas modificando = "+tableSelected);
    }

    public static JDBC getConection() {
        return conection;
    }

    @FXML
private void click_modificar(ActionEvent event) throws IOException, SQLException {
    String nombreColumna = desp_campos_modificables.getValue();
    String nuevoNombre = txt_nuevo_nombre.getText();
    String nuevoTipoDato = desp_tipo_dato.getValue().toString();

    if (nombreColumna != null && nuevoNombre != null && nuevoTipoDato != null) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Estás a punto de modificar la columna " + nombreColumna);
        alert.setContentText("¿Estás seguro de que deseas realizar los cambios?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String renameColumn = "ALTER TABLE " + tableSelected + " change " + nombreColumna + " " + nuevoNombre+ " " +nuevoTipoDato;
            conection.Statment(renameColumn);
            click_volver(event);
        }
    } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, selecciona una columna y un tipo de dato.");
        alert.showAndWait();
    }
}
//Cambiarle el nombre al metodo, no tiene sentido 
    @FXML
    private void click_campo(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/key.fxml"));
        loader.load();
        KeyController keyController= loader.getController();
        keyController.setConnection(conection);
        KeyController.setTableSelected(tableSelected); 
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/key.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Modificar tabla: "+conection.getBaseDatos());
        window.setScene(MostrarScene);
        window.show();
    }
    
//Igual
    @FXML
    private void click_atributo(ActionEvent event) throws IOException, SQLException {
        String atributoselec= desp_campos_modificables.getValue();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Estás a punto de agregar el atributo " + atributoselec);
        alert.setContentText("¿Estás seguro de que agregar el atributo " + atributoselec + "? Esta acción es irreversible.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            String addColumn = "ALTER TABLE " + tableSelected + " drop " + atributoselec;
            conection.Statment(addColumn);
            click_volver(event);
        }
    }
 //DEfinir que es este metodo no entiendo
    @FXML
    private void click_key(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/key.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Añadir Key");
        window.setScene(MostrarScene);
        window.show();
               
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Modificar tabla: "+conection.getBaseDatos());
        window.setScene(MostrarScene);
        window.show();
    }

}
