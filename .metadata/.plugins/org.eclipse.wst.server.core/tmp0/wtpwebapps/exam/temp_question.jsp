<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*, dao.*, entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Question q = new Question();
		String id = request.getParameter("questionId");
		int questionId = Integer.parseInt(id);
		ResultDAO rd = new ResultDAO();
		List<Result> lr = rd.list(questionId);
		session.setAttribute("lr", lr);
		response.sendRedirect("answer.jsp");
	%>
</body>
</html>