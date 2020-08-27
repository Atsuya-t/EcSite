package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import bean.UserBean;

public class UserDao {

	String url = "jdbc:mysql://localhost/ec_ans?useSSL=false";
	String id = "root";
	String pw = "password";
	Connection cnct = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public UserBean login(String name, String pass) {
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

	public HashMap<String, String> makeUser(String name, String pass) {
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("name", null);
		message.put("complete", null);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnct = DriverManager.getConnection(url, id, pw);

			String sql = "select * from user where login_cd=?";
			pst = cnct.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				message.put("nameErr", "このログイン名は既に使われております。");
			}else if(message.get("name") ==null) {
				sql = "insert into user values(null,?,?)";
				pst = cnct.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, pass);
				pst.executeUpdate();

				message.put("complete", "登録完了しました！");
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

		return message;

	}
}
