<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*, dao.*, entity.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">
	body{
		background-image:url("imags/temp_oops.jpg");
		display: flex;
		align-items: center;
		justify-content: center;
	}
	table{
		margin-top:100px;
	}
	input{
		opacity:0.5;
		border-radius: 10px;
	}
	#review{
		font-family: "consolas";
		width:80%;
		border-collapse: collspse;
	}
	#review td, #question th{
		font-size: 1em;
		border: 1px solid #98bf21;
		padding 3px 7px 2px 7px;
	}
	#review th{
		font-size: 1.1em;
		text-align: left;
		padding-top: 5px;
		padding-bottom: 4px;
		background-color: #A7C942;
		color: #ffffff;
	}
	#review tr.alt td{
		color:#000000;
		background-color: #EAF2D3;
	}
</style>
</head>
<body>
<div style = "text-align: left; font-family: consolas; font-size: 20px;">
	<a href = "question.jsp">确定问题</a>
</div>
<div style = "text-align: center; font-family: consolas; font-size: 28px;">
	<a href = "oops.jsp">社员们</a>
</div>
	<form action="ReviewUpServlet" method = "post">
			<table id = "review">
			<%
				String id = request.getParameter("fk_review_user");
				int  fk_review_user = Integer.parseInt(id);
				out.print(id);
				session.setAttribute("fk_review_user", fk_review_user);
				ReviewDAO rd = new ReviewDAO();
			%>
			<tr>
				<td>昵称</td>
				<td><%=new UserDAO().get(fk_review_user).getUserName() %></td>
			</tr>
			<tr>
				<td>青年留言</td>
				<td><%=new ReviewDAO().getBySelf(fk_review_user).getReviewContent() %></td>
			</tr>
			<tr>
				<td>留言青年</td>
				<td>
					<textarea rows="1" cols="100" name = "reviewContent"></textarea><br>
					<input type = "submit" value = "up">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>