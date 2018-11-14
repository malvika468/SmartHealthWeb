package smarthealthgui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.core.ApplicationSessionCookieConfig;

/**
 * Servlet implementation class ForumServlet
 */
@WebServlet("/ForumServlet")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int fid=1007;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumServlet() {
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
		   String temp="NULL";
		   String t=request.getParameter("Topic");
		   String s=request.getParameter("Summary");
		   String u=request.getParameter("URL");
		   if(t.length()==0 || s.length()==0 || u.length()==0)
		    out.println("Please provide all the details");	   
		   else 
		   
		   {
		   Timestamp t1=null;
		   SQLcom comm=new SQLcom();
		   ResultSet res=null;
		   try
		   {
		   res=comm.retrieve("Select max(forumID) from forum;");
		   res.next();
		   fid=res.getInt(1);
		   }
		   catch(Exception e)
		   {
			e.printStackTrace();   
		   }
		  Date date=new Date();
		  Timestamp time=new Timestamp(date.getTime());
		  String unamelogin=(String)request.getSession(false).getAttribute("uname");
		  fid++; 
		  // creating a forum object
		  Forum forum=new Forum(fid,t,s,u,time,t1,unamelogin,temp,new ArrayList<Post>());      
		  int forumId=forum.getForumId();
		  String topic=forum.getTopic();
		  String summary=forum.getSummary();
		  String url=forum.getUrl();
		  Timestamp created=forum.getCreated();
		  Timestamp closed=forum.getClosed();
		  String modCreated=forum.getModCreated();	  
		  String modDeleted=forum.getModDeleted();
		  Moderator m=new Moderator();
		  
		 // moderator creating a forum
		  int k= m.createForum(forumId, topic, summary, url, created, closed, modDeleted, modCreated);
		  
		  // saving the forum object in application scope to be used in postservlet 
		  getServletContext().setAttribute(fid+"", forum);
		
		  if(k>0)
		  out.println("created successfully");	
		  else
		 out.println("some error");	  
		 }
		  		
		
	}

}
