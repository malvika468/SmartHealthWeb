<%-- 
    Document   : SeeFriendReq
    Created on : Oct 25, 2016, 3:22:05 AM
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
        <h3>Friend Request from users</h3>
        <br>
        <br>
        <% 
            String uname=(String)(request.getSession(false).getAttribute("uname")); //obtains username from the previous jsp
            String result1=(String)request.getAttribute("result");  //obtains result from RequestRes.jsp if Accept/Reject button is pressed by user
            request.setAttribute("result", null);  
            User u=new User();
            ResultSet res= u.seeRequest(uname);     //reads friend request sent to user from the db and prints them and asks if want to respons to them
            while(res.next()){
             out.println(res.getString(1)+"<br><br>");
                    }
            %>
            
            <br><br><br><br>

<h3>Would you like to respond to friend requests</h3>

<br>Enter the user name to confirm/reject request : 

<form action="RequestRes.jsp" >
<input type="text" name="userReq"><br><br>
 <input type="submit" value="Accept" name="acceptF">
   <input type="submit" value="Reject" name="rejectF">
</form>
<br>
<form action="LoginUser.jsp" >
        <input type="submit" value="Back" />
    </form>
<br>
<%
    if(result1!=null)       //prints the result of accepting/rejecting requests obtained by RequestRes.jsp
            out.println(result1);
        
    %>
   
    </body>
</html>
