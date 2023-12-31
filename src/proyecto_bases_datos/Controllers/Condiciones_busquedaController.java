/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Condiciones_busquedaController implements Initializable {
    public static JDBC conection;
    static String tablaSelected;
    private ArrayList<ChoiceBox<String>> choiceBoxAtributos = new ArrayList<>();
    @FXML
    private ChoiceBox<String> dep_atributo1;
    @FXML
    private ChoiceBox<String> desp_operador1;
    @FXML
    private ChoiceBox<String> desp_atributo2;
    @FXML
    private ChoiceBox<String> desp_operador2;
    @FXML
    private Button btn_continuar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_mas1;
    @FXML
    private Button btn_menus1;
    @FXML
    private VBox vBoxAddAtributos;
    @FXML
    private TextField valor1;
    @FXML
    private TextField valor2;
    @FXML
    private ChoiceBox<String> operadorLogico;
    private int contadorAtributos = 1;
    Resultado_busquedas1Controller rBusquedas1Controller = new Resultado_busquedas1Controller();
    String[] operadores = { "<", ">", "<=", ">=", "=", "<>", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL" };
    @FXML
    private CheckBox primera_Condición;
    @FXML
    private CheckBox segunda_Condicion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (conection != null) {
            operadorLogico();
            atributo1();
            atributo2();
        }
        primera_Condición.selectedProperty().addListener((observable, oldValue, newValue) -> {
            desp_atributo2.setDisable(!newValue);
            desp_operador2.setDisable(!newValue);
            valor2.setDisable(!newValue);
            segunda_Condicion.setDisable(false);
        });

        segunda_Condicion.selectedProperty().addListener((observable, oldValue, newValue) -> {
            dep_atributo1.setDisable(!newValue);
            desp_operador1.setDisable(!newValue);
            valor1.setDisable(!newValue);
             operadorLogico.setDisable(false);
        });
    }

    public String esNoNumerico(String valor) {
        try {
            Integer.parseInt(valor);
        } catch (NumberFormatException e1) {
            try {
                Float.parseFloat(valor);
            } catch (NumberFormatException e2) {
                String devolucion = '"' + valor + '"';
                return devolucion;
            }
        }
        return valor; // Return a default value if the value is numeric
    }
    public String queryUnaTabla() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        for (ChoiceBox<String> sel : choiceBoxAtributos) {
            if (sel.getSelectionModel().getSelectedItem() == null) {
                showAlert("Los campos están vacíos. Por favor, complétalos.");
                return null;
            } else {
                query.append(sel.getSelectionModel().getSelectedItem());
                if (!sel.getSelectionModel().getSelectedItem().equals(
                        choiceBoxAtributos.get(choiceBoxAtributos.size() - 1).getSelectionModel().getSelectedItem())) {
                    query.append(",");
                }
            }
        }
        query.append(" FROM " + tablaSelected);
        if (primera_Condición.isSelected()) {
            if (desp_atributo2.getSelectionModel().getSelectedItem() == null ||
                    desp_operador2.getSelectionModel().getSelectedItem() == null ||
                    esNoNumerico(valor2.getText()) == null) {
                showAlert("Los campos están vacíos. Por favor, complétalos.");
                return null;
            } else {
                query.append(" WHERE " + desp_atributo2.getSelectionModel().getSelectedItem()
                        + " " + desp_operador2.getSelectionModel().getSelectedItem()
                        + " " + esNoNumerico(valor2.getText()));
            }
        }
        if (segunda_Condicion.isSelected()) {
            if (dep_atributo1.getSelectionModel().getSelectedItem() == null ||
                    desp_operador1.getSelectionModel().getSelectedItem() == null ||
                    esNoNumerico(valor1.getText()) == null) {
                showAlert("Los campos están vacíos. Por favor, complétalos.");
                return null;
            } else {
                if (primera_Condición.isSelected()) {
                    query.append(" AND ");
                } else {
                    query.append(" WHERE ");
                }
                query.append(dep_atributo1.getSelectionModel().getSelectedItem()
                        + " " + desp_operador1.getSelectionModel().getSelectedItem()
                        + " " + esNoNumerico(valor1.getText()));
            }
        }
        return query.toString();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void click_continuar(ActionEvent event) throws IOException {
        System.out.println(queryUnaTabla());
         // llenar esto con try cath para los erroes que lance
        // , como si la busqueda quedo mal en sql, el nullpointer, y mas cosas que
        // pueden salir.
        try {
            // Cambio de slide
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_bases_datos/FXML/Resultado_busquedas1.fxml"));
            Parent MostrarParent = loader.load();
            Scene MostrarScene = new Scene(MostrarParent);
            Resultado_busquedas1Controller rBusquedas1Controller = loader.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            rBusquedas1Controller.setConnection(conection);
            rBusquedas1Controller.setQuerie(queryUnaTabla());
            rBusquedas1Controller.usarInformacion();
            rBusquedas1Controller.setNombreTabla(tablaSelected);
            window.setScene(MostrarScene);
            window.setTitle("Resultado Busqueda");
            window.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Revise los espacios vacios, vuelva a intentarlo");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error con la base base de datos");
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Busqueda_una_tabla.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(MostrarScene);
        window.setTitle("Busqueda dos tabla");
        window.show();
    }

    @FXML
    private void click_mas1(ActionEvent event) {
        // creo el label y el choice box
        Label numeroAtributo = new Label("Atributo #" + contadorAtributos);
        ChoiceBox<String> nuevoAtributo = new ChoiceBox<>();
        nuevoAtributo.getItems().clear();
        nuevoAtributo.getItems().addAll("*");
        nuevoAtributo.getItems().addAll(conection.getDatafromOneField("Describe " + tablaSelected + ";", "Field"));
        // Agregarlo a la lista local
        choiceBoxAtributos.add(nuevoAtributo);
        // Agregarlos al drame
        vBoxAddAtributos.getChildren().add(numeroAtributo);
        vBoxAddAtributos.getChildren().add(nuevoAtributo);

        // Incrementar el contador de atributos
        contadorAtributos++;
    }

    @FXML
    private void click_menus1(ActionEvent event) {
        // Verificar si hay elementos para eliminar
        if (!vBoxAddAtributos.getChildren().isEmpty()) {
            // Eliminar el último elemento (Label o ChoiceBox)
            vBoxAddAtributos.getChildren().remove(vBoxAddAtributos.getChildren().size() - 1);
            // Eliminar el label
            vBoxAddAtributos.getChildren().remove(vBoxAddAtributos.getChildren().size() - 1);
            // ELiminarlo del array de choice box
            contadorAtributos--;
            choiceBoxAtributos.remove(contadorAtributos - 1);
        } else {
            System.out.println("No hay elementos para eliminar.");
        }
    }

    public void setConnection(JDBC connection) {
        conection = connection;
    }

    public static JDBC getConection() {
        return conection;
    }

    public void setTablaSelected(String tablaSelecteds) {
        tablaSelected = tablaSelecteds;
    }

    // Operadores
    @FXML
    private void operadore2() {
        desp_operador2.getItems().clear();
        // Agregar operadores al ChoiceBox desp_operador2
        desp_operador2.getItems().addAll(operadores);
    }

    @FXML
    private void operadore1() {
        desp_operador1.getItems().clear();
        // Agregar operadores al ChoiceBox desp_operador1
        desp_operador1.getItems().addAll(operadores);
    }

    @FXML
    private void operadorLogico() {
        operadorLogico.getItems().clear();
        operadorLogico.getItems().addAll("AND", "OR");
    }

    // Atributos
    @FXML
    private void atributo1() {
        dep_atributo1.getItems().clear();
        dep_atributo1.getItems().addAll(conection.getDatafromOneField("Describe " + tablaSelected + ";", "Field"));

    }

    @FXML
    private void atributo2() {
        desp_atributo2.getItems().clear();
        desp_atributo2.getItems().addAll(conection.getDatafromOneField("Describe " + tablaSelected + ";", "Field"));
    }

}
