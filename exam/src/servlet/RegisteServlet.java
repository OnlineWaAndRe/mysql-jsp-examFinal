package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;
import util.Judge;

@WebServlet("/RegisteServlet")
public class RegisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String confirmNum = request.getParameter("confirmNum");
		HttpSession hs = request.getSession();
		String num = (String)hs.getAttribute("num");
		if(!Judge.isTrue(userName,userPassword)) {
			response.sendRedirect("logUp.jsp");
		}
		if(!confirmNum.equals(num)) {
			response.sendRedirect("logUp.jsp");
		}
		UserDAO ud = new UserDAO();
		if(!ud.isExist(userName)) {
			response.sendRedirect("logUp.jsp");
		}
		User bean = new User();
		bean.setUserName(userName);
		bean.setUserPassword(userPassword);
		ud.add(bean);
		response.sendRedirect("question.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
