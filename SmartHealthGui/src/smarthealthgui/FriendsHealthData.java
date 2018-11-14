
package smarthealthgui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FriendsHealthData extends HttpServlet {

   
   

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //PrintWriter out = response.getWriter();
            
		 String uname=(String)request.getSession(false).getAttribute("uname");
                 System.out.print("in servlet get friends : "+uname);
		 ArrayList<HashMap> res=null;
		 try{
                 User u=new User(uname);
                 res=u.getHealthData().getFriendsHealthData(uname);
                
                 }catch(Exception e){
                     System.out.println(e);
                     
		 }
                    HttpSession sess = request.getSession(); 
                    sess.setAttribute("uname", uname);
                    sess.setAttribute("resultF",res);
                    RequestDispatcher rd=request.getRequestDispatcher("LoginUser.jsp");  	        
                    rd.forward(request, response);
           
	}

    

}
