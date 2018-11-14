package smarthealthgui;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUpload
 */
//@WebServlet("/FileUpload")
@MultipartConfig(maxFileSize = 16177215)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private static Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private Connection getConnection() {
        
   	 try {
   		 
   		    Class.forName("com.mysql.jdbc.Driver");
   		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarthealthdb?autoReconnect=true&useSSL=false", "malvika468", "root@123");	
   		             }
   		        catch(Exception e){
   		          e.printStackTrace();
   		    } 
   	 return conn;
   }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//String uname=request.getParameter("username");
		//System.out.println(uname);
		 String uname=(String)(request.getSession(false).getAttribute("uname"));
		 System.out.println(uname);
		 System.out.println("hello");
		 InputStream inputStream = null; // input stream of the upload file
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
           
            System.out.println(filePart.getContentType());
           

            //obtains input stream of the upload file
            //the InputStream will point to a stream that contains
            //the contents of the file
            inputStream = filePart.getInputStream();
        }

        Connection conn = null; // connection to the database
        String message = null; // message will be
		
		
        try {
            // connects to the database
        	
            conn = getConnection();
            //System.out.println("mlvika"+conn);
            // constructs SQL statement
            String sql = "INSERT INTO ImageUP (photo,username) values (?,?)";
            //Using a PreparedStatement to save the file
            PreparedStatement pstmtSave = conn.prepareStatement(sql);
            

            if (inputStream != null) {
                //files are treated as BLOB objects in database
                //here we're letting the JDBC driver
                //create a blob object based on the
                //input stream that contains the data of the file
                pstmtSave.setBlob(1, inputStream);
                pstmtSave.setString(2, uname);
            }
            //sends the statement to the database server
            int row = pstmtSave.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        }
		
            catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
	
		
		
                request.setAttribute("message", message);

                // forwards to the message page
                getServletContext().getRequestDispatcher("/Message.jsp")
                    .include(request, response);
		
		
			
		
		
	}


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
