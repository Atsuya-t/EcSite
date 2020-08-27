package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.ProductBean;
import model.CalculationLogic;

@WebServlet("/productDetails")
public class ProductDetailsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ProductDetailsServlet開始");

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

		request.setCharacterEncoding("UTF-8");
		//購入数量
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		//購入商品情報
		ProductBean pBean = (ProductBean) session.getAttribute("productDetails");

		//カート情報取得
		ArrayList<CartBean> cartList = (ArrayList<CartBean>) session.getAttribute("Cart");
		CartBean cBean = new CartBean();

		//nullチェック
		if (cartList == null) {
			cartList = new ArrayList<CartBean>();
		}

		//カートに同一商品があるかをみる
		List<Integer> idList;
		idList = cartList.stream().map(CartBean -> CartBean.getProCd()).collect(Collectors.toList());
		int index = idList.indexOf(pBean.getProCd());

		if (index == -1) {
			//同一商品がなかった場合
			//商品をカートに追加
			cBean.setProCd(pBean.getProCd());
			cBean.setProName(pBean.getProName());
			cBean.setProPrice(pBean.getProPrice());
			cBean.setQuantity(quantity);
			cBean.setStock(pBean.getStock());
			cBean.setSubTotal(pBean.getProPrice() * quantity);
			cartList.add(cBean);
		}else {
			//同一商品があった場合
			cBean = cartList.get(index);
			cBean.setQuantity(cBean.getQuantity() + quantity);
			cBean.setSubTotal(cBean.getQuantity() * cBean.getProPrice());
			cartList.set(index, cBean);
		}

		//計算メソッドを呼ぶ
		CalculationLogic calcLogic = new CalculationLogic();

		//合計金額算出
		int total = calcLogic.totalCalc(cartList);
		//合計金額の消費税算出
		int tax = calcLogic.taxCalc(total);

		//カート画面と確認画面で使うためセット
		session.setAttribute("Cart", cartList);
		session.setAttribute("total", total);
		session.setAttribute("tax", tax);

		//リダイレクトでカート画面に遷移する
		response.sendRedirect("http://localhost:8080/Ecsite/jsp/Cart.jsp");

	}
}
