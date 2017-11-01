package com.ppi.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.ppi.constants.Redirect;
import com.ppi.database.ConnectionFactory;
import com.ppi.impl.LoginIMPL;
import com.ppi.model.Bcrypt;
import com.ppi.model.Login;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String oldPassword = request.getParameter("oldPassword");
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");
		
		boolean check1 = newPassword1.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$");
		boolean check2 = newPassword2.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$");
		
		//System.out.println(oldPassword+ " "+newPassword1 +" "+ newPassword2);
		HttpSession sess = request.getSession(false);

		if (null != sess) {
			String sid = (String) request.getSession(false).getAttribute("sid");
			if (sid == null) {
				response.sendRedirect("index.jsp");
				return;
			}

		}
		Connection connection = null;
		try {
			if (oldPassword.isEmpty() || newPassword1.isEmpty() || newPassword2.isEmpty()) {
				System.out.println("1");
				throw new IllegalArgumentException();
			}
			if (newPassword1.length() > 15 || newPassword1.length() < 7) {
				System.out.println("2");
				throw new IllegalArgumentException();
			}
			if(!(check1 && check2)){
				throw new IllegalArgumentException();
			}
			if (!newPassword1.equals(newPassword2)) {
				System.out.println("3");
				throw new IllegalArgumentException();
			}
			LoginIMPL dao = new LoginIMPL();
			connection = ConnectionFactory.getConnection();
			String sid = (String) request.getSession(false).getAttribute("sid");
			String userid = dao.getUsernameBySessionID(sid);
			Login login = dao.getLogin(userid);
			if (null == login) {
				System.out.println(4);
				throw new NullPointerException();
			}
			//System.out.println(login);
			if (!login.getPassword().equals(Bcrypt.hashpw(oldPassword, login.getSalt()))) {
				//System.out.println(5);
				throw new IllegalArgumentException();
			}
			PreparedStatement preparedStatement1 = connection
					.prepareStatement("update login set password=?, salt=? where username=?");
			String salt = Bcrypt.gensalt();
			preparedStatement1.setString(1, Bcrypt.hashpw(newPassword1, salt));
			preparedStatement1.setString(2, salt);
			preparedStatement1.setString(3, userid);
			if (preparedStatement1.executeUpdate() > 0) {
				response.sendRedirect("account/change_password.jsp?change=success");
				return;
			}

		} catch (IllegalArgumentException e) {
			response.sendRedirect("account/change_password.jsp?change=illegal");

		} catch (SQLException e) {
			response.sendRedirect("account/change_password.jsp?change=dataloss");
			e.printStackTrace();
		} catch (NullPointerException n) {
			response.sendRedirect("account/change_password.jsp?change=nosuchlogin");
			n.printStackTrace();
		} catch (Exception e) {
			response.sendRedirect("account/change_password.jsp?change=error");
			e.printStackTrace();
		}finally {
			ConnectionFactory.close(connection);
		}

	}

}
