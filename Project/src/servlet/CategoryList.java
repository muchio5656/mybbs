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

import beans.ThreadsDataBeans;
import beans.UserDataBeans;
import dao.ThreadsDAO;

/**
 * Servlet implementation class CategoryList
 */
@WebServlet("/CategoryList")
public class CategoryList extends HttpServlet {
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

		//ゲットパラメータでcategoryIdを取得
		String id = request.getParameter("id");
		int categoryId = Integer.parseInt(id);

		//カテゴリー名を取得
		String categoryName =ThreadsDAO.findCategory(categoryId);
		request.setAttribute("categoryName", categoryName);

		//categoryIdを引数にスレッド情報取得
		List<ThreadsDataBeans> categoryThreads = ThreadsDAO.categoryThreads(categoryId);
		if (categoryThreads == null || categoryThreads.size() == 0) {
			request.setAttribute("errMsg", "に関する投稿はまだありません");
		}
		// リクエストスコープにスレッド情報をセット
		request.setAttribute("categoryThreads", categoryThreads);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categorylist.jsp");
		dispatcher.forward(request, response);
	}

}
