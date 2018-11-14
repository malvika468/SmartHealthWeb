package smarthealthgui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
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
	//controller
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		  PrintWriter out = response.getWriter();
		  int j=-1;
		String unamelogin=(String)request.getSession(false).getAttribute("uname");
		//String f1=(String)request.getSession(false).getAttribute("forumId");
                Forum f=(Forum)request.getSession(false).getAttribute("forum");
                
		int fid=f.getForumId();
		System.out.println("in post servlet id : "+fid);
		String text=request.getParameter("post");
		Date date=new Date();
        Timestamp time=new Timestamp(date.getTime());
        String pic=request.getParameter("piclink");
        String web=request.getParameter("weblink");
        String video=request.getParameter("videolink");
        String result;
        
        if(text.length()==0)
        result="post cannot be empty"; 	
        else
        { 	
        //Forum forum=new Forum();
        // getting the forum object through application scope
		//calling forum method 
        j=f.createPost(unamelogin,time,fid,text,pic,web,video,f);
        if(j>0)
        result="posted succesfully"; 
        else
            result="error occurred during post";
        }
        HttpSession sess = request.getSession(); 
                    sess.setAttribute("forum", f);
                    sess.setAttribute("result",result);
                    RequestDispatcher rd=request.getRequestDispatcher("UserResult.jsp");  	        
                    rd.forward(request, response);
     
		
	}

}
