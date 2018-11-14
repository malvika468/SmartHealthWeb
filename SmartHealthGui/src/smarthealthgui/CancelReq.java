package smarthealthgui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CancelReq
 */
@WebServlet("/CancelReq")
public class CancelReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelReq() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 PrintWriter out = response.getWriter();
		 String unamelogin=(String)request.getSession(false).getAttribute("uname");
		 String f=request.getParameter("friendname");
		 Date date=new Date();
		 Timestamp time=new Timestamp(date.getTime());
		 User u=new User();
		 if(f.length()==0)
		 out.println("name cannot be null");
		 else
		 {
		  int i= u.cancelRequest(unamelogin, f, time);
	      if(i>0)
	      out.println("cancelled successfully");
		 }
		
		 
		 
		
	}

}
