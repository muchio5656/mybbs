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
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
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
		//削除確認用のスレッドデータを取得
		List<ThreadsDataBeans> threads = ThreadsDAO.showThread(i);
		// リクエストスコープにスレッド情報をセット
		request.setAttribute("threads", threads);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDataBeans userInfo = (UserDataBeans) session.getAttribute("userInfo");
		int userId = userInfo.getId();

		//threadsIdを引数にスレッド削除
		String id = request.getParameter("id");
		ThreadsDAO.threadDelete(id);
		//アラートメッセージをセット
		request.setAttribute("doneMsg", "削除しました");

		//ユーザーページ表示用データ取得
		List<ThreadsDataBeans> threads = ThreadsDAO.userThreads(userId);
		request.setAttribute("threads", threads);
		if (threads == null || threads.size() == 0) {
			request.setAttribute("errMsg", "が建てたスレッドは存在しません");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user.jsp");
		dispatcher.forward(request, response);
	}

}
