package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDao;

/**
 * Servlet implementation class MakeAccountServlet
 */
@WebServlet("/makeAccount")
public class MakeAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("loginCd");
		String pass = request.getParameter("loginPw");
		String back = request.getParameter("back");

		if (back != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
			rd.forward(request, response);
			return;
		} else if (name.equals("") || pass.equals("")) {
			request.setAttribute("err1", "ログインコード又はログインパスワードを入力してください。");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/MakeAccount.jsp");
			rd.forward(request, response);
			return;
		}

		HashMap<String, String> message = new HashMap<String, String>();
		UserDao uDao = new UserDao();

		message = uDao.makeUser(name, pass);
		request.setAttribute("err2", message);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/MakeAccount.jsp");
		rd.forward(request, response);
	}

}
