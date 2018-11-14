package smarthealthgui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ServletClass
 */
@WebServlet("/ServletClass")
public class ServletClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	 String res="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	     int status=1;     
	    String uname=request.getParameter("usernamelogin");  
	    String pass=request.getParameter("password");
	    System.out.println("hello");
	    System.out.println(uname);
	    HttpSession sess = request.getSession(); 
	    sess.setAttribute("uname", uname);
	    
	    
	    
	    String sql="select status from user where username='"+uname+"'";
		  try{
		   SQLcom comm1=new SQLcom();
		   ResultSet res1=comm1.retrieve(sql);
	       while(res1.next())
	       {
	    	status=res1.getInt(1);   
	       }
		  }
		  catch(Exception e){
			  
		  }
	    
		  if(status==0)
		    	
			    out.println("Sorry your account has been closed. Please sign up again");
			    
			    else  if(uname.length()==0 || pass.length()==0)
			    	
			    out.println("this field cannot be empty");
		  
		      
			   
			   
			    else if(!(check(uname,pass)))
			    {  	
	     
	        RequestDispatcher rd=request.getRequestDispatcher("Error.jsp");  	        
	        rd.forward(request, response);
	        
	    }
	    else 
	    {
	    	 try
	            {
	    		 SQLcom comm=new SQLcom(); 
	          //  String  sql3="select user_type from usertype where username='"+uname+"';"; 
	             String sql3="SELECT description from usertype  inner join user on user.usertypeid = usertype.usertypeid  where user.username='"+uname+"'";
                                                           
	            ResultSet rs=comm.retrieve(sql3);
	          
	    	    while(rs.next()){
	    	    
	    	    res=rs.getString(1);
	    	    }
	    	    if(res.equalsIgnoreCase("new") || res.equalsIgnoreCase("old")|| res.equalsIgnoreCase("middle"))
	    	    {
	    	            RequestDispatcher rd=request.getRequestDispatcher("LoginUser.jsp");  	        
	                    rd.forward(request, response);
	    	    }
	    	    else if(res.equalsIgnoreCase("mod"))
	    	    {
	    	    
	    	    	  RequestDispatcher rd=request.getRequestDispatcher("LoginMod.jsp");  	        
	                    rd.forward(request, response);
	    	    }
	    	    else if(res.equalsIgnoreCase("admin"))
	    	    {
	    	    	
	    	    	  RequestDispatcher rd=request.getRequestDispatcher("LoginAdmin.jsp");  	        
	                    rd.forward(request, response);
	    	    }
	        
	    }
	    	 
	  catch(Exception e)
	    	 {
		  
	    	 }
	}
	
	}
	 public static boolean check(String uname, String pass) //done
	  {

	 boolean r=false;
	
	 
	 String str="Select password from user where username='"+uname+"' and status='"+1+"';";
	 try
	 {
	     //user must be active user not the one with removed account
	 SQLcom comm=new SQLcom();
	 ResultSet res=comm.retrieve(str);
	 if(res.next())
	        { 
	            String passwrd=res.getString(1);
	            if(passwrd.equals(pass))r=true;
	        }   
	 }
	 catch(Exception e)
	 {
	     System.out.println(e);
	 }
	 return r;
	}


}
