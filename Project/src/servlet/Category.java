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

import beans.CategoryDataBeans;
import beans.UserDataBeans;
import dao.ThreadsDAO;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
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
		//カテゴリーデータ取得
		List<CategoryDataBeans> categories = ThreadsDAO.findCategory();

		// リクエストスコープにカテゴリーデータをセット
		request.setAttribute("categories", categories);
		//カテゴリー一覧ページへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/category.jsp");
		dispatcher.forward(request, response);
	}

}
