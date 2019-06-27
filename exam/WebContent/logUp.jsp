<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<style type="text/css">
		input{
			border-radius: 10px;
			opacity: 0.5;
		}
		html, body{
			height:100%;
		}
		body{
			background-image:url("imags/resgist.jpg");
			display: flex;
			align-items: center;
			justify-content: center;
		}
	</style>
</head>
<body>
	<form action="RegisteServlet" method = post>
		userName:<input type = "txt" name = "userName"><br><br>
		password:<input type = "password" name = "userPassword"><br><br>
		<input type = "text" name = "confirmNum"><img src="random_image.jsp"><br>
		submit:<input type = "submit"> reset:<input type = "reset"> 
	</form>
</body>
</html>