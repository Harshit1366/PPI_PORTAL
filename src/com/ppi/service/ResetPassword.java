package com.ppi.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppi.impl.LoginIMPL;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
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
		PrintWriter out = response.getWriter();
		LoginIMPL dao = new LoginIMPL();
		String newPassword = request.getParameter("password");
		String newPasswordAgain = request.getParameter("password_again");
		if(newPassword.isEmpty() || newPasswordAgain.isEmpty()){
			out.println(returnScript("Empty fields found.", "login.jsp"));
			return;
		}
		if(!newPassword.equals(newPasswordAgain)){
			out.println(returnScript("Passwords do not match.", "login.jsp"));
			return;
		}else{
			if(dao.finishForgetPassword(request.getParameter("id"), newPassword)){
				out.println(returnScript("Password Reset Successfull! Login again now.", "login.jsp"));
				return;
			}else{
				out.println(returnScript("Password cannot be changed, try again later.", "login.jsp"));
				return;
			}
		
		}
		
		
	}
	
	public String returnScript(String msg, String url) {
		return "<script type=\"text/javascript\">\n"
				+ "alert('"+msg+"');\n" + "location='"+url+"';\n"
				+ "</script>";
	}
}
