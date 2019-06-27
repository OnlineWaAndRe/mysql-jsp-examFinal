<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>****</title>
	<style type="text/css">
		input{
			border-radius: 10px;
			width:120px;
			height:20px;
		}
		#logset{
			text-align:center;
			opacity:0.5;
		}
		#subset{
			opacity:0.5;
			margin-top:.5rem;
			border-radius:15px;
			text-align: center;
			display: flex;
			justify-content: center;
		}
		html, body{
			height:100%;
		}
		body{
			background-image:url("imags/login.jpg");
			display: flex;
			align-items: center;
			justify-content: center;
		}
	</style>
</head>
<body>
	<form action="LoginServlet" method = post>
	<%
		String userName = "";
		String userPassword = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!= null){
			for(Cookie c:cookies){
				if(c.getName().equals("userNameCookie")){
					userName = c.getValue();
				}
				if(c.getName().equals("userPasswordCookie")){
					userPassword = c.getValue();
				}
			}
		}
	%>
	    <div id = "logset">
			userName:<input type = "text" name = "userName" value = <%=userName %>><br><br>
			password:<input type = "password" name = "userPassword" value =<%=userPassword%>><br><br>
		</div>
		<div id = "subset">
			login:<input type = "submit" value = "login"> reset:<input type = "reset"> 
			<a href = "logUp.jsp">logUp</a>
		</div>
	</form>
</body>
</html>