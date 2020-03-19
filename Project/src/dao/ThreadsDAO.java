package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.CategoryDataBeans;
import beans.PostsDataBeans;
import beans.ThreadsDataBeans;
import beans.UserDataBeans;
import database.DBManager;

public class ThreadsDAO {

	public static List<CategoryDataBeans> findCategory() {
		Connection conn = null;
		List<CategoryDataBeans> categories = new ArrayList<CategoryDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM category ";

			//SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("category_name");
				CategoryDataBeans category = new CategoryDataBeans(id, name);

				categories.add(category);

			}
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
		return categories;
	}

	public static int createTC(String[] categoryList) {
		Connection conn = null;
		String[] categories = categoryList;
		int TCID = 0;

		try {
			conn = DBManager.getConnection();
			//最新のthread_idを取得
			String sql1 = "SELECT * FROM thread_category ORDER BY threads_category_id DESC LIMIT 1";

			String sql2 = "INSERT INTO thread_category" +
					"(threads_category_id,category_id)VALUES(?,?)";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql1);

			if (!rs.next()) {
			}
			//最新のthread_idに+ 1
			TCID = rs.getInt("threads_category_id");
			TCID += 1;

			//選択されたカテゴリID分登録
			for (int i = 0; categories.length > i; i++) {
				PreparedStatement pStmt = conn.prepareStatement(sql2);
				pStmt.setInt(1, TCID);
				pStmt.setString(2, categories[i]);
				pStmt.executeUpdate();
			}
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
		return TCID;
	}

	public static void createThread(String userName, String title, int tID) {

		Connection conn = null;

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO threads" +
					"(user_name,title,threads_category_id,create_date,update_date)" +
					"VALUES(?,?,?,NOW(),NOW())";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userName);
			pStmt.setString(2, title);
			pStmt.setInt(3, tID);
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

	public static void newPost(String message, String userName, int tCID) {
		Connection conn = null;

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO posts" +
					"(message,user_name,thread_id,create_date,update_date)" +
					"VALUES(?,?,?,NOW(),NOW())";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, message);
			pStmt.setString(2, userName);
			pStmt.setInt(3, tCID);
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

	public static List<ThreadsDataBeans> showThread(int TID) {
		Connection conn = null;
		List<UserDataBeans> userList = new ArrayList<UserDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM posts WHERE thread_id = ? ";

			//SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, TID);
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				String message = rs.getString("message");
				String userName = rs.getString("user_name");
				String threadid = rs.getString("thread_id");
				Date createDate = rs.getDate("create_date");

				PostsDataBeans user = new PostsDataBeans(id, message, userName, threadid, createDate);

				userList.add(user);

			}
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
		return userList;
	}

}
