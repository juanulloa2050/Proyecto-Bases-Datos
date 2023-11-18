/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class KeyController implements Initializable {
    public static JDBC conection;
    @FXML
    private Button btn_volver;
    @FXML
    private ChoiceBox<String> desp_tipo_dato;
    @FXML
    private TextField txt_nombre;
    @FXML
    private Button btn_crear;
    @FXML
    private CheckBox CheckPrimaryKey;
    @FXML
    private CheckBox CheckForeignKey;
    public static String tableSelected;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarTiposDatos();
        CheckPrimaryKey.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                CheckForeignKey.setSelected(false);
            }
        });

        CheckForeignKey.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                CheckPrimaryKey.setSelected(false);
            }
        });

    }

    public void setConnection(JDBC connection) {
        conection = connection;
        // JOptionPane.showMessageDialog(null, conection.getBaseDatos());
    }

    public static void setTableSelected(String tableSelectedd) {
        tableSelected = tableSelectedd;
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Base de datos:" + conection.getBaseDatos());
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void llenarTiposDatos() {
        desp_tipo_dato.getItems().addAll(
                "INT", "TINYINT", "SMALLINT", "MEDIUMINT", "BIGINT", "DECIMAL", "NUMERIC",
                "FLOAT", "DOUBLE", "BIT", "DATE", "DATETIME", "TIMESTAMP", "TIME", "YEAR",
                "CHAR", "VARCHAR", "BINARY", "VARBINARY", "TINYBLOB", "BLOB", "MEDIUMBLOB",
                "LONGBLOB", "TINYTEXT", "TEXT", "MEDIUMTEXT", "LONGTEXT", "ENUM", "SET");
    }

    @FXML
    public void click_crear(ActionEvent event) throws SQLException, IOException {
        String nombre = txt_nombre.getText();
        String tipoDato = desp_tipo_dato.getValue();
        boolean isPrimaryKey = CheckPrimaryKey.isSelected();
        boolean isForeignKey = CheckForeignKey.isSelected();
        String sql = " ";
        String key = "";
        if (!isPrimaryKey && !isForeignKey) {
            key = "PRIMARY KEY, FOREIGN KEY";
            sql = "ALTER TABLE " + tableSelected +
                    " ADD COLUMN " + nombre + " " + tipoDato + ";";
        }
        if (isPrimaryKey) {
            key = "PRIMARY KEY";
            sql = "ALTER TABLE " + tableSelected +
                    " ADD COLUMN " + nombre + " " + tipoDato + " " + key;
        } else if (isForeignKey) {
            key = "FOREIGN KEY";
            String DESCRIBE_TABLE = "DESCRIBE " + tableSelected;
            ArrayList<String> atributos = new ArrayList<String>();
            ArrayList<String> nombresArrayList = conection.getDatafromOneField(DESCRIBE_TABLE, "Field");
            ArrayList<String> tiposArrayList = conection.getDatafromOneField(DESCRIBE_TABLE, "Type");
            
            for (int i = 0; i < nombresArrayList.size(); i++) {
               String nombreAtributo = nombresArrayList.get(i);
                String tipoAtributo = tiposArrayList.get(i);
                if (tipoAtributo.equalsIgnoreCase(tipoDato)) {
                    atributos.add(nombreAtributo + " (" + tipoAtributo + ")");
                }
            }
            String[] atributosArray = atributos.toArray(new String[0]);
             String atributoSeleccionado= "";
            if (atributos.size() > 0) {
                atributoSeleccionado = (String) JOptionPane.showInputDialog(null,
                        "Selecciona el atributo al que se va a referenciar la clave foránea:",
                        "Selecciona un atributo", JOptionPane.QUESTION_MESSAGE, null, atributosArray, atributosArray[0]);
                if (atributoSeleccionado != null) {
                    key = "FOREIGN KEY";
                    String nombreAtributoSeleccionado = atributoSeleccionado.substring(0, atributoSeleccionado.indexOf('(')).trim();
                    String createIndexSql = "CREATE INDEX idx_" + nombreAtributoSeleccionado + " ON " + tableSelected + "(" + nombreAtributoSeleccionado + ")";
                    conection.Statment(createIndexSql);
                    sql = "ALTER TABLE " + tableSelected +
                            " ADD COLUMN " + nombre + " " + tipoDato + ", " +
                            "ADD CONSTRAINT " + nombre +
                            " FOREIGN KEY (" + nombre + ")" +
                            " REFERENCES " + tableSelected + "(" + nombreAtributoSeleccionado + ")";
                    click_volver(event);
                }
            } else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Error");
                alert.setContentText("No hay ningún atributo compatible.");
                alert.showAndWait();
                return;
             }
        }
        try {
            conection.Statment(sql);
            click_volver(event);
        } catch (SQLException e) {
            if (e.getMessage().contains("primary key")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Error");
                alert.setContentText("Ya existe una clave primaria en la tabla.");
                alert.showAndWait();
            } else {
                throw e;
            }
        }

    }

}
