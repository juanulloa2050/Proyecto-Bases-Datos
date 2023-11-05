package proyecto_bases_datos.managment;
import java.sql.*;

public class JDBC {
    // definir las variables url, usuario y contrasena que vienen desde la clase
    // usuario
    private static JDBC instance = null;
    private Connection conexion = null;
    String usuario;
    String clave;
    String url;
    String baseDatos;
    private JDBC() {
        // Constructor privado para prevenir la creación de instancias directamente
    }
    public static JDBC getInstance() {
        if (instance == null) {
            instance = new JDBC();
        }
        return instance;
    }
    // metodos
    public Connection conectarBase(String url, String usuario, String clave) {
        // do the conection with the sql database
        try {
            // Paso 1: Registrar el driver JDBC.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Paso 2: Abrir una conexion
            System.out.println("Conectando a la base de datos...");
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
    public Connection getConexion() {
        return conexion;
    }
    public void ingresarBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
        this.url = "jdbc:mysql://localhost:3306/" + baseDatos;
        try {
            this.conexion = DriverManager.getConnection(this.url, this.usuario, this.clave);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerInformacionBaseDatos(String sql) {
        try {
            Statement st = conexion.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editarBase() {
        // do the editing of the sql database
    }
}
