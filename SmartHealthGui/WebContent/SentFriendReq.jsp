<%@page import="smarthealthgui.SQLcom"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<title>Insert title here</title>
</head>
<body bgcolor="SkyBlue">

<%
String uname=(String)(request.getSession(false).getAttribute("uname")); 
Date date=new Date();
SQLcom comm=new SQLcom();
Timestamp time=new Timestamp(date.getTime());
String sql1="Select requested_username from friendship where requester_username='"+uname+"' and (whenRequested is not NULL  and  whenWithdrawn is NULL)";
try{
ResultSet rs=comm.retrieve(sql1);
while(rs.next()){
out.println(rs.getString(1));
} 
}
catch(Exception e)
{
e.printStackTrace();  
}


%>

<h3>Want to withdraw any??</h3>

<br>Enter the friend name to cancel request : 
<form action="CancelReq"  method=post>
<input type="text" name="friendname"><br><br>
<input type="submit" value="Cancel" >
</form>
   
<br>
<form action="LoginUser.jsp" >
        <input type="submit" value="Back" />
</form>

</body>
</html>