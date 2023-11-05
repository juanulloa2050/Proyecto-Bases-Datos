package proyecto_bases_datos;
import java.sql.*;

public class JDBC {
    // definir las variables url, usuario y contrasena que vienen desde la clase
    // usuario
    String usuario;
    String clave;
    String url;
    String baseDatos;
    Connection conexion;

    // metodos
    public void conectarBase(String url, String usuario, String clave) {
        // do the conection with the sql database
        try {
            // Paso 1: Registrar el driver JDBC.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Paso 2: Abrir una conexion
            System.out.println("Conectando a la base de datos...");
            conexion = DriverManager.getConnection(url, usuario, clave);
            // Paso 3: Obtener los nombres de todas las bases de datos
            DatabaseMetaData metaDatos = conexion.getMetaData();
            ResultSet basesDeDatos = metaDatos.getCatalogs();
            while (basesDeDatos.next()) {
                String nombreBaseDeDatos = basesDeDatos.getString(1);
                System.out.println("Base de datos: " + nombreBaseDeDatos);

                // Paso 4: Obtener los nombres de todas las tablas en cada base de datos.
                ResultSet tablas = metaDatos.getTables(nombreBaseDeDatos, null, null, new String[] { "TABLE" });

                while (tablas.next()) {
                    String nombreTabla = tablas.getString("TABLE_NAME");
                    System.out.println("\tTabla: " + nombreTabla);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
