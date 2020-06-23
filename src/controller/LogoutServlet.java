package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッション終了
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		//ログイン画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
		rd.forward(request, response);
	}
}
