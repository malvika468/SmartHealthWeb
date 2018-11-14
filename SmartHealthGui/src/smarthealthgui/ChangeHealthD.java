
package smarthealthgui;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeHealthD extends HttpServlet{
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //PrintWriter out = response.getWriter();
            
		 String uname=(String)request.getSession(false).getAttribute("uname");
                 System.out.print("in servlet : "+uname);
		 String run=request.getParameter("run");
                 String cal=request.getParameter("cal");
                 String bp=request.getParameter("bp");
		 try{
                 User u=new User(uname);
                 u.getHealthData().setHealthData(run, cal, bp);
                 u.getHealthData().saveHealthData(uname);
                 
                //out.println("cancelled successfully");
                 }catch(Exception e){
                     System.out.println(e);
                     
		 }
                    HttpSession sess = request.getSession(); 
                    sess.setAttribute("uname", uname);
                    RequestDispatcher rd=request.getRequestDispatcher("LoginUser.jsp");  	        
                    rd.forward(request, response);
           
	}
}
