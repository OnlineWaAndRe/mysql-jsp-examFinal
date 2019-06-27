<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0){
			for(Cookie c: cookies){
				if(c.getName().equals("userNameCookie") || c.getName().equals("userPasswordCookie")){
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}
		HttpSession sessions = request.getSession();
		String[] sessionName = session.getValueNames();
		for(String name: sessionName){
			sessions.removeAttribute(name);
		}
		response.sendRedirect("index.jsp");
	%>
</body>
</html>