package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartBean;
import model.MeisaiDao;
import model.ProductDao;
import model.UserBean;

@WebServlet("/confirmation")
public class ConfirmationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Confirmation開始");

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

		//カート情報
		ArrayList<CartBean> cartList = (ArrayList<CartBean>) session.getAttribute("Cart");
		//ユーザー情報
		UserBean uBean = (UserBean) session.getAttribute("user");
		//Daoの呼び出し
		ProductDao pDao = new ProductDao();
		MeisaiDao mDao = new MeisaiDao();

		//カートの中の商品分繰り返す。
		for (int i = 0; i < cartList.size(); i++) {
			CartBean cBean = cartList.get(i);
			//特定の商品の在庫を取ってくる
			int stock = pDao.getStock(cBean.getProCd());
			//購入分在庫を減らしてテーブルを更新する
			pDao.setStock(stock - cBean.getQuantity(), cBean.getProCd());
			//明細テーブルを更新する。
			mDao.setStatement(uBean.getUserId(), cBean.getProCd(), cBean.getSubTotal());
		}
		//カートの中身を破棄
		cartList.clear();

		session.setAttribute("Cart", cartList);

		//完了画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Complete.jsp");
		rd.forward(request, response);

	}

}
