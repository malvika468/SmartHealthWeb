<%-- 
    Document   : UpdateAdmin
    Created on : Oct 28, 2016, 1:42:53 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ page import="smarthealthgui.*" %>
<%@page import="java.sql.ResultSet"%>
        <title>JSP Page</title>
    </head>
    <body>
        <center>   <h1 style="color:darkgreen">Update your profile</h1>
        <br><br>
<%
    
String uname1=(String)request.getSession(false).getAttribute("uname");  //obtaining username from previous jsp
    
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
String phone="";

//to display the values earlier provided(fetched from db) to be displayed by the html components
SQLcom comm=new SQLcom();
String str="select * from user where username='"+uname1+"';";   
ResultSet res=comm.retrieve(str);
String str2="select phone from administrator where username='"+uname1+"';";
ResultSet res2=comm.retrieve(str2);

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

while(res2.next())
{
phone=res2.getString(1);	
}

request.setAttribute("uname",uname1);
%>

<form action="UpdateAdminHandle.jsp" method=post  >  
UserName&nbsp<input type="text"  name="username"    value=<%=uname1%>   disabled  ><br><br>
Email-One&nbsp
<input type="text"  name="email1"    value=<%=e1%>><br><br>
Email-Two&nbsp
<input type="text"  name="email2"    value=<%=e2%>><br><br>
Last Name&nbsp
<input type="text"  name="lastname"      value=<%=l%>><br><br>
First name&nbsp
<input type="text"   name="firstname"   value=<%=f%>><br><br>
Password&nbsp
<input type="text"   name="password"       value=<%=p%>><br><br>
StreetNumber&nbsp
<input type="text"     name="streetNo"    value=<%=sn1%>><br><br>
StreetName&nbsp
<input type="text"       name="streetName"   value=<%=sn2%>><br><br>
Major Municipality&nbsp
<input type="text"        name="municipality"    value=<%=mun%>><br><br>
District&nbsp
<input type="text"        name="district"        value=<%=dis%>><br><br>
Something About You&nbsp
<input type="text"         name="aboutYou"       value=<%=a%>><br><br>
Postal Area&nbsp
<input type="text"        name="postal"        value=<%=pa%>><br><br>
Phone&nbsp
<input type="text" name="phone"   value=<%=phone%>><br><br>
<input type="submit" value="Submit">
</form>
</center>
    </body>
</html>
