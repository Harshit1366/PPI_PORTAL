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
 * Servlet implementation class ExpSkills
 */
@WebServlet("/ExpSkills")
public class ExpSkills extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpSkills() {
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
		
        String team= request.getParameter("team");
        
        String enth= request.getParameter("enth");

        String conf= request.getParameter("conf");

        String clean= request.getParameter("clean");

        String oral= request.getParameter("oral");
        
        String lang= request.getParameter("lang");

        String prob= request.getParameter("prob");
 
        String skill= request.getParameter("skill");

        List<String> skills=new ArrayList<String>();
        
        skills.add(team);
        skills.add(enth);
        skills.add(conf);
        skills.add(clean);
        skills.add(oral);
        skills.add(lang);
        skills.add(prob);
        skills.add(skill);
        
        sess.setAttribute("skills",skills);
        
        response.sendRedirect("expert/expertRemarks.jsp");
	}

}
