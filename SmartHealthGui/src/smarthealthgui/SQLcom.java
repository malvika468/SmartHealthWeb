package smarthealthgui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLcom {

	
	 public int update(String cmd)           //Using this function to INSERT ;UPDATE; DELETE to db
	    {
	        try{
	        Connection conn=SingletonConn.getConn();
	        Statement stmt=conn.createStatement();
	        return stmt.executeUpdate(cmd);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	        return 0;
	    }
	    
	    public ResultSet retrieve(String cmd) //Using this method to execute SELECT ie to fetch data from db given the command
	    {
	        try{
	        Connection conn=SingletonConn.getConn();
	        Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        return stmt.executeQuery(cmd);
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	        return null;
	    }
	    
}
