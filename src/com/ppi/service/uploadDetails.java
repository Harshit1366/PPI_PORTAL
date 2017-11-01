package com.ppi.service;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ppi.database.ConnectionFactory;
import com.ppi.model.Ppi;

/**
 * Servlet implementation class uploadDetails
 */
@WebServlet("/uploadDetails")
public class uploadDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadDetails() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String roll[]= request.getParameterValues("checkbox");
		for(int i=0;i<roll.length;i++){
			System.out.println(roll[i]);
		}
		
		HttpSession sess=request.getSession();  
		//int no=(int) sess.getAttribute("no");
		//int no=Integer.parseInt((String) sess.getAttribute("no"));
		//String date=(String) sess.getAttribute("date");
		ArrayList<?> expert=(ArrayList<?>) sess.getAttribute("experts");
		
		Connection con=null;
		
		
		
        try
        {        	
                   con=ConnectionFactory.getConnection();
                   //con.setAutoCommit(false);
                   PreparedStatement ps = null,ps2=null;
                   List<Ppi> list=new ArrayList<Ppi>();
                   int a = 0,b = 0,c,d;//,e=0;
                   String x = null,y = null;
                   

                   //System.out.println(expert);
                   
                   a=roll.length;
                   
                   b=expert.size();

                   c=a/b;
                   d=a%b;
                   
//                   System.out.println("a : "+a);
//                   System.out.println("b : "+b);
//                   System.out.println("c : "+c);
//                   System.out.println("d : "+d);

                   int k=0;
                  
                   
                	  
                    	   for(int j=0;j<c;j++){   
                    		   for(int i=0;i<b;i++){
                               
                               if(k!=a){
                            	   //System.out.println("k : "+(k+1));                             	   
                            	   x=(String) expert.get(i);
                            	   //System.out.println("x : "+x);

                            	   y=roll[k];
                            	   //System.out.println("y : "+y);
                           		
                      				Ppi p=new Ppi();
                      				p.setExpert(x);
                      				p.setRoll(y);
                      				p.setAssign(1);

                      				list.add(p);
                               }
                    		   k++;
                    		   
                    	 }  

                   }
                	   
                	   if(d!=0){

                		   for(int i=0;i<d;i++){
                           
                           //System.out.println("k : "+(k+1));  
                                          	   
                        	   x=(String)expert.get(i);
                        	   //System.out.println("x : "+x);

                        	   y=roll[k];
                        	   //System.out.println(y);

                        	   
                        	   Ppi p=new Ppi();
                 				p.setExpert(x);
                 				p.setRoll(y);
                 				p.setAssign(1);

                 				list.add(p);
                 				
                 				k++;
                	   }
                	   
            		   
           	   }

        	Iterator<Ppi> itr2=list.iterator();

     	    while(itr2.hasNext())
     	    {
     	        Ppi p = itr2.next();
     	           String sql = "insert into assign(expert_id,student_id) values(?,?)";
       		       ps=con.prepareStatement(sql);
           		   ps.setString(1,p.getExpert());
                   ps.setString(2,p.getRoll());
                   //System.out.println(ps.executeUpdate());
                   String sql2 = "update records set ppi_assigned = ? where rno = ?";
           		   ps2=con.prepareStatement(sql2);
           		   ps2.setInt(1, p.getAssign());
           		   ps2.setString(2, p.getRoll());
           		   //System.out.println(ps2.executeUpdate());

                   }
  
     	    ps.close();
     	    con.close();
     	    
        response.sendRedirect("admin/assigned_students.jsp");
	}catch(Exception e){
		e.printStackTrace();
	}

	}

}
