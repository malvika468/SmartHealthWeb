<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>File Upload to Database</h1>
        <form name="fileform" method="post" action="uploadServlet" enctype="multipart/form-data">
            <input type="file" name="photo" size="50" placeholder="Upload Your Image" required/><br><br>
            <input type="submit" value="Save">
        </form>
</body>
</html>
