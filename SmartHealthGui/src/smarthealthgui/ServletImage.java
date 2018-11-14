package smarthealthgui;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class ServletImage
 */
//@WebServlet("/ServletImage")
public class ServletImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		Blob image = null;
		Connection con = null;
		byte[ ] imgData = null ;
		Statement stmt = null;
		ResultSet rs = null;
	
		try {
				 
			    Class.forName("com.mysql.jdbc.Driver");
			    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthealthdb?autoReconnect=true&useSSL=false", "malvika468", "root@123");
			    stmt = con.createStatement();
                 //usernamelogin
			    
			    String uname=(String)(request.getSession(false).getAttribute("unamelogin"));
				System.out.println("after login"+uname);
			    rs = stmt.executeQuery("select Photo from imageUP  where  username='"+uname+"'");

			    if (rs.next()) {

			    image = rs.getBlob(1);

			    imgData = image.getBytes(1,(int)image.length());
			    
			   // byte[] encodeBase64 = Base64.
			   // String base64DataString = new String(encodeBase64 , "UTF-8");
			   // imgDataBase64=new String(Base64.getEncoder().encode(imgData));
			   // System.out.println(imgDataBase64);

			    } else {

			  

			    }

			    
		response.setContentType("image/png");

		OutputStream o = response.getOutputStream();

		o.write(imgData);

		o.flush();

		o.close();
		}

			        catch(Exception e){
			        //  e.printStackTrace();
			          
			          
			         
			    } 

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
