package com.ppi.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppi.impl.AssignIMPL;
import com.ppi.impl.ExpRecordsIMPL;

/**
 * Servlet implementation class ExpRemarks
 */
@WebServlet("/ExpRemarks")
public class ExpRemarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpRemarks() {
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
		
		@SuppressWarnings("unchecked")
		List<String> know=(List<String>)sess.getAttribute("knowledge");
		
        String rno= know.get(0);
        
        String name= know.get(1);
               
        String data= know.get(2);

        String logic= know.get(3);
      
        String cao= know.get(4);
    
        String dbms= know.get(5);

        String os= know.get(6);
        
        String cn= know.get(7);
  
        String app= know.get(8);
        
        @SuppressWarnings("unchecked")
		List<String> skills=(List<String>)sess.getAttribute("skills");
        
        String team= skills.get(0);
        
        String enth= skills.get(1);

        String conf= skills.get(2);

        String clean= skills.get(3);

        String oral= skills.get(4);
        
        String lang= skills.get(5);

        String prob= skills.get(6);
 
        String skill= skills.get(7);
		
		String remarks= request.getParameter("remarks");
	    

	    try{
	   
	        ExpRecordsIMPL.addKnowledge(rno, name, data, logic, cao, dbms, os, cn, app);
	        ExpRecordsIMPL.addSkills(rno, name, team, enth, conf, clean, oral, lang, prob, skill);
	        ExpRecordsIMPL.addRemarks(rno, remarks);
	        AssignIMPL.updateAssign(rno);
	        sess.removeAttribute("studname");
	        sess.removeAttribute("studroll");
	        sess.removeAttribute("knowledge");
	        sess.removeAttribute("skills");
	  
	    }
	    catch(Exception e)
	    {
	       System.out.println(e); 
	    }
	    
	    response.sendRedirect("expert/expertKnowledge.jsp");
	}

}
