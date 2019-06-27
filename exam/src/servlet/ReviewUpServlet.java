package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReviewDAO;
import dao.UserDAO;
import entity.Review;

@WebServlet("/ReviewUpServlet")
public class ReviewUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		String userName = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!= null){
			for(Cookie c:cookies){
				if(c.getName().equals("userNameCookie")){
					userName = c.getValue();
				}
			}
		}
		int selfId = new UserDAO().get(userName).getUserId();
		HttpSession hs = request.getSession();
		int fk_review_user = (int)hs.getAttribute("fk_review_user");
		String reviewContent = request.getParameter("reviewContent");
		System.out.println(reviewContent+"===========");
		if(reviewContent.length() <= 0 || reviewContent == null) {
			request.setAttribute("fk_review_user", fk_review_user);
			request.getRequestDispatcher("temp_oops.jsp");
		}
		Review bean = new Review();
		bean.setSelfId(selfId);
		bean.setFk_review_user(fk_review_user);
		bean.setReviewContent(reviewContent);
		new ReviewDAO().add(bean);
		response.sendRedirect("question.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
