<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ page import="java.util.*, dao.*, entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Result> lr = (List<Result>)session.getAttribute("lr");
	%>
	<table align = "center">
		<tr>
		 	<td> 答案</td>
		</tr>
		<%
			for(Result r: lr){
		%>
		<tr>
			<%AnswerDAO ad = new AnswerDAO() ;%>
			<td><%=ad.get(r.getAid()).getAnswerContent() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>