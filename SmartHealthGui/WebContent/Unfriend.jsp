<%-- 
    Document   : Unfriend
    Created on : Oct 25, 2016, 1:32:29 AM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page import="smarthealthgui.*" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
        <title>JSP Page</title>
    </head>
    <body bgcolor="SkyBlue">
        <%      
            //calling the unfriend fiunction on the entered user name and dispalying result of query
            System.out.println("here0: ");
            String uname=(String)(request.getSession(false).getAttribute("uname"));
            System.out.println("here: "+uname);
            String f=request.getParameter("friendname");
            System.out.println("herefriend1 :"+f);
            
            User u=new User();
	        Date date=new Date();
            Timestamp time=new Timestamp(date.getTime());
             int j= u.unfriend(uname, f,time);
             if(j==1)
             {	 
            request.setAttribute("result","successfully unfriended");
            request.setAttribute("uname",uname);
             }
            else if(j==0)
            {
           request.setAttribute("result","username cant be empty");	
            }
            RequestDispatcher rd=request.getRequestDispatcher("SeeFriends.jsp");  
                            
	       rd.forward(request, response);
            
            %>
            
    </body>
</html>
