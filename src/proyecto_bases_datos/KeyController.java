/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    public String tableSelected;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        //JOptionPane.showMessageDialog(null, conection.getBaseDatos());
    }
    public void setTableSelected(String tableSelectedd) {
        tableSelected = tableSelectedd;
        JOptionPane.showMessageDialog(null, tableSelected);
    }

    @FXML
    private void click_volver(ActionEvent event) {
    }
    @FXML
    private void llenarTiposDatos() {
        desp_tipo_dato.getItems().addAll(
            "INT", "TINYINT", "SMALLINT", "MEDIUMINT", "BIGINT", "DECIMAL", "NUMERIC",
            "FLOAT", "DOUBLE", "BIT", "DATE", "DATETIME", "TIMESTAMP", "TIME", "YEAR",
            "CHAR", "VARCHAR", "BINARY", "VARBINARY", "TINYBLOB", "BLOB", "MEDIUMBLOB",
            "LONGBLOB", "TINYTEXT", "TEXT", "MEDIUMTEXT", "LONGTEXT", "ENUM", "SET"
        );}

    @FXML
public void click_crear() {
    String nombre = txt_nombre.getText();
    String tipoDato = desp_tipo_dato.getValue();
    boolean isPrimaryKey = CheckPrimaryKey.isSelected();
    boolean isForeignKey = CheckForeignKey.isSelected();
    String sql = " ";
    String key = "";
    if (!isPrimaryKey && !isForeignKey) {
        key = "PRIMARY KEY, FOREIGN KEY";
         sql = "ALTER TABLE " + tableSelected+
                 "ADD COLUMN " + nombre + " " + tipoDato;
    } else
    if (isPrimaryKey) {
        key = "PRIMARY KEY";
        sql = "ALTER TABLE " + tableSelected+
                 "ADD COLUMN " + nombre + " " + tipoDato + " " + key;
    } else if (isForeignKey) {
        key = "FOREIGN KEY";
        sql = "ALTER TABLE " + tableSelected+
                 "ADD COLUMN " + nombre + " " + tipoDato + " " + key;
    }
    conection.Statment(sql);
}

    
}
