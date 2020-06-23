package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserBean;
import model.UserDao;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet開始");

		request.setCharacterEncoding("UTF-8");
		//ログイン情報取得
		String name = request.getParameter("loginCd");
		String pass = request.getParameter("loginPw");

		System.out.println("logincd:" + name);
		System.out.println("loginpw:" + pass);

		//入力チェック
		if (name.equals("") || pass.equals("")) {
			System.out.println("値が入力されていませんでした。");
			//エラーメッセージをセット
			//エラーメッセージをセット
			request.setAttribute("err", "入力してください。");
			//urlの場所に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
			rd.forward(request, response);
			return;
		} else {

			//ユーザー情報取得
			UserDao uDao = new UserDao();
			UserBean uBean = uDao.login(name, pass);

			//ログイン判定
			if (uBean.getUserId() != 0) {
				System.out.println("ログイン成功です。");

				//session開始
				HttpSession session = request.getSession(true);

				//ユーザー情報をsessionオブジェクトに格納
				session.setAttribute("user", uBean);
				//LoginServletから呼び出されたかを判定するもの
				request.setAttribute("loginFlg", "login");
				RequestDispatcher rd = request.getRequestDispatcher("/search");
				rd.forward(request, response);
				return;
				//				response.sendRedirect("http://localhost:8080/Ecsite/search");
				//				return;
			} else {
				System.out.println("ログインに失敗しました。");
				//エラーメッセージをセット
				request.setAttribute("err", "入力した値が間違っています。");
				//urlの場所に遷移
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
				rd.forward(request, response);
				return;
			}
		}

	}

}
