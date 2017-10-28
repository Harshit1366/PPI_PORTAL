package com.ppi.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppi.impl.ExpRecordsIMPL;

/**
 * Servlet implementation class EditDetails
 */
@WebServlet("/EditExpDetails")
public class EditExpDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditExpDetails() {
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
		
	        String rno= request.getParameter("rno");
	       
	        
	        String data= request.getParameter("data");

	        String logic= request.getParameter("logic");
	      
	        String cao= request.getParameter("cao");
	    
	        String dbms= request.getParameter("dbms");

	        String os= request.getParameter("os");
	        
	        String cn= request.getParameter("cn");
	  
	        String app= request.getParameter("app");

	        
	        String team= request.getParameter("team");
	        
	        String enth= request.getParameter("enth");

	        String conf= request.getParameter("conf");

	        String clean= request.getParameter("clean");

	        String oral= request.getParameter("oral");
	        
	        String lang= request.getParameter("lang");

	        String prob= request.getParameter("prob");
	 
	        String skill= request.getParameter("skill");

	        
	        String remarks= request.getParameter("remarks");
	    

	    try{
	   
	        ExpRecordsIMPL.updateKnowledge(rno, data, logic, cao, dbms, os, cn, app);
	        ExpRecordsIMPL.updateSkills(rno, team, enth, conf, clean, oral, lang, prob, skill);
	        ExpRecordsIMPL.updateRemarks(rno, remarks);
	  
	    }
	    catch(Exception e)
	    {
	       System.out.println(e); 
	    }
	    
	    response.sendRedirect("expert/expert.jsp");
	}

}
