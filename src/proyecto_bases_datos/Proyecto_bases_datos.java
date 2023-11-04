
package proyecto_bases_datos;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Proyecto_bases_datos extends Application {
     @Override
    public void start(Stage ventana) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tablas.fxml")); //Arma la ventana con el XML
        Scene scene = new Scene(root);
        ventana.setScene(scene);
        ventana.setTitle("inicio");
        ventana.setResizable(false);  // No se puede cambiar el tamaño de la ventana
        //ventana.setOnCloseRequest(event -> {event.consume();});
        ventana.show();   //Muestra la ventana}

    }

    public static void main(String[] args) {// TODO code application logic here
        launch(args);
    }
}
   
