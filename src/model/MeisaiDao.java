package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MeisaiDao {
	String url = "jdbc:mysql://localhost/ec_ans?useSSL=false";
	String id = "root";
	String pw = "password";
	Connection cnct = null;
	Statement st = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	//明細テーブル更新
	public void setStatement(int userId, int proCd, int subTotal) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);

			String sql = "insert into meisai values(null,?,?,now(),?)";
			pst = cnct.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, proCd);
			pst.setInt(3, subTotal);
			pst.executeUpdate();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
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
	}

}
