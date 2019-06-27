<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="dao.*, entity.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">
	td{
		text-align:center;
		opacity:0.5;
	}
	#review{
		font-family: "consolas";
		width:80%;
		border-collapse: collapse;
		border-radius:15px;
	}
	#user td, #user th{
		font-size:1em;
		border:1px solid #98bf21;
		padding 3px 7px 2px 7px;
	}
	#user th{
		font-size:1.1em;
		text-align:left;
		padding-top:5px;
		padding-bottom:4px;
		background-color: #A7C942;
		color:#ffffff;
	}
	#user tr.alt td{
		color:#000000;
		background-color: #EAF2D3;
	}
	body{
		background-image:url("imags/user.jpg");
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
	<%
	String userName = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!= null){
		for(Cookie c:cookies){
			if(c.getName().equals("userNameCookie")){
				userName = c.getValue();
			}
		}
	}
		ReviewDAO reviewBean = new ReviewDAO();
		List<Review> lr = reviewBean.listByFk(new UserDAO().get(userName).getUserId());
		out.print(new UserDAO().get(userName).getUserId());
	%>
		<table id = "user">
			<th>向我这样的人啊~</th>
			<tr>
				<td>用户名</td>
				<td><%=userName %></td>
			</tr>
			<tr>
				<td>个人介绍</td>
				<td><%=reviewBean.getBySelf(new UserDAO().get(userName).getUserId()).getReviewContent() %></td>
			</tr>
			<tr>
				<td>修改个人介绍</td>
				<td>
					<a href="temp_oops.jsp?fk_review_user=<%=new UserDAO().get(userName).getUserId()%>"><input type = "submit" value = "reviewUp"></a>
				</td>
			</tr>
			<tr>
				<td>修改个人信息</td>
				<td><a href="logUp.jsp"><input type = "submit" value = "logUp"></a></td>
			</tr>

			<tr>
				<td>退出登录</td>
				<td><a href="out.jsp"><input type = "submit" value = "quit"></a></td>
			</tr>
			<%
				for(Review r:lr){
			%>
			<tr>
				<td>谁对我的关照</td>
				<td><a href = "temp_oops.jsp?fk_review_user=<%=r.getSelfId()%>"> <%=new UserDAO().get(new ReviewDAO().getByFk(r.getSelfId()).getFk_review_user()).getUserName()%></a></td>
			</tr>
			<tr>
				<td>说的什么甜言蜜语</td>
				<td><%=new ReviewDAO().get_review(r.getSelfId(),r.getFk_review_user()).getReviewContent()%></td>
			</tr>
			<%} %>
		</table>
</body>
</html>