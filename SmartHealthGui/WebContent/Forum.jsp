<%@page import="java.util.Collections"%>
<%@page import="smarthealthgui.Comment"%>
<%@page import="smarthealthgui.Rating"%>
<%@page import="smarthealthgui.Post"%>
<%@page import="java.util.ArrayList"%>
<%@page import="smarthealthgui.Forum"%>
<%@page import="smarthealthgui.SQLcom"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="java.sql.*" %>
<title>Insert title here</title>
</head>
<body  bgcolor="Lightgreen"> 
    <center>
<%!  static int nc; %>



<%
            
 String fid=(String)request.getParameter("Id");
 System.out.println("received id :"+fid);
 if(fid==null)
     fid=(String)request.getSession(false).getAttribute("id");
 System.out.println("received id1 :"+fid);
 String uname=(String)request.getSession(false).getAttribute("uname");
 //if(f==null)
 Forum  f=Forum.getForum(Integer.parseInt(fid));
 HttpSession sess = request.getSession(); 
sess.setAttribute("forum", f);
sess.setAttribute("uname",uname);
sess.setAttribute("id",fid);
System.out.println("p1:"+fid+f.getForumId());
%>


<font color="orangered"><h1><%= f.getTopic() %></h1></font>
<h4>forum</h4>
<p><font color="maroon">Summary: </font><%= f.getSummary() %></p>
    </center>
    <br><br><br>
    <% 
    //out.println(f.getSummary());
    ArrayList<Post> posts=f.getPostList();
    if(posts.isEmpty())
        out.println("no posts exist!");
    else{
        Collections.sort(posts);
    for(Post p:posts){
        
        HttpSession sess1 = request.getSession();
        sess.setAttribute("post",p.getTimeCreated().toString());
         nc=p.getCommList().size();
        ArrayList<Rating> ratings=p.getRatList();
        int rate=0;
        
        for(Rating r:ratings)rate+=r.getStar();
        if(ratings.isEmpty()==false)rate=rate/ratings.size();
        
        out.println("<br><b>"+p.getUsername()+":</b>&nbsp;<br>");
        //out.println("<textarea style=\"width:80%;\" disabled>"+p.getText()+"</textarea><br>rating:"+rate+"("+ratings.size()+")<br>");
        out.println("<textarea style=\"width:80%;\" disabled>"+p.getText()+"</textarea><br>");
        
        out.println("rating:"+rate+"("+ratings.size()+
                        ")&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=\"2\" >"+p.getTimeCreated()+"</font><br>");
         
        //out.println("<div data-role=\"collapsible\"><a id=\"displayText\" href=\"javascript:toggle();\" style=\"text-decoration: none\">"+nc
          //      +"&nbsp;comments</a><div id=\"toggleText\" style=\"display: none\"><ul style=\"list-style-type:none\" >");
        
        ArrayList<Comment> comments=p.getCommList();
        Collections.sort(comments);
        if(comments.size()==0)out.println("<font color=\"blue\">"+comments.size()+"&nbsp;comments</font><br>");
        else{
            out.println("<ul style=\"list-style-type:none\" >");
                    out.println("<font color=\"blue\">"+comments.size()+"&nbsp;comments</font>");
        for(Comment h:comments){
                    
                     String cText=h.getText(),unameC=h.getUnameComment(),cTime=h.getTimeComment().toString();
                    out.print("<li><font size=\"3\" color=\"red\"><b>&nbsp;"+unameC+"</b></font>&nbsp;&nbsp;&nbsp;<font size=\"2\">"+
                            cTime+"</font><br>");
                     out.print("&nbsp;<textarea style=\"width:60%;\" disabled>"+cText+"</textarea><br>"+"</li>");
                }
                out.print("</ul> ");
                
        }   
        String p1=p.getTimeCreated().toString();
        out.println("<form action=\"AddComment?post="+p1+"\" method=\"post\">"
                        + "<input type=\"text\" placeholder=\"Write a comment...\" name=\"comment\" >&nbsp;"
                        + "<input type=\"text\" placeholder=\"add photo..\" name=\"photo\" >&nbsp;"
                        + "<input type=\"text\" placeholder=\"add link...\" name=\"link\" >&nbsp;"
                        + "<input type=\"text\" placeholder=\"add video...\" name=\"video\">&nbsp;&nbsp;"
                + "<input type=\"submit\" value=\"post\"></form><br>");
        
        String disable="";
        if(!Forum.checkRating(p, uname, p.getTimeCreated()))disable="disabled";
        System.out.println("disable value : "+disable);
        out.println("<form action=\"AddRating?post="+p1+"\" method=\"post\"><select name=\"rating\"><option value=\"none\" disabled=\"disabled\" selected=\"selected\">Rate the post</option>"
                + "<option value=\"1\">1</option><option value=\"2\">2</option><option value=\"3\">3</option>"
                + "<option value=\"4\">4</option><option value=\"5\">5</option></select>"+"&nbsp;&nbsp;"
                + "<input type=\"submit\" value=\"post rating\" "+ disable+"></form><br>");
        out.println("<br><br><br>");
        
    }
    
    }
    
    %>
    
 <script language="javascript"> 
function toggle() {
	var ele = document.getElementById("toggleText");
	var text = document.getElementById("displayText");
        var nc="<%=nc%>";
        var tt=" comments";
        var com=nc+tt;
	if(ele.style.display == "block") {
    		ele.style.display = "none";
		text.innerHTML = com;
                
  	}
	else {
		ele.style.display = "block";
		text.innerHTML = com;
	}
} 
</script>
        <br>
    <form action="CreatePost.jsp" >
        <input type="submit" value="Add Post" />
    </form>
        <br><br>
    <form action="LoginUser.jsp" >
        <input type="submit" value="Back" />
    </form>
        <br><br><br>
</body>
</html>