package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    JDBC conexion = JDBC.getInstance();
    Connection conn = null;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public void choicebox_action() {
        try {
            // Obtén la conexión desde la instancia de JDBC
            conn = conexion.getConexion();

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getCatalogs();

            // Limpia la ChoiceBox
            desp_bases.getItems().clear();

            // Agrega cada base de datos a la ChoiceBox
            while (rs.next()) {
                desp_bases.getItems().add(rs.getString("TABLE_CAT"));
            }

            // Cierra el ResultSet
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void click_acceder(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_eliminar(ActionEvent event) {
    }

    @FXML
    private void click_crear(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Nueva_baseD.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Interfaz.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_exit(ActionEvent event) {
    }
}
