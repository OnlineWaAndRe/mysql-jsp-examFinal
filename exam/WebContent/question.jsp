<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "dao.*, entity.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	td{
		text-align:center;
	}
	#question{
		font-family: "consolas";
		width:80%;
		border-collapse: collapse;
	}
	#question td, #question th{
		font-size:1em;
		border:1px solid #98bf21;
		padding 3px 7px 2px 7px;
	}
	#question th{
		font-size:1.1em;
		text-align:left;
		padding-top:5px;
		padding-bottom:4px;
		background-color: #A7C942;
		color:#ffffff;
	}
	#question tr.alt td{
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
	<a href = "user.jsp">个人主页</a>
</div>
<div style = "text-align: right; font-family: consolas; font-size: 28px;">
	<a href = "oops.jsp">社员们</a>
</div>
	<form action="QuestionUpServlet" method = "post">
		<%
			QuestionDAO qd = new QuestionDAO();
			List<Question> question = qd.list();
		%>
		<table id = "question" align="center">
			<tr>
				<th>问题青年</th>
				<th>青年问题</th>
			</tr>
			<%

				for(Question i: question){
			%>
			<tr>
				<td><a href="temp_oops.jsp?fk_review_user=<%=i.getFk_question_user()%>"><%=new UserDAO().get(i.getFk_question_user()).getUserName() %></a></td>
				<td> <a href = "temp_question.jsp?questionId=<%=i.getQuestionId() %>&userName=<%=new UserDAO().get(i.getFk_question_user()).getUserName()%>>"> <%= new QuestionDAO().get(i.getQuestionId()).getQuestionContent() %></a></td>
			</tr>
			<%} %>
			<tr>
				<td>身量苗条,体格风骚,问个敏感的问题先?</td>
				<td>
					<textarea rows="1" cols="100" name = "questionContent" >一问三知</textarea>
					<input type = "submit" value = "up">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>