<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="smarthealthgui.*" %>
<%@ page import="java.sql.*" %>

<script type="text/javascript">
        function display() {
               
                document.getElementById("topic").style.display='block';
                document.getElementById("summary").style.display ='block';
                document.getElementById("url").style.display ='block';
                document.getElementById("t").style.display ='block';
                document.getElementById("s").style.display ='block';
                document.getElementById("u").style.display ='block';

        }
        </script>
</head>
<body bgcolor="SkyBlue">


<% //Displays the mooderator name obtained from the parameter of login and provides options
String uname=request.getParameter("usernamelogin");  
if(uname==null)
    uname=(String)request.getSession(false).getAttribute("uname");

request.setAttribute("uname",uname);
%>
<h2>Welcome Moderator <%= uname %>!</h2>





<% // displays all the forum id as hyperlinks
SQLcom comm=new SQLcom();

try
{
String sql="Select forumID from Forum  where whenClosed IS  NULL";

ResultSet rs=comm.retrieve(sql);

	    while(rs.next()){
	  	
	    HttpSession sess = request.getSession();  // svaing forum id in session to retieve in forum
        sess.setAttribute("forumId", rs.getString(1));
        
        out.println("<a href="+ "\"Forum.jsp?Id=" + rs.getString(1) + "\"" + ">"+ rs.getString(1)+ "</a>");
     
	    
}
}
catch(Exception e)
{
e.printStackTrace();	
}
%>




<br><br><form action="UpdateMod.jsp">
&nbsp<input type="submit" value="Update">
</form><br><br>
 
<input type="submit" value="CreateForum" onclick="display()"><br><br> 

<form action="ForumServlet"  method=post>


<div style="display: none;"  id="t"> Enter Topic</div>
<input type="text" id="topic"    name="Topic"  style="display:none"><br><br>
<div style="display: none;"  id="s">Enter Summary</div>
<input type="text" id="summary"  name="Summary" style="display:none"><br><br>
<div style="display: none;"  id="u">Enter URL</div>
<input type="text" id="url"      name="URL"     style="display:none"><br><br>
 
<input type="submit" value="Submit">
</form><br><br>

Wish to Delete Any?

<br><br><form action="ForumDelete"  method=post>
Enter the forum ID<input type="text" name="fid">
<input type="submit" value="Delete Forum  ">
</form>
<br><br><br>
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