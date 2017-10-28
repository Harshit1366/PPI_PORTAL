package com.ppi.service;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");   
		//PrintWriter out=response.getWriter();
		//out.close(); 
		//request.logout();
		
		   //out.print("You are successfully logged out!");  
        
		  //request.getRequestDispatcher("login/login.jsp").include(request, response);  
	         
//         HttpSession sess=request.getSession(false);  
//         sess.invalidate(); 
        
		//String role= (String)request.getSession(false).getAttribute("role");
		
		//if(role.equals("admin") || role.equals("user")){
		
		
       // request.logout();
			 if (request.getSession(false).getAttribute("s_id") == null) {
	             response.sendRedirect("login.jsp");
	             return;
	         } else {
	             
	        	 //userObjs.deleteS_ID(request.getSession(false).getAttribute("s_id").toString());
	             request.getSession(false).invalidate();
	             response.sendRedirect("login.jsp");
	             return;
	            
	            
	         }
			
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
	}

}
