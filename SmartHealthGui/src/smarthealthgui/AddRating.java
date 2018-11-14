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

/**
 *
 * @author lenovo
 */

@WebServlet("/AddRating")
public class AddRating extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        
        Forum f=(Forum)request.getSession(false).getAttribute("forum");
        String post1=(String)request.getParameter("post");
        String uname=(String)request.getSession(false).getAttribute("uname");
        String rating=request.getParameter("rating");
         System.out.println("rating entered servllet : "+rating);
        
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
        
        Rating rateObj=new Rating(postUsername,Timestamp.valueOf(post1),uname,Integer.parseInt(rating));
       
        SQLcom comm=new SQLcom();
        String str="insert into rating values('"+postUsername+"','"+post1+"','"+uname+"',"+Integer.parseInt(rating)+");";
        int i=comm.update(str);
        System.out.println("i:"+i);
        String result=null;
        if(i==1)
            result="Thanks for rating the post";
        else
            result="You have already rated the post!";
        HttpSession sess = request.getSession(); 
        sess.setAttribute("uname",uname);
        sess.setAttribute("id",String.valueOf(f.getForumId()));
        
        RequestDispatcher rd=request.getRequestDispatcher("Forum.jsp");  	        
	        rd.forward(request, response);
        
    }

    
}
