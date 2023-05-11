import java.sql.*;

public class Conexion {
    static final String user = "root";
    static final String password = "leajim01";
    static final String url = "jdbc:mysql://127.0.0.1:3306/picture";

    private Connection con;
    private Statement stm;
    private ResultSet rs;

    public Conexion(){
        try{
            System.out.println("Connecting to database...");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Conexion c1 = new Conexion();
    }
    public Connection getCon() {
        return con;
    }

    public ResultSet getRs() {
        return rs;
    }

    public Statement getStm() {
        return stm;
    }


}
