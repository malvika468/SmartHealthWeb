<%-- 
    Document   : UserResult
    Created on : Nov 13, 2016, 1:34:33 AM
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
    <body>
        <% 
            Forum f=(Forum)request.getSession(false).getAttribute("forum");
            System.out.println("p3userresult:"+f.getForumId());
            String result=(String)request.getSession(false).getAttribute("result");
            HttpSession sess = request.getSession(); 
                    sess.setAttribute("forum", f);
                    sess.setAttribute("id", String.valueOf(f.getForumId()));
                    
        %>
        <h3><%= result %></h3>
        <form action="Forum.jsp">
            <input type="submit" value="Back"/>
        </form>
    </body>
</html>
