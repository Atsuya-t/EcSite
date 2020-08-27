package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.CategoryBean;

public class CategoryDao {
	String url = "jdbc:mysql://localhost/ec_ans?useSSL=false";
	String id = "root";
	String pw = "password";
	Connection cnct = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	//カテゴリー情報取得
	public ArrayList<CategoryBean> getCategory() {
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			st = cnct.createStatement();
			rs = st.executeQuery("SELECT * from category");

			while (rs.next()) {
				CategoryBean cBean = new CategoryBean();
				cBean.setCategoryId(rs.getInt("cat_id"));
				cBean.setCategoryName(rs.getString("cat_name"));
				System.out.println("cat_id:" + cBean.getCategoryId() + "、cat_name:" + cBean.getCategoryName() + "をセット");
				categoryList.add(cBean);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}
		return categoryList;
	}

	//特定カテゴリー名取得
	public String getCategoryName(int catId) {
		String catName = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);

			String sql = "SELECT * from category where cat_id=?";
			pst = cnct.prepareStatement(sql);
			pst.setInt(1, catId);
			rs = pst.executeQuery();

			rs.next();
			catName = rs.getString("cat_name");

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (cnct != null)
					cnct.close();
			} catch (Exception ex) {
			}
		}
		return catName;
	}

}
