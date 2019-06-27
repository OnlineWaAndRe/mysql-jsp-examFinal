package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDAO;
import dao.UserDAO;
import entity.Question;

@WebServlet("/QuestionUpServlet")
public class QuestionUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuestionUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String questionContent = request.getParameter("questionContent");
		Question questionBean = new Question();
		Cookie[] cookies = request.getCookies();
		String userName = "";
		for(Cookie c: cookies) {
			if(c.getName().equals("userNameCookie")) {
				userName = c.getValue();
			}
		}
		questionBean.setFk_question_user(new UserDAO().get(userName).getUserId());
		questionBean.setQuestionContent(questionContent);
		new QuestionDAO().add(questionBean);
		response.sendRedirect("question.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
