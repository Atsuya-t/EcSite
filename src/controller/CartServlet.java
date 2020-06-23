package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CartServlet開始");

		//セッション継続
		HttpSession session = request.getSession(false);

		//セッション継続確認
		if (session == null) {
			System.out.println("セッションがきれました。");
			//ログイン画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
			rd.forward(request, response);
			return;
		}

		//確認画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Confirmation.jsp");
		rd.forward(request, response);

	}

}
