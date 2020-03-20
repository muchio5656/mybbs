package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.DatatypeConverter;

import beans.UserDataBeans;
import database.DBManager;

public class UserDAO {

	//ログイン
	public static UserDataBeans findByLoginInfo(String email, String password) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE email = ? and password = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String loginName = rs.getString("name");

			return new UserDataBeans(id, loginName);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	//MD5　暗号化
	public static String encryption(String password) {
		String source = password;
		Charset charset = StandardCharsets.UTF_8;
		String algorithm = "MD5";
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String result = DatatypeConverter.printHexBinary(bytes);
		return result;
	}

	//user id重複チェック
	public static boolean emailCheck(String email) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE email = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return false;
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
	}

	public static void newUser(String email, String password, String name) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user (name,email,password,create_date,update_date) VALUES (?,?,?,NOW(),NOW())";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, email );
			pStmt.setString(3, password);

			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static UserDataBeans userDetail(int i) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
