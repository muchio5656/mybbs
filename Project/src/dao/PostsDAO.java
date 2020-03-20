package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.PostsDataBeans;
import database.DBManager;

public class PostsDAO {

	public static List<PostsDataBeans> showPosts(int TID) {
		Connection conn = null;
		List<PostsDataBeans> postsList = new ArrayList<PostsDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT p.user_id,p.id,p.message,p.user_name,t.title,p.create_date," +
					"t.id FROM posts p INNER JOIN threads t " +
					"ON p.thread_id = t.id WHERE t.id = ?";

			//SELECTを実行し、結果表を取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, TID);
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を
			// Userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				int postId = rs.getInt("p.id");
				String message = rs.getString("p.message");
				String userName = rs.getString("p.user_name");
				String title = rs.getString("t.title");
				Date createDate = rs.getTimestamp("p.create_date");
				int userId = rs.getInt("p.user_id");
				int threadId = rs.getInt("t.id");

				PostsDataBeans post = new PostsDataBeans(postId, message, userName, title, createDate,userId,threadId);

				postsList.add(post);

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
		return postsList;
	}



	public static void newPost(String message, String userName, int tCID,int userId,String title) {
		Connection conn = null;

		try {
			conn = DBManager.getConnection();

			String sql = "INSERT INTO posts" +
					"(message,user_name,thread_id,user_id,title,create_date,update_date)" +
					"VALUES(?,?,?,?,?,NOW(),NOW())";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, message);
			pStmt.setString(2, userName);
			pStmt.setInt(3, tCID);
			pStmt.setInt(4, userId);
			pStmt.setString(5, title);

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
	public static List<PostsDataBeans> wordSearchPost(String searchWord) {
		// キーワード検索
		Connection conn = null;
		List<PostsDataBeans> searchList = new ArrayList<PostsDataBeans>();
		try {
			// データベースへ接続
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM posts WHERE message like ? ORDER BY create_date DESC";

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
				String message = rs.getString("message");
				int threadId = rs.getInt("thread_id");

				PostsDataBeans search = new PostsDataBeans(id,  message,name, title, createDate,userId,threadId);

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

}
