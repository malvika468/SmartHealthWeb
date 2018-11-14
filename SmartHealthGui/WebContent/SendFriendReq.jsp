<%-- 
    Document   : SendFriendReq
    Created on : Oct 25, 2016, 2:51:28 AM
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
    <body  bgcolor="SkyBlue">
         <% 
            String uname=(String)(request.getSession(false).getAttribute("uname"));//gets username
            String f=request.getParameter("requestedPerson");           //gets requested username
            User u=new User();
	    Date date=new Date();
            Timestamp time=new Timestamp(date.getTime());
            
            int i= u.sendRequest(uname, f,time);            //sends request and obtains result and displays corresponding result
            if(i==1)
            request.setAttribute("result"," Friend Request Sent !! ");
            else if(i==2)
            request.setAttribute("result", "invalid user");
            else if(i==3)
            	request.setAttribute("result", "cannot send friend request to admin or mod");
            else if(i==4)
            	request.setAttribute("result", "cannot send friend request to yourself");
            else if(i==5)
             request.setAttribute("result", "username cannot be null"); 
            else if(i==6)
            request.setAttribute("result", "You have already send request to this user");
             request.setAttribute("uname", uname);
            RequestDispatcher rd=request.getRequestDispatcher("LoginUser.jsp");  
             rd.forward(request, response);
             
        %>
            
    </body>
</html>
