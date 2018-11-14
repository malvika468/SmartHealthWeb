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
<%@ page import="java.util.regex.Pattern"%>
<%@ page import="java.util.regex.Matcher;" %>


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
 
 ResultSet res;
 
 String str2="select * from user where username='"+uname+"';";///checks for unique username
 res=comm.retrieve(str2);//CALLING DB--Getting resultset
 res.last();
 int c=res.getRow();
 if(c==1)
 { //Display 
  //System.out.print("Entered user name already exists.. please enter different!!");
 }
 // for email vaidation
 String EMAIL_REGIX = "^[\\\\w!#$%&*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
 Pattern pattern = Pattern.compile(EMAIL_REGIX);
 Matcher matcher = pattern.matcher(email1);
 boolean i=((!email1.isEmpty()) && email1!=null) && (matcher.matches());
 
 
 
 ////////////i=true valid email
 
 res=comm.retrieve("Select max(usertypeId) from user;");
 res.next();
 UID1=res.getLong(1);
 String str="Insert into User values('"+uname+"','"+password+"','"+email1+"','"+email2+
         "','"+firstname+"','"+lastname+"','"+aboutMe+"','"+url[0]+"','"+url[1]+"','"+url[2]+"','"+streetNo+"','"+streetName+"','"+
         muncipality+"','"+district+"','"+postal+"'";
 
 String substr;      //query to be used for the usertype and mod , admin table
 UID1++;
 str=str+","+UID1+",1);";
 
 DateFormat d1=new SimpleDateFormat("yyyy/MM/dd");
 substr="Insert into enduser values('"+uname+"',0,'"+d1.format(new java.util.Date())+"');";
 comm.update(substr);      //inserting into end user table first
 UID1=UID1=1;
 substr="Insert into usertype values("+UID1+","+"'new');";
 int i1=comm.update(substr);        //inserting into usertype table
 System.out.println("i1 value : "+i1);
 
 int i2=comm.update(str);       //inserts in user table
 System.out.println("i2 value : "+i1);
 String strr;
 if(i1==1&&i2==1)               //registration status based on the results of above two queries is displayed by the jsp
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