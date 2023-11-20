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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyecto_bases_datos.managment.JDBC;

/**
 * FXML Controller class
 *
 * @author juanu
 */
public class Condiciones_busqueda_cruzadaController implements Initializable {
    public static JDBC conection;
    static String tablaSelected1;
    static String AcronimoTabla1;
    static String tablaSelected2;
    static String AcronimoTabla2;
    private ArrayList<ComboBox<String>> ComboBoxAtributosTabla1 =new ArrayList<>();
    private ArrayList<ComboBox<String>> ComboBoxAtributosTabla2 =new ArrayList<>();
    @FXML
    private ComboBox<String> desp_atributo_relacionTabla1;
    @FXML
    private ComboBox<String> desp_atributo_relacionTabla2;
    @FXML
    private ComboBox<String> desp_operador_relacion;
    private ComboBox<String> dep_atributo1;
    @FXML
    private ComboBox<String> desp_operador1;
    @FXML
    private ComboBox<String> desp_atributo2;
    @FXML
    private ComboBox<String> desp_operador2;
    @FXML
    private ComboBox<String> ANDOR2;
    @FXML
    private ComboBox<String> ANDOR1;
    @FXML
    private Button btn_continuar;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_masTabla1;
    @FXML
    private Button btn_menosTabla1;
    @FXML
    private Button btn_masTabla2;
    @FXML
    private Button btn_menosTabla2;
    @FXML
    private VBox vBoxAddAtributosTabla1;
    @FXML
    private VBox vBoxAddAtributosTabla2;
    @FXML
    private TextField valorCondicion1;
    @FXML
    private TextField valorCondicion2;
    private int contadorAtributosTabla1 = 1;
    private int contadorAtributosTabla2 = 1;
    String[] operadores = {"<", ">", "<=", ">=", "=", "<>", "LIKE", "NOT LIKE", "IS NULL", "IS NOT NULL"};
    @FXML
    private Label atributo_Tabla1;
    @FXML
    private Label atributo_Tabla2;
    @FXML
    private ComboBox<?> desp_atributo1;
    @FXML
    private Label campos_Tabla1;
    @FXML
    private CheckBox check_Condicion2;
    @FXML
    private CheckBox check_Condicion1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //hacer que si condicion1 no esta habilitado no se pueda habilitar condicion2
        check_Condicion1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (check_Condicion1.isSelected()) {
            desp_atributo1.setDisable(!newValue);
            desp_operador1.setDisable(!newValue);
            valorCondicion1.setDisable(!newValue);
            check_Condicion2.setDisable(false);
            }else if (!check_Condicion1.isSelected()) {
                check_Condicion2.setSelected(false);
            }

        });

        check_Condicion2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (check_Condicion1.isSelected()) {
                desp_atributo2.setDisable(!newValue);
                desp_operador2.setDisable(!newValue);
                valorCondicion2.setDisable(!newValue);
                ANDOR1.setDisable(false);
            } else {
                desp_atributo2.setDisable(true);
                desp_operador2.setDisable(true);
                valorCondicion2.setDisable(true);
                ANDOR1.setDisable(true);
        }});
        //Hacer que si check_Condicion2 esta seleccionado se habilite atributo2,operador2, y valor2
    }
    //Tabla 1
    @FXML
    private void click_masTabla1(ActionEvent event) {
        //creo el label y el choice box
        
        Label numeroAtributo = new Label("Atributo #" + contadorAtributosTabla1);
        ComboBox<String> nuevoAtributo = new ComboBox<>();
        nuevoAtributo.getItems().clear();
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected1+";","Field")){
            nuevoAtributo.getItems().addAll(AcronimoTabla1+"."+campo);
        }
        //Agregarlo a la lista local
        ComboBoxAtributosTabla1.add(nuevoAtributo);
        //Agregarlos al drame
        vBoxAddAtributosTabla1.getChildren().add(numeroAtributo);
        vBoxAddAtributosTabla1.getChildren().add(nuevoAtributo);

    // Incrementar el contador de atributos
    contadorAtributosTabla1++;
    }
    @FXML
    private void click_menusTabla1(ActionEvent event) {
        // Verificar si hay elementos para eliminar
        if (!vBoxAddAtributosTabla1.getChildren().isEmpty()) {
            // Eliminar el último elemento (Label o ComboBox)
            vBoxAddAtributosTabla1.getChildren().remove(vBoxAddAtributosTabla1.getChildren().size() - 1);
            //Eliminar el label
            vBoxAddAtributosTabla1.getChildren().remove(vBoxAddAtributosTabla1.getChildren().size() - 1);
            //ELiminarlo del array de choice box
            contadorAtributosTabla1--;
            ComboBoxAtributosTabla1.remove(contadorAtributosTabla1-1);
        } else {
            System.out.println("No hay elementos para eliminar.");
        }
    }
    
    //Tabla 2
    @FXML
    private void click_masTabla2(ActionEvent event) {
        //creo el label y el choice box
        Label numeroAtributo = new Label("Atributo #" + contadorAtributosTabla2);
        ComboBox<String> nuevoAtributo = new ComboBox<>();
        nuevoAtributo.getItems().clear();
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected2+";","Field")){
            nuevoAtributo.getItems().addAll(AcronimoTabla2+"."+campo);
        }
        //Agregarlo a la lista local
        ComboBoxAtributosTabla2.add(nuevoAtributo);
        //Agregarlos al drame
        vBoxAddAtributosTabla2.getChildren().add(numeroAtributo);
        vBoxAddAtributosTabla2.getChildren().add(nuevoAtributo);

    // Incrementar el contador de atributos
    contadorAtributosTabla2++;
    }
    @FXML
    private void click_menusTabla2(ActionEvent event) {
        // Verificar si hay elementos para eliminar
        if (!vBoxAddAtributosTabla2.getChildren().isEmpty()) {
            // Eliminar el último elemento (Label o ComboBox)
            vBoxAddAtributosTabla2.getChildren().remove(vBoxAddAtributosTabla2.getChildren().size() - 1);
            //Eliminar el label
            vBoxAddAtributosTabla2.getChildren().remove(vBoxAddAtributosTabla2.getChildren().size() - 1);
            //ELiminarlo del array de choice box
            contadorAtributosTabla2--;
            ComboBoxAtributosTabla2.remove(contadorAtributosTabla2-1);
        } else {
            System.out.println("No hay elementos para eliminar.");
        }
    }
    

    //Condiciones
    @FXML
    private void AndOr2(){
        ANDOR2.getItems().clear();
        ANDOR2.getItems().addAll("AND","OR");
        ANDOR2.setVisibleRowCount(5);
    }
    @FXML
    private void operador_condicion1(){
        desp_operador1.getItems().clear();
        desp_operador1.getItems().addAll(operadores);
        desp_operador1.setVisibleRowCount(5);
    }
    @FXML
    private void operador_condicion2(){
        desp_operador2.getItems().clear();
        desp_operador2.getItems().addAll(operadores);
        desp_operador2.setVisibleRowCount(5);
    }
    @FXML
    private void condicion1TablasCruzadas(){
        dep_atributo1.getItems().clear();
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected1+";","Field")){
            dep_atributo1.getItems().addAll(AcronimoTabla1+"."+campo);
            dep_atributo1.setVisibleRowCount(5);
        }
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected2+";","Field")){
            dep_atributo1.getItems().addAll(AcronimoTabla2+"."+campo);
            dep_atributo1.setVisibleRowCount(5);
        }
    }
    @FXML
    private void condicion2TablasCruzadas(){
        desp_atributo2.getItems().clear();
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected1+";","Field")){
            desp_atributo2.getItems().addAll(AcronimoTabla1+"."+campo);
            desp_atributo2.setVisibleRowCount(5);
        }
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected2+";","Field")){
            desp_atributo2.getItems().addAll(AcronimoTabla2+"."+campo);
            desp_atributo2.setVisibleRowCount(5);
        }
    }

    //RElacion
    @FXML
    private void operadorRelacion(){
        desp_operador_relacion.getItems().clear();
        // Agregar operadores al ComboBox desp_operador1
        desp_operador_relacion.getItems().addAll(operadores);
        desp_operador_relacion.setVisibleRowCount(5);
    }
    @FXML
    private void AndOr1(){
        ANDOR1.getItems().clear();
        ANDOR1.getItems().addAll("AND","OR");
        ANDOR1.setVisibleRowCount(5);
    }
    
        //Atributos
    @FXML
    private void atributoTabla1Relacion(){
        desp_atributo_relacionTabla1.getItems().clear();
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected1+";","Field")){
            desp_atributo_relacionTabla1.getItems().addAll(AcronimoTabla1+"."+campo);
            desp_atributo_relacionTabla1.setVisibleRowCount(5);
        }
    }
    @FXML
    private void atributoTabla2Relacion(){
        desp_atributo_relacionTabla2.getItems().clear();
        for (String campo : conection.getDatafromOneField("Describe "+tablaSelected2+";","Field")){
            desp_atributo_relacionTabla2.getItems().addAll(AcronimoTabla2+"."+campo);
            desp_atributo_relacionTabla2.setVisibleRowCount(5);
        }
    }
    
    //Busqueda
    public String queryUnaTabla(){
        StringBuilder query = new StringBuilder();
        query.append("Select");
        for (ComboBox<String> sel: ComboBoxAtributosTabla1){
            if (sel.getSelectionModel().getSelectedItem()==null){
                throw new NullPointerException();
            } else{
                query.append(" "+sel.getSelectionModel().getSelectedItem());
                if(!sel.getSelectionModel().getSelectedItem().equals(ComboBoxAtributosTabla1.get(ComboBoxAtributosTabla1.size()-1).getSelectionModel().getSelectedItem())){
                    query.append(",");
                }
            } 
        }
        query.append(",");
        for (ComboBox<String> sel: ComboBoxAtributosTabla2){
            if (sel.getSelectionModel().getSelectedItem()==null){
                throw new NullPointerException();
            } else{
                query.append(" "+sel.getSelectionModel().getSelectedItem());
                if(!sel.getSelectionModel().getSelectedItem().equals(ComboBoxAtributosTabla2.get(ComboBoxAtributosTabla2.size()-1).getSelectionModel().getSelectedItem())){
                    query.append(",");
                }
            } 
        }
        //Tablas seleccionadas
        query.append(" from "+tablaSelected1 + " " + AcronimoTabla1 +", "
        +tablaSelected2+" "+AcronimoTabla2 +" where ");
        //RElacion
        query.append(desp_atributo_relacionTabla1.getSelectionModel().getSelectedItem()
                    +" "+desp_operador_relacion.getSelectionModel().getSelectedItem()
                    +" "+desp_atributo_relacionTabla2.getSelectionModel().getSelectedItem()
                    +" "+ANDOR1.getSelectionModel().getSelectedItem()+" ");
        //Condiciones
        if (dep_atributo1.getSelectionModel().getSelectedItem() == null || 
            desp_operador1.getSelectionModel().getSelectedItem()==null ||
            valorCondicion1.getText()==null
            ){
                if (desp_atributo2.getSelectionModel().getSelectedItem() == null || 
                desp_operador2.getSelectionModel().getSelectedItem()==null ||
                valorCondicion2.getText()==null
                ){throw new NullPointerException("");}
                    
                query.append(desp_atributo2.getSelectionModel().getSelectedItem()
                +" "+desp_operador2.getSelectionModel().getSelectedItem()
                +" "+valorCondicion2.getText());
        } else{
            query.append(dep_atributo1.getSelectionModel().getSelectedItem()
            +" "+desp_operador1.getSelectionModel().getSelectedItem()
            +" "+valorCondicion1.getText());
            if (desp_atributo2.getSelectionModel().getSelectedItem() == null || 
                desp_operador2.getSelectionModel().getSelectedItem()==null ||
                valorCondicion2.getText()==null ||
                ANDOR2.getSelectionModel().getSelectedItem()==null
                ){query.append("");}
                else{
                    query.append(" "+ANDOR2.getSelectionModel().getSelectedItem()
                    +" "+desp_atributo2.getSelectionModel().getSelectedItem()
                    +" "+desp_operador2.getSelectionModel().getSelectedItem()
                    +" "+valorCondicion2.getText());
                }
        }
       // query.append(" limit 50"); // Limitar lineas de busquedas, valor que puede cambiar
        return query.toString();
    }
   

    //Metodos funcionales
    @FXML
    private void click_volver(ActionEvent event) throws IOException {
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Busquedas_dos_tablas.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(MostrarScene);
        window.setTitle("Busqueda dos tabla");
        window.show();
    }
    @FXML
    private void click_continuar(ActionEvent event) throws IOException {
        System.out.println(queryUnaTabla());
        Parent MostrarParent = FXMLLoader.load(getClass().getResource("/proyecto_bases_datos/FXML/Resultado_busquedas1.fxml"));
        Scene MostrarScene = new Scene(MostrarParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Resultado_busquedas1Controller rBusquedas1Controller = new Resultado_busquedas1Controller();
        rBusquedas1Controller.setConnection(conection);
        String querie=queryUnaTabla();
        rBusquedas1Controller.setQuerie(querie);
        window.setScene(MostrarScene);
        window.setTitle("Resultado Busqueda");
        window.show();
    }
    public void setConnection(JDBC connection) {
        conection = connection;
    }

    public static JDBC getConection() {
        return conection;
    }
    public void setTablaSelected(String tablaSelecteds1,String tablaSelecteds2 ) {
        tablaSelected1=tablaSelecteds1;
        tablaSelected2=tablaSelecteds2;
        AcronimoTabla1=tablaSelecteds1.substring(0, Math.min(tablaSelecteds1.length(), 4)); //Acomodar si el nombre de la tabla es mas pequeño de 4 de largo
        AcronimoTabla2=tablaSelecteds2.substring(0, Math.min(tablaSelecteds2.length(), 4));
        
    }
    
}
