<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="smarthealthgui.*" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<title>Insert title here</title>
</head>
<body bgcolor="SkyBlue">

    <h2>Friend list </h2>
    <br>
   <% 
            SQLcom comm=new SQLcom();
            User u1=new User();
            String result=(String)request.getAttribute("result");
            request.setAttribute("result",null);
            String uname=(String)(request.getSession(false).getAttribute("uname"));
            String str="select username2 from friends where username1='"+uname+"';";
            ResultSet res=comm.retrieve(str);
            boolean flag=false;
            while(res.next()){
                flag=true;
                System.out.println(res.getString(1)+"\n");
                out.println((res.getString("username2")).toLowerCase()+"<br><br>");
                               }
    
          %>
         
         <br>
         <br><br><br>
         <%
    if(result!=null)
            out.println("entered user removed from friendlist successfully!!");
        
    %>
	    
<br><br>

<h3>Want to Un-friend anyone??</h3>

<br>Enter the friend name to unfriend : 
<form action="Unfriend.jsp" >
<input type="text" name="friendname"><br><br>
<input type="submit" value="Unfriend" >
</form>
<br>
<form action="LoginUser.jsp" >
        <input type="submit" value="Back" />
    </form>
</body>
</html>