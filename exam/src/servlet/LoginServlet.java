package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;
import util.Judge;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		if(!Judge.isTrue(userName, userPassword)) {
			response.sendRedirect("out.jsp");
		}
		Cookie userNameCookie = new Cookie("userNameCookie", userName);
		Cookie userPasswordCookie = new Cookie("userPasswordCookie", userPassword);
		response.addCookie(userNameCookie);
		response.addCookie(userPasswordCookie);
		UserDAO ud = new UserDAO();
		User u = ud.get(userName);
		if(!Judge.isTrue(userName, userPassword)) {
			request.getRequestDispatcher("index.jsp");
		}else if(u == null || !userPassword.equals(u.getUserPassword())){
			request.getRequestDispatcher("logUp.jsp");
		}else {
			response.sendRedirect("question.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
