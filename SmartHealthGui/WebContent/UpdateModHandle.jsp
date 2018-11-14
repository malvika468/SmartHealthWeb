<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="smarthealthgui.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<title>Insert title here</title>
</head>
<body>
<%
    
SQLcom comm=new SQLcom();

//obtaining the values entered by user in update form
String uname=(String)request.getSession(false).getAttribute("uname");
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
String phone=request.getParameter("phone");
String quali=request.getParameter("quali");

//executing db queries for update
String sql1 = "UPDATE user " +
"SET  password='"+password+"' WHERE  username='"+uname+"';"; 
String sql2 = "UPDATE user " +
"SET  email1='"+email1+"' WHERE  username='"+uname+"';"; 
String sql3 = "UPDATE user " +
"SET  password='"+password+"' WHERE  username='"+uname+"';"; 
String sql4 = "UPDATE user " +
"SET  email2='"+email2+"' WHERE  username='"+uname+"';"; 
String sql5 = "UPDATE user " +
"SET  firstname='"+firstname+"' WHERE  username='"+uname+"';"; 
String sql6 = "UPDATE user " +
"SET  lastname='"+lastname+"' WHERE  username='"+uname+"';"; 
String sql7 = "UPDATE user " +
"SET  streetNumber='"+streetNo+"' WHERE  username='"+uname+"';"; 
String sql8 = "UPDATE user " +
"SET  streetName='"+streetName+"' WHERE  username='"+uname+"';"; 
String sql9 = "UPDATE user " +
"SET  MajorMunicipality='"+muncipality+"' WHERE  username='"+uname+"';"; 

String sql10 = "UPDATE user " +
"SET  GoverningDistrict='"+district+"' WHERE  username='"+uname+"';"; 

String sql11 = "UPDATE user " +
"SET  AboutMe='"+aboutMe+"' WHERE  username='"+uname+"';"; 

String sql12 = "UPDATE user " +
"SET  PostalArea='"+postal+"' WHERE  username='"+uname+"';"; 

String sql13 = "UPDATE moderator " +
"SET  phone='"+phone+"' WHERE  username='"+uname+"';"; 

String sql15="select qualificationid from qualification where description='"+quali+"';";
ResultSet res1=comm.retrieve(sql15);

int qualid=-1;

while(res1.next())qualid=res1.getInt(1);
System.out.println("retrieved:"+qualid);
String sql14 = "UPDATE moderatorqualification SET  qualificationid="+qualid+" WHERE  username='"+uname+"';"; 

//storing results of execution of queries
int i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14;
i1=comm.update(sql1);
i2=comm.update(sql2);
i3=comm.update(sql2);
i4=comm.update(sql4);
i5=comm.update(sql5);
i6=comm.update(sql6);
i7=comm.update(sql7);
i8=comm.update(sql8);
i9=comm.update(sql9);
i10=comm.update(sql10);
i11=comm.update(sql11);
i12=comm.update(sql12);
i13=comm.update(sql13);
i14=comm.update(sql14);
System.out.println("i14:"+i14);

String result="";//stores result based on if all updates are executed successfully or not which is diplayed then to user
if(i1==1&&i2==1&&i3==1&&i4==1&&i5==1&&i6==1&&i7==1&&i8==1&&i9==1&&i10==1&&i11==1&&i12==1&& i13==1&&i14==1)
    result="Your details are successfully updated";
else
    result="Not updated ! enter correct details";
request.setAttribute("uname",uname);
%>

<h2><%= result %> </h2>

    <form action="LoginMod.jsp">
        <input type="submit" value="Back">
    </form>
</body>
</html>