package smarthealthgui;

import java.sql.Connection;
import java.sql.DriverManager;



public class SingletonConn {

	private static Connection conn;
    private SingletonConn(){
        try {
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthealthdb", "malvika468", "root@123");	
             }
        catch(Exception e){
            System.out.println(e);
                }
    }
    public static Connection getConn(){
        if(conn==null)
            new SingletonConn();
        return conn;
         }
}
