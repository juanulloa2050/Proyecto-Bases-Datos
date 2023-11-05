package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

public class InterfazController implements Initializable {
    JDBC connection = JDBC.getInstance();
    private Connection conn;

    @FXML
    private AnchorPane T;
    @FXML
    private TextField txt_usr;
    @FXML
    private TextField txt_puerto;
    @FXML
    private TextField txt_maquina;
    @FXML
    private PasswordField txt_clave;
    @FXML
    private Button btn_acceder;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void acceder_action() throws IOException {
        String usuario = txt_usr.getText();
        String puerto = txt_puerto.getText();
        String clave = txt_clave.getText();

        if (usuario.isEmpty() || puerto.isEmpty() || clave.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellene todos los campos.");
            alert.showAndWait();
        } else {
            String url = "jdbc:mysql://localhost:"+puerto;
            conn=connection.conectarBase(url, usuario, clave);

            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Stage stage = (Stage) btn_acceder.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }
}
