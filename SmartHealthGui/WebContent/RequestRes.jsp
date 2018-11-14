<%-- 
    Document   : RequestRes
    Created on : Oct 25, 2016, 3:36:16 AM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <%@ page import="smarthealthgui.*" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
       
        <title>JSP Page</title>
    </head>
    <body bgcolor="SkyBlue">
        <% 
            String uname=(String)(request.getSession(false).getAttribute("uname")); //obtains username from the previous jsp
            String f=request.getParameter("userReq");           //obtains the username requested to connect to
            System.out.println("requested by :"+f);
            User u=new User();
            Date date=new Date();
            Timestamp time=new Timestamp(date.getTime());
            if(request.getParameter("acceptF")!=null && f!="")   //accept button pressed
            {
            	int i=u.confirmRequest(uname, f, time);
                    if(i==1)                                    //one record updated in db
                request.setAttribute("result"," Friend Request Accepted !! ");  
                    else                                        //no chanes in db
                        request.setAttribute("result"," Oops No such user sent friend request !! ");
            }
            else    
                if(request.getParameter("rejectF")!=null && f!="")  //reject button pressed
            {
                	 int i=u.rejectRequest(uname, f, time);
                if(i==1)                                        //one record updated in db
                request.setAttribute("result"," Friend Request Rejected !! ");
                else                                            //no chanes in db
                    request.setAttribute("result"," Oops No such user sent friend request !! ");
            }
            else
                    request.setAttribute("result"," wrong input");  //when any of the buttons is pressed with empty string 
         
                request.setAttribute("uname",uname);        //sets username for jsp to be called after it
            
            RequestDispatcher rd=request.getRequestDispatcher("SeeFriendReq.jsp");  
                            
	                 rd.forward(request, response);         //calls back SeeFriendReq jsp page
            
            %>
    </body>
</html>
