package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AnswerDAO;
import dao.ResultDAO;
import dao.UserDAO;
import entity.Answer;
import entity.Result;
import entity.User;

@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String textUp = request.getParameter("textUp");
		String userName = "";
		if(textUp != null && !textUp.equals("ÆÚ´ýÄúµÄÆÀÂÛ")) {
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies) {
				if(c.getName().equals("userNameCookie"))
					userName = c.getValue();
			}
			Answer bean = new Answer();
			bean.setAnswerContent(textUp);
			
			User userBean = new UserDAO().get(userName);
			int userId = userBean.getUserId();
			
			bean.setFk_answer_user(userId);
			AnswerDAO ad = new AnswerDAO();
			bean = ad.add(bean);
			int answerId = bean.getAnswerId();
			
			HttpSession hp = request.getSession();
			int questionId = (int) hp.getAttribute("questionId");
			
			Result resultBean = new Result();
			resultBean.setQid(questionId);
			resultBean.setAid(answerId);
			System.out.println("userid = "+userId+"questionId = "+questionId+"answerId" + answerId+"userName"+userName);
			ResultDAO rd = new ResultDAO();
			rd.add(resultBean);
			response.sendRedirect("question.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
