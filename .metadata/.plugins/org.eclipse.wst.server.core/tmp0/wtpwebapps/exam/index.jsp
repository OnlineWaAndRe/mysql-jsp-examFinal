<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginServlet" method = post>
		userName:<input type = "txt" name = "userName"><br>
		password:<input type = "password" name = "userPassword"><br>
		login:<input type = "submit" value = "login"> reset:<input type = "reset"> 
		<a href = "logUp.jsp">loginUp</a>
	</form>
</body>
</html>