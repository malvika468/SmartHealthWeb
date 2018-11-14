<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body  bgcolor="SkyBlue">

<% //Displays the administrator name obtained from the parameter of login and provides the update and logout options
String uname=request.getParameter("usernamelogin");  
if(uname==null)
    uname=(String)request.getSession(false).getAttribute("uname");
request.setAttribute("uname",uname);
%>

<h2>Welcome <%= uname %>!</h2>
<br><br>

<form action="UpdateAdmin.jsp">
<input type="submit" value="Update">
</form><br><br><br><br>

<form action="Home.html">
<input type="submit" value="Logout">
</form>

<br><br><br>
<form  action ="DeleteAcc" method=post>
<input type="submit" value="Delete Account" >
</form>
<br><br><br>

</body>
</html>