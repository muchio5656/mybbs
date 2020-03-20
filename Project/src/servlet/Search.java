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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");

		if (userInfo == null) {
			// ログインセッションがない場合、ログイン画面にリダイレクトさせる
			response.sendRedirect("Login");
			return;
		}
		String searchWord = request.getParameter("search_word");
		if (searchWord == "") {
			request.setAttribute("errMsg", "検索ワードを入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 新たに検索されたキーワードをセット
		request.setAttribute("searchWord", searchWord);

		//検索ワードをもとに、スレッドタイトルから部分一致検索

		List<ThreadsDataBeans> searchThreads = ThreadsDAO.wordSearchThreads(searchWord);
		if (searchThreads == null || searchThreads.size() == 0) {
			request.setAttribute("errMsg1", "スレッドタイトルは見つかりませんでした");
		}
		request.setAttribute("searchThreads", searchThreads);

		List<PostsDataBeans> searchPosts = PostsDAO.wordSearchPost(searchWord);
		if (searchPosts == null || searchPosts.size() == 0) {
			request.setAttribute("errMsg2", "レスは見つかりませんでした");
		}
		request.setAttribute("searchPosts", searchPosts);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
