package com.ppi.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ppi.impl.RecordsIMPL;



/**
 * Servlet implementation class Stud_Knowledge
 */
@WebServlet("/Stud_details")
public class Stud_details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stud_details() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        String name= request.getParameter("name");
        String rno= request.getParameter("rno");
       
        
        String data= request.getParameter("data_structures");

        String algo= request.getParameter("algorithms");
      
        String log= request.getParameter("logic_building");
    
        String cao= request.getParameter("cao");

        String dbms= request.getParameter("dbms");
        
        String os= request.getParameter("os");
  
        String network= request.getParameter("networks");
        
        String info= request.getParameter("info_security");

        String dev= request.getParameter("software_dev");
      
        String pro= request.getParameter("software_project");
    
        String mob= request.getParameter("mobility");

        String mining= request.getParameter("data_mining");
        
        String sm= request.getParameter("software_metrics");
  
        String inter= request.getParameter("internet");
        
        String app= request.getParameter("application_dev");

        String fin= request.getParameter("business_financial");
      
        String intel= request.getParameter("busi_intelligence");
    
        String hr= request.getParameter("human_resource");

        String ms= request.getParameter("modeling_simulation");
        
        String er= request.getParameter("enterprise_resource");

        
        String it= request.getParameter("IT-skills");
        
        String lis= request.getParameter("listening");

        String oral= request.getParameter("oral_comm");

        String writ= request.getParameter("written_comm");

        String body= request.getParameter("body_lang");
        
        String con= request.getParameter("conceptual");

        String ei= request.getParameter("EI");
 
        String reso= request.getParameter("conflict_reso");

        String prob= request.getParameter("prob_solving");
        
        String org= request.getParameter("organising");

        String per= request.getParameter("inter-personal");

        String soc= request.getParameter("social");

        String inno= request.getParameter("innovative");
        
        String act= request.getParameter("pro-active");

        String task= request.getParameter("multi_tasking");
 
        String obs= request.getParameter("observational");

        String ana= request.getParameter("analytical");
        
        String nego= request.getParameter("negotiating");

        String com= request.getParameter("robust_common");

        String time= request.getParameter("time_manage");
        
        
        String pos= request.getParameter("positive_thinking");
        
        String inte= request.getParameter("integrity");

        String dep= request.getParameter("dependability");

        String dis= request.getParameter("discipline");

        String ex= request.getParameter("excellence");
        
        String pers= request.getParameter("perseverance");

        String ent= request.getParameter("enthusiasm");
 
        String conf= request.getParameter("self-confidence");

        String init= request.getParameter("initiative");
        
        String far= request.getParameter("farsightedness");

        String team= request.getParameter("team_spirit");

        String des= request.getParameter("desire_learn");

        String open= request.getParameter("open_mindedness");
        
        String ada= request.getParameter("adaptability");

        String emp= request.getParameter("empathy");
 
        String conc= request.getParameter("concern");

        String fair= request.getParameter("fairness");
        
        String mod= request.getParameter("modesty");

        String eth= request.getParameter("ethics");

        String clean= request.getParameter("cleanliness");
        
        
    

    try{
   
        boolean a=RecordsIMPL.addKnowledge(rno, name, data, algo, log, cao, dbms, os, network, info, dev, pro, mob, mining ,sm, inter, app, fin, intel, hr, ms, er);
        boolean b=RecordsIMPL.addSkills(rno, name, it, lis, oral, writ, body, con, ei, reso, prob, org, per, soc, inno, act, task ,obs, ana, nego, com ,time);
        boolean c=RecordsIMPL.addAttitude(rno, name, pos, inte, dep, dis, ex, pers, ent, conf, init, far, team, des, open, ada, emp, conc, fair, mod, eth, clean);
        if(a && b && c){
        RecordsIMPL.updateAssess(rno);
        }
  
    }
    catch(Exception e)
    {
       System.out.println(e); 
    }
    
    response.sendRedirect("student/student.jsp");
   
    
    }
}
