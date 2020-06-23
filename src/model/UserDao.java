package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	String url = "jdbc:mysql://localhost/ec_ans?useSSL=false";
	String id = "root";
	String pw = "password";
	Connection cnct = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public UserBean login(String name,String pass){
		UserBean uBean = new UserBean();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);
			String sql = "select * from user where login_cd=? and login_pw=?";
			pst = cnct.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pass);
			rs = pst.executeQuery();



			if (rs.next()) {
				uBean.setUserId(rs.getInt("user_id"));
				uBean.setLoginCd(rs.getString("login_cd"));
				uBean.setLoginPw(rs.getString("login_pw"));
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

		return uBean;

	}
}
