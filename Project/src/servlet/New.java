package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class New
 */
@WebServlet("/New")
public class New extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String name = request.getParameter("name");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		boolean check = UserDAO.idCheck(userId);

		//値が間違ってる場合エラーメッセージをセットして新規登録ページへフォワード
		if (userId.equals("") || password.equals("") || name.equals("")
				|| !(password.equals(password2)) || check) {
			request.setAttribute("errMsg", "入力された値は正しくありません");

			//入力された値をセット
			request.setAttribute("name", name);
			request.setAttribute("userId", userId);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//パスワードを暗号化
		password = UserDAO.encryption(password);
		// 新規データの登録
		UserDAO.newUser(userId, password, name);

		request.setAttribute("doneMsg", "新規登録完了");


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);;

	}

}
