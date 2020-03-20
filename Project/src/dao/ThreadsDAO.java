package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.CategoryDataBeans;
import beans.ThreadsDataBeans;
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

	public static int createThread(String userName, String title, int tID, int userId) {

		Connection conn = null;
		int newId = 0;

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO threads" +
					"(user_name,title,threads_category_id,user_id,create_date,update_date)" +
					"VALUES(?,?,?,?,NOW(),NOW())";

			String sql2 = "SELECT * FROM threads ORDER BY id DESC LIMIT 1";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userName);
			pStmt.setString(2, title);
			pStmt.setInt(3, tID);
			pStmt.setInt(4, userId);
			pStmt.executeUpdate();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);

			if (!rs.next()) {
			}
			newId = rs.getInt("id");

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
		return newId;
	}

	public static List<ThreadsDataBeans> showThread(int tCID) {
		Connection conn = null;
		List<ThreadsDataBeans> threadInfo = new ArrayList<ThreadsDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT t2.id,c.category_name,t1.category_id,t2.title,t2.create_date" +
					" FROM thread_category t1 JOIN threads t2 ON " +
					"  t1.threads_category_id = t2.threads_category_id " +
					"  JOIN category c ON c.id = t1.category_id WHERE t2.id = ?";

			//SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, tCID);
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("t2.id");
				String categoryName = rs.getString("c.category_name");
				int categoryId = rs.getInt("t1.category_id");
				String title = rs.getString("t2.title");
				Date createDate = rs.getDate("t2.create_date");

				ThreadsDataBeans thread = new ThreadsDataBeans(id, categoryName, categoryId, title, createDate);

				threadInfo.add(thread);

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
		return threadInfo;
	}

	public static List<ThreadsDataBeans> threadsList() {
		Connection conn = null;
		List<ThreadsDataBeans> threads = new ArrayList<ThreadsDataBeans>();
		String message = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = " SELECT * FROM threads ORDER BY create_date DESC";

			//SELECTを実行し、結果表を取得
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String userName = rs.getString("user_name");
				Date createDate = rs.getTimestamp("create_date");
				int userId = rs.getInt("user_id");

				ThreadsDataBeans thread = new ThreadsDataBeans(id, title, userName, createDate, userId);

				threads.add(thread);

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
		return threads;
	}

	public static List<ThreadsDataBeans> userThreads(int userId) {
		Connection conn = null;
		List<ThreadsDataBeans> threads = new ArrayList<ThreadsDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = " SELECT * FROM threads WHERE user_id = ? ORDER BY create_date DESC";

			//SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String userName = rs.getString("user_name");
				Date createDate = rs.getTimestamp("create_date");
				int userId2 = rs.getInt("user_id");

				ThreadsDataBeans thread = new ThreadsDataBeans(id, title, userName, createDate, userId2);

				threads.add(thread);

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
		return threads;
	}

	public static List<ThreadsDataBeans> wordSearchThreads(String searchWord) {
		// キーワード検索
		Connection conn = null;
		List<ThreadsDataBeans> searchList = new ArrayList<ThreadsDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM threads WHERE title like ? ORDER BY create_date DESC";

			//SELECTを実行し、アイテム全データ取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + searchWord + "%");
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String name = rs.getString("user_name");
				String title = rs.getString("title");
				Date createDate = rs.getTimestamp("create_date");

				ThreadsDataBeans search = new ThreadsDataBeans(id, title, name, createDate, userId);

				searchList.add(search);

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
		return searchList;
	}

	public static List<ThreadsDataBeans> categoryThreads(int categoryId) {
		Connection conn = null;
		List<ThreadsDataBeans> categorythreads = new ArrayList<ThreadsDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT t.id,t.title,t.user_name,t.create_date,t.user_id " +
					"FROM thread_category th JOIN category c  ON th.category_id = c.id JOIN " +
					"threads t ON th.threads_category_id = t.threads_category_id WHERE c.id = ? " +
					"ORDER BY t.create_date DESC";

			//SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, categoryId);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String userName = rs.getString("user_name");
				Date createDate = rs.getTimestamp("create_date");
				int userId2 = rs.getInt("user_id");

				ThreadsDataBeans thread = new ThreadsDataBeans(id, title, userName, createDate, userId2);

				categorythreads.add(thread);

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
		return categorythreads;
	}

	public static String findCategory(int categoryId) {
		Connection conn = null;
		String categoryName = "";
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM category WHERE id = ?";

			//SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, categoryId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			categoryName = rs.getString("category_name");

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
		return categoryName;
	}

	public static void threadDelete(String threadId) {
		Connection conn = null;

		try {
			conn = DBManager.getConnection();

			String sql = "DELETE FROM threads WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, threadId);
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
}
