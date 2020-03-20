package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータを取得
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		//パスワードを暗号化
		password = UserDAO.encryption(password);

		//リクエストパラメータで取得した値を引数にユーザ情報を探す
		UserDataBeans user = UserDAO.findByLoginInfo(email, password);

		if (user == null) {
			//ログイン失敗
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力内容に誤りがあります");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//ログイン成功
		//セッションにユーザ情報をセット
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);
		//Indexページへリダイレクト
		response.sendRedirect("Index");
	}

}
