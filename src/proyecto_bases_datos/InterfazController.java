package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;


/** Este crea la interfaz donde se le ingresa lo del root y eso */
public class InterfazController implements Initializable {
    JDBC connection;
    

    @FXML
    private AnchorPane T;
    private Parent root;
    @FXML
    private TextField txt_usr;
    @FXML
    private TextField txt_puerto;
    @FXML
    private PasswordField txt_clave;
    @FXML
    private Button btn_acceder;
    @FXML
    private ChoiceBox<?> desp_maquina;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void acceder_action() throws IOException {
        String usuario = "root";//txt_usr.getText();
        String puerto = "3306";//txt_puerto.getText();
        String clave = "12345";//txt_clave.getText();

        if (usuario.isEmpty() || puerto.isEmpty() || clave.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellene todos los campos.");
            alert.showAndWait();
        } else {
            //Crea la conexion
            connection =new JDBC("mysql","world", usuario, clave, puerto);
            //Envia el objeto a la otra clase
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Menu.fxml"));
            root =loader.load();
            MenuController menuDataBases= loader.getController();
            menuDataBases.setConnection(connection);
            
            //CAmbio de frame
            root = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Menu.fxml"));
            Stage stage = (Stage) btn_acceder.getScene().getWindow();
            stage.setScene(new Scene(root)); 
            stage.setTitle("Menu");
            stage.setUserData(connection);
        }
    }
}
