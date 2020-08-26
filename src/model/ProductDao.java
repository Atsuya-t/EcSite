package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDao {

	String url = "jdbc:mysql://localhost/ec_ans?useSSL=false";
	String id = "root";
	String pw = "password";
	Connection cnct = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Statement st =null;

	//全件取得
	public ArrayList<ProductBean> all() {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			//キーワード検索
			System.out.println("全件取得");
			st = cnct.createStatement();
			String sql = "select pro_cd,pro_name,pro_price,stock_no from product";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				ProductBean pBean = new ProductBean();
				pBean.setProCd(rs.getInt("pro_cd"));
				pBean.setProName(rs.getString("pro_name"));
				pBean.setProPrice(rs.getInt("pro_price"));
				pBean.setStock(rs.getInt("stock_no"));
				productList.add(pBean);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

		return productList;

	}

	//キーワード検索
	public ArrayList<ProductBean> keywordSearch(String key) {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			//キーワード検索
			System.out.println("キーワード検索");
			String sql = "select pro_cd,pro_name,pro_price,stock_no from product where pro_name like ?";
			pst = cnct.prepareStatement(sql);
			pst.setString(1, "%" + key + "%");
			rs = pst.executeQuery();

			while (rs.next()) {
				ProductBean pBean = new ProductBean();
				pBean.setProCd(rs.getInt("pro_cd"));
				pBean.setProName(rs.getString("pro_name"));
				pBean.setProPrice(rs.getInt("pro_price"));
				pBean.setStock(rs.getInt("stock_no"));
				productList.add(pBean);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

		return productList;

	}

	//カテゴリー検索
	public ArrayList<ProductBean> categorySearch(int catid) {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			//カテゴリー検索
			System.out.println("カテゴリー検索");
			String sql = "select pro_cd,pro_name,pro_price,stock_no from product where cat_id= ?";
			pst = cnct.prepareStatement(sql);
			pst.setInt(1, catid);
			rs = pst.executeQuery();

			while (rs.next()) {
				ProductBean pBean = new ProductBean();
				pBean.setProCd(rs.getInt("pro_cd"));
				pBean.setProName(rs.getString("pro_name"));
				pBean.setProPrice(rs.getInt("pro_price"));
				pBean.setStock(rs.getInt("stock_no"));
				productList.add(pBean);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

		return productList;

	}

	//両方検索
	public ArrayList<ProductBean> bothSearch(String key, int catId) {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			System.out.println("両方検索");
			//両方検索
			String sql = "select pro_cd,pro_name,pro_price,stock_no from product where pro_name like ? and cat_id= ?";
			pst = cnct.prepareStatement(sql);
			pst.setString(1, "%" + key + "%");
			pst.setInt(2, catId);
			rs = pst.executeQuery();

			while (rs.next()) {
				ProductBean pBean = new ProductBean();
				pBean.setProCd(rs.getInt("pro_cd"));
				pBean.setProName(rs.getString("pro_name"));
				pBean.setProPrice(rs.getInt("pro_price"));
				pBean.setStock(rs.getInt("stock_no"));
				productList.add(pBean);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

		return productList;

	}

	//特定商品情報取得
	public ProductBean getProductDetails(int proCd) {
		ProductBean pBean = new ProductBean();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			//特定商品情報取得
			System.out.println("特定商品情報取得");
			String sql = "select * from product where pro_cd= ?";
			pst = cnct.prepareStatement(sql);
			pst.setInt(1, proCd);
			rs = pst.executeQuery();

			if (rs.next()) {
				pBean.setProCd(rs.getInt("pro_cd"));
				pBean.setProName(rs.getString("pro_name"));
				pBean.setProPrice(rs.getInt("pro_price"));
				pBean.setStock(rs.getInt("stock_no"));
				pBean.setCatId(rs.getInt("cat_id"));
				pBean.setImg(rs.getString("pro_img"));
				pBean.setMsg(rs.getString("pro_msg"));
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

		return pBean;

	}

	//特定在庫情報取得
	public int getStock(int proCd) {
		int stock = 0;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			System.out.println("特定在庫情報取得");
			String sql = "select stock_no from product where pro_cd= ?";
			pst = cnct.prepareStatement(sql);
			pst.setInt(1, proCd);
			rs = pst.executeQuery();

			rs.next();
			stock = rs.getInt("stock_no");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

		return stock;

	}

	//在庫情報の更新
	public void setStock(int stock, int proCd) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			String sql = "update product set stock_no = ? where pro_cd= ?";
			pst = cnct.prepareStatement(sql);
			pst.setInt(1, stock);
			pst.setInt(2, proCd);
			pst.executeUpdate();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}

	}

}
