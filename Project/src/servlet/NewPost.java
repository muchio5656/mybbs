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
import beans.ThreadsDataBeans;
import beans.UserDataBeans;
import dao.ThreadsDAO;

/**
 * Servlet implementation class NewPost
 */
@WebServlet("/NewPost")
public class NewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewPost() {
		super();
		// TODO Auto-generated constructor stub
	}

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

		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("categories", categories);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newpost.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		//カテゴライズ処理
		String[] categoryList = request.getParameterValues("category_list");
		if(categoryList == null) {
			request.setAttribute("errMsg", "カテゴリーを選んでください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newpost.jsp");
			dispatcher.forward(request, response);
		}
		//スレッドカテゴリーID制作
		int TCID = ThreadsDAO.createTC(categoryList);

		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");

		String userName = userInfo.getName();
		String title = request.getParameter("title");
		String message = request.getParameter("message");

		//スレッド制作
		ThreadsDAO.createThread(userName,title,TCID);

		//1レス目の書き込みとShowへのフォワード

		 ThreadsDAO.newPost(message,userName,TCID);

		//スレ詳細取得
		List<ThreadsDataBeans> thread = ThreadsDAO.showThread(TCID);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/show.jsp");
		dispatcher.forward(request, response);
	}

}
