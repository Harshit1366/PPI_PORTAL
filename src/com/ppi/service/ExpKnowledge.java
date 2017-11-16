package com.ppi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ExpKnowledge
 */
@WebServlet("/ExpKnowledge")
public class ExpKnowledge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpKnowledge() {
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
		HttpSession sess=request.getSession();  
		
        String rno= request.getParameter("rno");
        
        String name= request.getParameter("name");
        
        String data= request.getParameter("data");

        String logic= request.getParameter("logic");
      
        String cao= request.getParameter("cao");
    
        String dbms= request.getParameter("dbms");

        String os= request.getParameter("os");
        
        String cn= request.getParameter("cn");
  
        String app= request.getParameter("app");
        
        List<String> know=new ArrayList<String>();
        
        know.add(rno);
        know.add(name);
        know.add(data);
        know.add(logic);
        know.add(cao);
        know.add(dbms);
        know.add(os);
        know.add(cn);
        know.add(app);
        
        sess.setAttribute("knowledge",know);
        sess.setAttribute("studname", name);
        sess.setAttribute("studroll", rno);
        
        response.sendRedirect("expert/expertSkills.jsp");

	}

}
