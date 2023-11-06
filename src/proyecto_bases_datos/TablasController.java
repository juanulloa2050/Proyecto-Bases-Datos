package proyecto_bases_datos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class TablasController implements Initializable {

    @FXML
    private ChoiceBox<?> desp_tablas;
    @FXML
    private TableView<?> tablas;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_borar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_crear1;
    @FXML
    private Button btn_borar1;
    @FXML
    private Button btn_modificar_registro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void click_modificar(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Modificar_tabla.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }

    @FXML
    private void click_crear(ActionEvent event) {
    }

    @FXML
    private void click_borrar(ActionEvent event) {
    }

    @FXML
    private void click_modificar_registro(ActionEvent event) {
    }

    @FXML
    private void click_nuevo_dato(ActionEvent event) {
    }

    @FXML
    private void click_borrar_registro(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("Eliminar_registro.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.show();
    }
    
}
