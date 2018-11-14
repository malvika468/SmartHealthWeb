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
 
 //retrieving max user id from db in user table
 ResultSet res;
 res=comm.retrieve("Select max(usertypeId) from user;");
 res.next();
 UID1=res.getLong(1);
 String str="Insert into User values('"+uname+"','"+password+"','"+email1+"','"+email2+
         "','"+firstname+"','"+lastname+"','"+aboutMe+"','"+url[0]+"','"+url[1]+"','"+url[2]+"','"+streetNo+"','"+streetName+"','"+
         muncipality+"','"+district+"','"+postal+"'";
 
 String substr;      
 UID1++;                //increments the obtained userid(last entered)
 str=str+","+UID1+",1);";   
 substr="Insert into administrator values('"+uname+"','"+phone+"');";   //creates entry in administrator
 comm.update(substr);    //inserting into admin table
 substr="Insert into usertype values("+UID1+","+"'admin');";            //creates netry in user table
 int i1=comm.update(substr);        //inserting into usertype table
 int i2=comm.update(str);    
 System.out.println("i1:"+i1+"i2:"+i2);
 String strr;
 if(i1==1&&i2==1)               //checks if both tables were updated or not for successfull registration
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