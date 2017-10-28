package com.ppi.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppi.impl.LoginObjs;
import com.ppi.model.Login;

/**
 * Servlet implementation class addExpert
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
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
		//doGet(request, response);
		
		Login login=new Login();
		
		login.setUsername(request.getParameter("user"));
		login.setPassword(request.getParameter("pass"));
		login.setRole(request.getParameter("role"));
		login.setStatus(request.getParameter("status"));
		
		LoginObjs l=new LoginObjs();
		l.saveNewLogin(login);
		
		response.sendRedirect("admin/admin_home.jsp");
		
	}

}
