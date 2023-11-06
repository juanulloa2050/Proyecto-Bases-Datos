package proyecto_bases_datos;

import java.awt.HeadlessException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

import java.util.ArrayList;


import java.sql.*;

public class MenuController implements Initializable {
    public static JDBC conection;
    @FXML
    private ChoiceBox<String> desp_bases;
    @FXML
    private Button btn_acceder;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_exit;
    public AnchorPane raiz;
    private String GETDATABASES="SELECT schema_name FROM information_schema.schemata \n" +
            "WHERE schema_name Not LIKE 'information_schema'\n" +
            "and schema_name not LIKE 'mysql' and schema_name not like 'performance_schema';";

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
    }    

    public void setConnection(JDBC connection) {
        conection=connection;
    }
    public static JDBC getConection(){
        return conection;
    }
            
    public void choicebox_action() {  
        // Limpia la ChoiceBox   
        desp_bases.getItems().clear(); 
        // Agrega cada base de datos a la BOx
        desp_bases.getItems().addAll(MenuController.getConection().getDatafromOneField(GETDATABASES,"SCHEMA_NAME"));
    }
}
