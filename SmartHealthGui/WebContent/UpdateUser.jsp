<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="smarthealthgui.*" %>
<%@page import="java.sql.ResultSet"%>
<title>Insert title here</title>
</head>
<body bgcolor="SkyBlue">
    <center>   <h1 style="color:darkgreen">Update your profile</h1>
        <br><br>
<%
//to display the values earlier provided(fetched from db) to be displayed by the html components
String uname1=(String)request.getSession(false).getAttribute("uname");  
String p="";
String e1="";
String e2="";
String f="";
String l="";
String a="";
String sn1="";
String sn2="";
String mun="";
String dis="";
String pa="";

try{
SQLcom comm=new SQLcom();
String str="select * from user where username='"+uname1+"';";
ResultSet res=comm.retrieve(str);


while(res.next())
{
e1=res.getString(3);
p=res.getString(2);
e2=res.getString(4);
f=res.getString(5);
l=res.getString(6);
a=res.getString(7);
sn1=res.getString(11);
sn2=res.getString(12);
mun=res.getString(13);
dis=res.getString(14);
pa=res.getString(15);
}
request.setAttribute("email1",e1);
request.setAttribute("uname",uname1);
}
catch(Exception e){
    System.out.println(e);
}

%>
<form action="UpdateUserHandle.jsp" method=post >  
UserName&nbsp<input type="text"  name="username"    value=<%= uname1 %>   disabled  ><br><br>
Email-One&nbsp
<input type="text"  name="email1"    value="${email1}"><br><br>
Email-Two&nbsp
<input type="text"  name="email2"    value=<%= e2 %>><br><br>
Last Name&nbsp
<input type="text"  name="lastname"      value=<%= l %>><br><br>
First name&nbsp
<input type="text"   name="firstname"   value=<%= f %>><br><br>
Password&nbsp
<input type="text"   name="password"       value=<%= p %>><br><br>
StreetNumber&nbsp
<input type="text"     name="streetNo"    value=<%= sn1 %>><br><br>
StreetName&nbsp
<input type="text"       name="streetName"   value=<%= sn2 %>><br><br>
Major Municipality&nbsp
<input type="text"        name="municipality"    value=<%= mun %>><br><br>
District&nbsp
<input type="text"        name="district"        value=<%= dis %>><br><br>
Something About You&nbsp
<input type="text"         name="aboutYou"       value=<%= a %>><br><br>
Postal Area&nbsp
<input type="text"        name="postal"        value=<%= pa %>><br><br>
<input type="submit" value="Submit">
</form>
    </center>
</body>
</html>