package com.ppi.service;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppi.impl.EmailService;
import com.ppi.impl.LoginIMPL;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginIMPL dao = new LoginIMPL();
		String username = request.getParameter("username");
		String token = dao.forgotRequest(username);
		String link = "http://localhost:8080/PPI_PORTAL/account/forgot_password_response.jsp?token="+token;
		String to = dao.getEmailByUsername(username);
		EmailService.sendEmail("NCU : Forgot Password", link, to);
		URL url = new URL(link);
	    System.out.println(url);
		response.sendRedirect("login.jsp");
		return;
	}

}
