/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthgui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AddComment")
public class AddComment extends HttpServlet {

	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        
        Forum f=(Forum)request.getSession(false).getAttribute("forum");
        String post1=(String)request.getParameter("post");
       // System.out.println("post time received : "+post);
        //if(post==null)
        //post=(String)request.getSession(false).getAttribute("post");
        String uname=(String)request.getSession(false).getAttribute("uname");
        String comment=request.getParameter("comment");
        String photo=request.getParameter("photo");
        String link=request.getParameter("link");
        String video=request.getParameter("video");
        System.out.println("comment entered servllet : "+comment+"  username: "+uname+photo+link+video);
        
        Date date=new Date();
        Timestamp time=new Timestamp(date.getTime());
        
        String str1="Select username from post where timecreated='"+post1+"';";
        ResultSet res=(new SQLcom()).retrieve(str1);
        String postUsername="";
        try{
        while(res.next())
            postUsername=res.getString(1);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        Comment comObj=new Comment(postUsername,Timestamp.valueOf(post1),uname,time,comment,photo,link,video);
        
       // post.getCommList().add(comObj);
        
        
        //for(Comment c:post.getCommList())
          //  System.out.println("see:"+c.getText());
        
        //int indexOfPost=f.getPostList().indexOf(post);
        //f.getPostList().remove(indexOfPost);
        //f.getPostList().add(indexOfPost, post);
        
        
        SQLcom comm=new SQLcom();
        String str="insert into comment values('"+postUsername+"','"+post1+"','"+uname+"','"+time+"','"+
              comment+"','"+photo+"','"+link+"','"+video+"');";
        int i=comm.update(str);
        System.out.println("i:"+i);
        HttpSession sess = request.getSession(); 
        //sess.setAttribute("forum", f);
        sess.setAttribute("uname",uname);
        sess.setAttribute("id",String.valueOf(f.getForumId()));
        
        RequestDispatcher rd=request.getRequestDispatcher("Forum.jsp");  	        
	        rd.forward(request, response);
        
    }

    
}
