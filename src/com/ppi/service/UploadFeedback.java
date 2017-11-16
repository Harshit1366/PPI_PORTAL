package com.ppi.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppi.database.ConnectionFactory;

/**
 * Servlet implementation class UploadFeedback
 */
@WebServlet("/UploadFeedback")
public class UploadFeedback extends HttpServlet {
	static Connection con = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFeedback() {
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

		String r1="",r2="",r3="",r4="",r5="",r6="",r7="";

		 r1 = request.getParameter("r1");
		 r2 = request.getParameter("r2");
		 r3 = request.getParameter("r3");
		 r4 = request.getParameter("r4");
		 r5 = request.getParameter("r5");
		 r6 = request.getParameter("r6");
		 r7 = request.getParameter("r7");

		int r11=0,r12=0,r13=0,r14=0,r15=0,r16=0,r17=0;

		r11=Integer.parseInt(r1);
		r12=Integer.parseInt(r2);
		r13=Integer.parseInt(r3);
		r14=Integer.parseInt(r4);
		r15=Integer.parseInt(r5);
		r16=Integer.parseInt(r6);
		r17=Integer.parseInt(r7);

		 try{
			       HttpSession sess=request.getSession(); 
		           con=ConnectionFactory.getConnection();
                   String id= sess.getAttribute("user").toString();
		           PreparedStatement ps=con.prepareStatement("insert into feedback values(?,?,?,?,?,?,?,?)");
		           ps.setString(1,id);
		           ps.setInt(2,r11);
		           ps.setInt(3,r12);
		           ps.setInt(4,r13);
		           ps.setInt(5,r14);
		           ps.setInt(6,r15);
		           ps.setInt(7,r16);
		           ps.setInt(8,r17);

		           if( ps.executeUpdate() > 0)
		           {
		           PreparedStatement ps1=con.prepareStatement("update records set feedback=? where rno=?");
		           ps1.setInt(1, 1);
		           ps1.setString(2, id);
		           ps1.executeUpdate();
		           }
		             
		          
		       con.close();
		       response.sendRedirect("student/student.jsp");
		 
		       }
		 catch(Exception e)
		       {
			          System.out.println(e); 
			       }
		   }


}
