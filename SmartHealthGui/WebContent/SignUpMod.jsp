<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.sql.*" %>
<%@ page import="smarthealthgui.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.*" %>
<title>Insert title here</title>
</head>
<body>
<%
//obtains all the entered details by user in html fields
long UID1=0;
SQLcom comm=new SQLcom();
String url[]=new String[3];
String uname=request.getParameter("username");
String email1=request.getParameter("email1");
String email2=request.getParameter("email2");
String lastname=request.getParameter("lastname");
String firstname=request.getParameter("firstname");
String password=request.getParameter("password");
String streetNo=request.getParameter("streetNo");
String streetName=request.getParameter("streetName");
String muncipality=request.getParameter("municipality");
String district=request.getParameter("district");
String aboutMe=request.getParameter("aboutYou");
String postal=request.getParameter("postal");
url[0]=request.getParameter("browse1");
url[1]=request.getParameter("browse2");
url[2]=request.getParameter("browse3");
String phone=request.getParameter("phone");
String qual=request.getParameter("quali1");

//retrieving max user id from db in user table
ResultSet res;
res=comm.retrieve("Select max(usertypeId) from user;");
res.next();
UID1=res.getLong(1);
String str="Insert into User values('"+uname+"','"+password+"','"+email1+"','"+email2+
        "','"+firstname+"','"+lastname+"','"+aboutMe+"','"+url[0]+"','"+url[1]+"','"+url[2]+"','"+streetNo+"','"+streetName+"','"+
        muncipality+"','"+district+"','"+postal+"'";

String substr;      
UID1++;             //increments the obtained userid(last entered)
str=str+","+UID1+",1);";

substr="Insert into moderator values('"+uname+"','"+phone+"');";
    int i5=comm.update(substr);    //inserts into moderator table

substr="Insert into usertype values("+UID1+","+"'mod');";
int i1=comm.update(substr);        //inserting into usertype table
int i2=comm.update(str);      

String cmd="Select Qualificationid from qualification where description='"+qual+"';";   //obtains id of qualification entered by user if exists
res=comm.retrieve(cmd);
res.last();int i3=0,i4=0;
if(res.getRow()<0)      
{
  cmd="insert into qualification(description) values('"+qual+"');"; //setting qualification id to autoincremented                        }
  i3=comm.update(cmd);
}

java.util.Date date = new java.util.Date();
long time = date.getTime();
String dateof=new java.sql.Timestamp(time).toString();
String substr1="Insert into moderatorqualification values((select qualificationid from qualification where description='"+qual+"'),'"+uname+"','"+dateof+"');";
i4=comm.update(substr1);

String strr;    //stores the msg of registartion status displaysed by it 
if(i2==1&&i5==1)
 { out.println("You are successfully registered");strr="Login";}
 else
 {
     out.println("Oops pls supply correct details ");strr="Try again";
 }
%>
<br><br>
<form action="Home.html">
    <input type="submit" value="<%= strr %>">
</form>
</body>
</html>