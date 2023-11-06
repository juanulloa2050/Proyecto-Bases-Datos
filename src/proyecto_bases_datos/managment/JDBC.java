package proyecto_bases_datos.managment;
import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    // definir las variables url, usuario y contrasena que vienen desde la clase
    // usuario
    String baseDatos;
    String usuario;
    String password;
    String motor;
    String puerto;
    Connection conn;
    Statement st;
    ResultSet rs;

    public JDBC( String motor,String baseDatos,String usuario, String password, String puerto) {
        String url= "jdbc:"+motor+"://localhost:"+puerto+"/"+baseDatos;
        this.motor=motor;
        this.puerto=puerto;
        this.baseDatos=baseDatos;
        this.usuario = usuario;
        this.password = password;
        try{
            this.conn = DriverManager.getConnection(url, this.usuario, this.password);
        }catch (SQLException e){
            e.printStackTrace();            
        }
    }

    public ArrayList<String> getDatafromOneField(String sql,String field){
        ArrayList<String> Data=new ArrayList<>();
        try{
            Statement st = conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                Data.add(rs.getString(field));
            }
            st.close();
            return Data;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Data;
    }
    public void Statment(String query){
        try {
            Statement st = conn.createStatement();
            System.out.println("Peticion: "+st.executeUpdate(query));
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Getters and setters
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
        String url="jdbc:"+motor+"://localhost:"+puerto+"/"+baseDatos;
        try{
            this.conn = DriverManager.getConnection(url, this.usuario, this.password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet getRs(String sql) {
        try{
            Statement st = conn.createStatement();
            return st.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getBaseDatos() {
        return baseDatos;
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

}
