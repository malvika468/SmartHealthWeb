<%-- 
    Document   : CreatePost
    Created on : Nov 13, 2016, 12:37:32 AM
    Author     : lenovo
--%>

<%@page import="smarthealthgui.Forum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="green">
        <% Forum f=(Forum)request.getSession(false).getAttribute("forum");
        HttpSession sess = request.getSession(); 
        sess.setAttribute("forum", f);
        System.out.println("p2,createpost:"+f.getForumId());
        %>
    <center>
        <font color="orangered"><h1><%= f.getTopic() %></h1></font>
        <h3>Create Post</h3>
        <form method=post action="PostServlet">                                          
What's on you Mind?&nbsp
<input type="text" name="post" ><br><br>
Choose any pic&nbsp
<input type="text" name="piclink" ><br><br>
Choose any web&nbsp
<input type="text" name="weblink" ><br><br>
Choose any video&nbsp
<input type="text" name="videolink" ><br><br>
<input type="submit" name="Submit"><br><br>
</form>
<br><br>

<form method=post action="CommentServlet">
Write any comment&nbsp
<input type="text" name="comment" ><br><br>
</form>

<form method=post action="RatingServlet">
Give any Rating?&nbsp
<input type="text" name="rating" ><br><br>
</form>
    </center>
    </body>
</html>
