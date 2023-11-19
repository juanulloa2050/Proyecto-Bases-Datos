package proyecto_bases_datos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;
import java.util.Optional;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

//Este es el menu de las bases de datos
public class MenuController implements Initializable {
    public static JDBC conection;
    @FXML
    private ComboBox<String> desp_bases; // Cambiado de ChoiceBox a ComboBox
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
        if (conection != null) {
           choicebox_action();
        }
        
    }    

    public void setConnection(JDBC connection) {
        conection=connection;
    }
    public static JDBC getConection(){
        return conection;
    }
            
    @FXML
    public void choicebox_action() {  
        // Limpia la ChoiceBox   
        desp_bases.getItems().clear(); 
        // Agrega cada base de datos a la BO
        
        try{
            desp_bases.getItems().addAll(conection.getDatafromOneField(GETDATABASES,"SCHEMA_NAME"));
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Conection");
            alert.setHeaderText(null);
            alert.setContentText("Revise la coneccion con la base de datos");
            alert.showAndWait();
        } 
         
    }

    @FXML
    private void click_acceder(ActionEvent event) throws IOException {
        //Cambiar la base de datos seleccionada
        conection.setBaseDatos(desp_bases.getSelectionModel().getSelectedItem());
        //Envio de datos
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Tablas.fxml"));
        loader.load();
        TablasController tablasController= loader.getController();
        tablasController.setConnection(conection);
        if (desp_bases.getSelectionModel().getSelectedItem()==null) {
            JOptionPane.showMessageDialog(null, "Seleccione una base de datos");
        }else{
TablasController.setDataBaseSelected(desp_bases.getSelectionModel().getSelectedItem());
        //Cambio de slide.
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Menu Tablas, esta en la base de datos: "+conection.getBaseDatos());
        window.show();
        }
        
    }

    
@FXML
private void click_eliminar(ActionEvent event)  {
    if (desp_bases.getValue()!=null) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmación");
    alert.setHeaderText("Estás a punto de eliminar "+desp_bases.getValue());
    alert.setContentText("¿Estás seguro de que quieres continuar? Esta acción es irreversible.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        String DROPDATABASE="Drop database "+desp_bases.getSelectionModel().getSelectedItem();
        try {
            conection.Statment(DROPDATABASE);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        desp_bases.getItems().clear();        
    } 
    }else{  Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setContentText("No estas seleccionando ninguna base de datos");

    }
    
}

    @FXML
    private void click_crear(ActionEvent event) throws IOException {
        //Envia el objeto a la otra clase
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Nueva_baseD.fxml"));
        loader.load();
        Nueva_baseDController newDataBase= loader.getController();
        newDataBase.setConnection(conection);
        //Cambio de pesataña
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Nueva_baseD.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Nueva base de datos");
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Interfaz.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Acceso");
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_exit(ActionEvent event) {
        //cerrar el programa
        System.exit(0);
    }
}
