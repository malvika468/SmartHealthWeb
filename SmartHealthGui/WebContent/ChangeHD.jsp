<%-- 
    Document   : ChangeHD
    Created on : Nov 12, 2016, 11:53:01 AM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page import="smarthealthgui.*" %>
<%@ page import="java.sql.*" %>
        <title>JSP Page</title>
        
    </head>
    <body bgcolor="pink">
        <% 
        String uname1=(String)request.getSession(false).getAttribute("uname");
        System.out.println("un jsp: "+uname1);
        User u=new User(uname1);
        %>
        <center>
            <font color="maroon"><h1>Your Health data</h1></font>
            <br><br><br>
       <form action="ChangeHealthD" method=post>  
           <h3>Run</h3>
        &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="run" value=<%= u.getHealthData().getRun() %>>&nbspmeters<br><br><br>
        <h3>Calories-Burned</h3>
        <input type="text" name="cal" value=<%= u.getHealthData().getCalories_burned() %>>&nbspcal<br><br><br>
        <h3>Blood-Pressure</h3>
        &nbsp;&nbsp;<input type="text" name="bp" value="<%= u.getHealthData().getBloodPressure() %>">&nbspmmHg<br><br><br><br><br>
        <input type="submit" value="Submit">
        </center>
    </body>
</html>
