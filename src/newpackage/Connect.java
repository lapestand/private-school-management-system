package newpackage;

import java.sql.*;

/**
 *
 * @author İzzet Yılmaz
 */


public class Connect {
    
    private String username = "root";
    private String password = "";
    
    private String db_name = "dbmsproject";
    private String host = "localhost";
    private int port = 3306;
    private Connection con = null;
    
    public Connection ConnectDB(){
        
        //jdbc:mysql://localhost:3306/dbmsproject
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver can't found!");
        }
        
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            return con;
        } catch (SQLException ex) {
            System.out.println("Connection failed!");
            System.out.println(ex);
            return con;
        }
    }
    
    public Connection DisconnectDB(Connection c){
        if (c!=null) {
            try {
                c.close();
                c=null;
                System.out.println("Connection has closed. = "+c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    Connection DisconnectDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
