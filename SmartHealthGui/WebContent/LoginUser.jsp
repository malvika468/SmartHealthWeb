<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="smarthealthgui.*" %>
<%@ page import="java.sql.*" %>


</head>
<body bgcolor="SkyBlue">
 <% //Displays the enduser name obtained from the parameter of login and provides the options
String uname1=request.getParameter("usernamelogin");   
if(uname1==null)
        uname1=(String)request.getAttribute("uname");   //if uname comes from update and other jsps
if(uname1==null)
        uname1=(String)request.getSession(false).getAttribute("uname");
User u=new User(uname1);
String result1=(String)request.getAttribute("result");  //stores the result obtained from the add friend jsp
request.setAttribute("result", null);
String username=(String)request.getAttribute("uname");
request.setAttribute("uname", uname1); 
HttpSession sess = request.getSession(); 
sess.setAttribute("unamelogin", uname1);
//ArrayList<HashMap> resF=(ArrayList)request.getAttribute("resultF");
ArrayList<HashMap> resF=u.getHealthData().getFriendsHealthData(uname1);
//sets the username to be given to jsps called from it
%>
    <style type="text/css">
    .topcorner{
        position:absolute;
        top:0;
        right:0;
        padding-right: 1cm;
              }
      .bottomcorner{
        position:absolute;
        bottom:0;
        right:0;
        padding-right: 1cm;
        padding-bottom: 2cm;
              }
              
    </style>
    
<h2>Welcome <%= uname1 %>!</h2>

<img src="ServletImage" alt="images Here" width="130px" height="90px"/>


<script language="javascript"> 
function toggle() {
	var ele = document.getElementById("toggleText");
	var text = document.getElementById("displayText");
	if(ele.style.display == "block") {
    		ele.style.display = "none";
		text.innerHTML = "Friends Health data";
                
  	}
	else {
		ele.style.display = "block";
		text.innerHTML = "Friends Health data";
	}
} 
</script>


<div class="topcorner">
    
        <form action="ChangeHD.jsp">
        
        <h4>My Health data</h4>
        <ul data-role="listview">
            <li>Run : <%= u.getHealthData().getRun() %></li>
            <li>Calories burned : <%= u.getHealthData().getCalories_burned() %></li>
            <li>Blood pressure : <%= u.getHealthData().getBloodPressure() %></li>
        </ul>
        <input type="submit" value="change">
        </form>
    
    <a href="Home.html" style="text-decoration: none"><h4>Logout</h4></a>
</div>

<br>

    
<% // displays the forum links 
SQLcom comm=new SQLcom();

try
{
String sql="Select forumID from Forum  where whenClosed IS  NULL";

ResultSet rs=comm.retrieve(sql);

	    while(rs.next()){
	  	
	     HttpSession sess1 = request.getSession(); 
         sess1.setAttribute("Id", rs.getString(1));
        
         out.println("<a href="+ "\"Forum.jsp?Id=" + rs.getString(1) + "\"" + ">"+ rs.getString(1)+ "</a>");
     
	    
}
}
catch(Exception e)
{
e.printStackTrace();	
}
%>



<br><br>
<br>





   
<br>
<form action="UpdateUser.jsp">
<input type="submit" value="Update">
</form>


<br><br>
<form action="SeeFriends.jsp">
<input type="submit" value="View all friends">
</form>
<br><br>
<form action="SeeFriendReq.jsp">
<input type="submit" value="View friend requests">
</form>
<br><br><br>

<form action="SentFriendReq.jsp">
<input type="submit" value="View sent requests">
</form>
<br><br><br>

<div class="bottomcorner">
<h4>Want new connection</h4>
Enter user name :
<form action="SendFriendReq.jsp">
    <input type="text" name="requestedPerson"><br><br>
<input type="submit" value="Send Request">
</form>
<br><br><br>
</div>
 <div data-role="collapsible">
        
        <a id="displayText" href="javascript:toggle();" style="text-decoration: none">Friends Health data </a>
        <br>
        <div id="toggleText" style="display: none">
            <% if(resF==null)
                out.println("<br>nothing to show<br>");
            else
            {   out.println("<ul>");
                for(HashMap h:resF){
                     String a=h.get("run").toString(),b=h.get("calories-burned").toString(),c=h.get("blood-pressure").toString()
                             ,d="meters",e="cal",f="mmHg";
                     if(a.equalsIgnoreCase("not-specified"))d="";
                     if(b.equalsIgnoreCase("not-specified"))e="";
                     if(c.equalsIgnoreCase("not-specified"))f="";
                     out.print("<li><font size=\"3\" color=\"red\"><b>&nbsp;&nbsp;"+h.get("Name:")+"</b></font><br>");
                     out.print("&nbsp;<font size=\"2\"><b>run count:</b></h5>&nbsp;"+h.get("run")+d+
                             "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"2\"><b>calories-burned:</b></font>&nbsp;"+
                             h.get("calories-burned")+e+"&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"2\"><b>blood-pressure:</b></font>"
                             + "&nbsp;"+h.get("blood-pressure")+f+"</li>");
                }
                out.println("</ul>");
            }
            %>
        
        </div>
        
    </div>
<br><br><br><br>



<br><br><br>
<form  action ="DeleteAcc" method=post>
<input type="submit" value="Delete Account" >
</form>
<br><br><br>

<%
    if(result1!=null)
            out.println(result1);
        
    %>

</body>
</html>