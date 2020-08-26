package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoryBean;
import model.CategoryDao;
import model.ProductBean;
import model.ProductDao;

@WebServlet("/search")

public class SearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String productDetails = (String) request.getParameter("form1");
		System.out.println("productDetails" + productDetails);

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
		ProductBean pBean = new ProductBean();
		ProductDao pDao = new ProductDao();
		CategoryDao cDao = new CategoryDao();

		System.out.println("ProductDetails.jspに行くよ");
		//productDetailsをもとに商品情報を取得
		pBean = pDao.getProductDetails(Integer.parseInt(productDetails));
		String catName = cDao.getCategoryName(pBean.getCatId());
		//sessionオブジェクトに格納
		session.setAttribute("productDetails", pBean);
		//カテゴリー名requestオブジェクトに格納
		request.setAttribute("catName", catName);

		//商品詳細画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/ProductDetails.jsp");
		rd.forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SearchServlet開始");

		request.setCharacterEncoding("UTF-8");
		//LoginServletから呼び出されたかを判定するもの
		String loginFlg = (String) request.getAttribute("loginFlg");
		String productDetails = (String) request.getParameter("Details");
		System.out.println("productDetails" + productDetails);
		ProductDao pDao = new ProductDao();
		CategoryDao cDao = new CategoryDao();

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

		if (loginFlg != null) {
			//LoginServlet.javaから呼び出された時
			System.out.println("LoginServlet.javaから来たよ");
			//カテゴリー情報取得
			ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
			categoryList = cDao.getCategory();

			//カテゴリー情報をsessionオブジェクトに格納
			session.setAttribute("category", categoryList);

			//検索画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/Search.jsp");
			rd.forward(request, response);
			return;

		} else {
			//Search.jspから呼び出された時
			System.out.println("Search.jspから来たよ");
			request.setCharacterEncoding("UTF-8");
			//検索情報取得
			String key = request.getParameter("keyWord");
			String sCat=request.getParameter("categoryId");
			int cat = Integer.parseInt(sCat);

			System.out.println("keyword:" + key);
			System.out.println("categoryid:" + cat);

			//検索結果格納
			ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

			//入力チェック
			if (key.equals("") && cat == 0) {
				//全件取得
				productList = pDao.all();
			} else {

				if (cat == 0) {
					//キーワード検索
					productList = pDao.keywordSearch(key);
				} else if (key.equals("")) {
					//カテゴリー検索
					productList = pDao.categorySearch(cat);
				} else {
					//両方検索
					productList = pDao.bothSearch(key, cat);
				}

				if (productList.size() == 0) {
					request.setAttribute("err", "0件です。");
					productList = null;
				}

			}
			HashMap<String,String> searchMap = new HashMap<String,String>();
			searchMap.put("key",key);
			searchMap.put("cat",sCat);
			session.setAttribute("search", searchMap);
			session.setAttribute("product", productList);
			//検索画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/Search.jsp");
			rd.forward(request, response);
			return;

		}
	}

}
