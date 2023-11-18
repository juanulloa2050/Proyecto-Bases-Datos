/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyecto_bases_datos.Controllers;

import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class Crear_tablaController implements Initializable {
    public static JDBC conection;
    private ArrayList<ChoiceBox<String>> choiceBoxAtributos = new ArrayList<>();
    @FXML
    private TextField txt_nombre;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_volver;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBoxAddAtributos;
    @FXML
    private Button btn_mas1;
    @FXML
    private Button btn_menus1;
    private int contadorAtributos = 1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void click_crear(ActionEvent event) throws SQLException, IOException {
        System.out.println(getAtributos());
        String nombreTabla=txt_nombre.getText();
        conection.Statment("CREATE TABLE " + nombreTabla + " ("+ getAtributos() +")");
        click_volver(event);
    }
    public String getAtributos() {
        String atributos = "";
        for (int i = 0; i < choiceBoxAtributos.size(); i++) {
            atributos += ((TextField) vBoxAddAtributos.getChildren().get(i * 3 + 1)).getText()+ " " +  choiceBoxAtributos.get(i).getValue() + ", ";
        }
        return atributos.substring(0, atributos.length() - 2);
    }

    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Tablas");
        window.show();
    }
     @FXML
    private void click_mas1(ActionEvent event) {
        // creo el label y el choice box
        Label numeroAtributo = new Label("Atributo #" + contadorAtributos);
        ChoiceBox<String> nuevoAtributo = new ChoiceBox<>();
        // Crear el TextField y agregarlo al VBox
        TextField userInput = new TextField();
        nuevoAtributo.getItems().clear();
        nuevoAtributo.getItems().addAll(
            "INT", "TINYINT", "SMALLINT", "MEDIUMINT", "BIGINT", "DECIMAL", "NUMERIC",
            "FLOAT", "DOUBLE", "BIT", "DATE", "DATETIME", "TIMESTAMP", "TIME", "YEAR",
            "CHAR", "VARCHAR", "BINARY", "VARBINARY", "TINYBLOB", "BLOB", "MEDIUMBLOB",
            "LONGBLOB", "TINYTEXT", "TEXT", "MEDIUMTEXT", "LONGTEXT", "ENUM", "SET"
        );
        // Agregarlo a la lista local
        choiceBoxAtributos.add(nuevoAtributo);
        // Agregarlos al frame
        vBoxAddAtributos.getChildren().add(numeroAtributo);
        vBoxAddAtributos.getChildren().add(userInput);
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
    
}
