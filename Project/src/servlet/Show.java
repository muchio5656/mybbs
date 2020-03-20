package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.PostsDataBeans;
import beans.ThreadsDataBeans;
import beans.UserDataBeans;
import dao.PostsDAO;
import dao.ThreadsDAO;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");

		if (userInfo == null) {
			// ログインセッションがない場合、ログイン画面にリダイレクトさせる
			response.sendRedirect("Login");
			return;
		}

		String id = request.getParameter("id");
		int i = Integer.parseInt(id);

		//スレッド情報取得
		List<ThreadsDataBeans> thread = ThreadsDAO.showThread(i);
		// リクエストスコープにスレッド情報をセット
		request.setAttribute("thread", thread);

		//レス一覧取得
		List<PostsDataBeans> posts = PostsDAO.showPosts(i);

		// リクエストスコープにレス１覧情報をセット
		request.setAttribute("posts", posts);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/show.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		String userName = userInfo.getName();
		int userId = userInfo.getId();


		String id = request.getParameter("id");
		String message = request.getParameter("message");
		String title = request.getParameter("title");

		int i = Integer.parseInt(id);
		//新規レス書き込み
		PostsDAO.newPost(message, userName, i,userId,title);

		//スレッド情報取得
		List<ThreadsDataBeans> thread = ThreadsDAO.showThread(i);
		// リクエストスコープにスレッド情報をセット
		request.setAttribute("thread", thread);

		//レス一覧取得
		List<PostsDataBeans> posts = PostsDAO.showPosts(i);
		// リクエストスコープにレス１覧情報をセット
		request.setAttribute("posts", posts);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/show.jsp");
		dispatcher.forward(request, response);
	}

}
