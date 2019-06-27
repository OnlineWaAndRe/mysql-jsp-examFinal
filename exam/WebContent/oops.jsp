<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="dao.*, entity.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>oh my opps~</title>
<style type="text/css">
	td{
		text-align:center;
	}
	#review{
		font-family: "consolas";
		width:100%;
		border-collapse: collapse;
	}
	#review td, #review th{
		font-size:1em;
		border:1px solid #98bf21;
		padding 3px 7px 2px 7px;
	}
	#review th{
		font-size:1.1em;
		text-align:left;
		padding-top:5px;
		padding-bottom:4px;
		background-color: #A7C942;
		color:#ffffff;
	}
	#review tr.alt td{
		color:#000000;
		background-color: #EAF2D3;
	}
	body{
		background-image:url("imags/question.jpg");
	}
	table{
		display: flex;
		align-items: center;
		justify-content: center;
	}
</style>
</head>
<body>
<div style = "text-align: left; font-family: consolas; font-size: 20px;">
	<a href = "question.jsp">确定问题</a>
</div>
<div style = "text-align: left; font-family: consolas; font-size: 20px;">
	<a href = "user.jsp">个人主页</a>
</div>
	<table id = "review">
		<tr>
			<th>哥们姐们</th>
		</tr>
		<%
			List<User> userBeans = new UserDAO().list();
			for(User u:userBeans){
		%>
		<tr>
			<td><a href="temp_oops.jsp?fk_review_user=<%=u.getUserId() %>"><%=u.getUserName()%></a></td>
		</tr>
		<%} %>
	</table>
</body>
</html>