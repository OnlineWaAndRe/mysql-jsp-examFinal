<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@ page import="java.util.*, dao.*, entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>不看口碑，看疗效</title>
<style type="text/css">
	body{
		background-image:url("imags/answer.jpg");
	}
	table{
		margin-top: 100px;
	}
	#answer{
		font-family: "consolas";
		width:100%;
		bor-collapse:collapse;
	}
	#answer td, #answer th{
		font-size:1em;
		border:1px solid #98bf21;
		padding 3px 7px 2px 7px;

	}
	#answer th{
		font-size: 1.1em;
		text-align: left;
		padding-top: 5px;
		padding-bottom: 4px;
		background-color: #A7C942;
		color:#ffffff;
	}
	#answer tr.alt td{
		color:#000000;
		background-color: #EAF2D3;
	}
	td{
		text-align: left;
	}
}
</style>
</head>
<body>
	<form action="AnswerServlet" method = post>
	<%
		List<Result> lr = (List<Result>)session.getAttribute("lr");
		int id = (int)session.getAttribute("questionId");
		String userName = (String)session.getAttribute("userName");
	%>
		<table id ="answer">
			<caption>切记:敬遵医嘱口服外用</caption>
			<tr>
				<td><%=userName %>>>问题:&nbsp&nbsp&nbsp<%=new QuestionDAO().get(id).getQuestionContent()%>&nbsp?</td>
			</tr>
			<%
				for(Result r: lr){
			%>
			<tr>
				<td><%=new UserDAO().get(new AnswerDAO().get(r.getAid()).getFk_answer_user()).getUserName()%>回复:&nbsp&nbsp&nbsp<%=new AnswerDAO().get(r.getAid()).getAnswerContent() %></td>
			</tr>
			<%} %>
			<tr>
				<td>
					<textarea rows="1" cols="100" name = "textUp">期待您的评论</textarea><br>
					up:<input type = "submit"vaule="up">
				</td>
			</tr>

		</table>
	</form>
</body>
</html>