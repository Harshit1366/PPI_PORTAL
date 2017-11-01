package com.ppi.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ppi.database.ConnectionFactory;
import com.ppi.impl.ExpertIMPL;
import com.ppi.impl.RecordsIMPL;
import com.ppi.model.Attitude;
import com.ppi.model.Knowledge;
import com.ppi.model.Skills;
import com.ppi.model.StudExpert;

/**
 * Servlet implementation class PDF_SelfSheet
 */
@WebServlet("/PDF_SelfSheet")
public class PDF_SelfSheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDF_SelfSheet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public static String getStatus(int score){

        String status ;
        switch(score){

            case 1: status = "Poor";
            break;
            case 2: status = "Satisfactory";
            break;
            case 3: status = "Good";
            break;
            case 4: status = "Very Good";
            break;
            case 5: status = "Excellent";
            break;
            default: status = "None";
            break;
        }

        return status;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession sess = request.getSession(false);
		String id=sess.getAttribute("user").toString();
		
		StudExpert se = new StudExpert();
		
		se = ExpertIMPL.getDetailsById(id);
		
	    Knowledge k = new Knowledge();
		
		k = RecordsIMPL.getRecordById(id);
		
		Skills s = new Skills();
		
		s = RecordsIMPL.getSkillsById(id);
		
		Attitude a = new Attitude();
		
		a = RecordsIMPL.getAttitudeById(id);
		
		
        Connection connection = null;
        try {
         
                Document document = new Document();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, bs);
                Rectangle rect = new Rectangle(15, 15, 580, 820);
                Rectangle rect2 = new Rectangle(17, 17, 578, 818);

                rect.setBorder(Rectangle.BOX);
                rect.setBorderWidth(0.5f);
                rect2.setBorder(Rectangle.BOX);
                rect2.setBorderWidth(0.5f);

                document.open();
                document.add(rect);
                document.add(rect2);

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                //System.out.println(dateFormat.format(date));

                Font headerFont
                        = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC);
                Paragraph p1 = new Paragraph("Name:" + se.getStudent().toUpperCase() + "\n",headerFont);
                Paragraph p2 = new Paragraph("Roll Number:" + se.getRoll().toUpperCase() + "\n",headerFont);
                Paragraph p3 = new Paragraph("Date:" + dateFormat.format(date) + "\n",headerFont);
                document.add(p1);
                document.add(p2);
                document.add(p3);

                Font tableHeader
                        = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);

                Font tableHeader2
                        = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);

                Paragraph p4 = new Paragraph("A. Attitude. ", tableHeader);
                
                
                PdfPTable table1 = new PdfPTable(2);
                table1.addCell(new Paragraph("Attitudnal Parameters", tableHeader2));
                table1.addCell(new Paragraph("Grades", tableHeader2));
                table1.addCell("Positive Thinking");
                table1.addCell(getStatus(Integer.valueOf(a.getPositive_thinking())));
                table1.addCell("Integrity");
                table1.addCell(getStatus(Integer.valueOf(a.getIntegrity())));
                table1.addCell("Dependabilty");
                table1.addCell(getStatus(Integer.valueOf(a.getDependability())));
                table1.addCell("Discipline");
                table1.addCell(getStatus(Integer.valueOf(a.getDiscipline())));
                table1.addCell("Commitment to excellence");
                table1.addCell(getStatus(Integer.valueOf(a.getExcellence())));
                table1.addCell("Perseverance");
                table1.addCell(getStatus(Integer.valueOf(a.getPerseverance())));
                table1.addCell("Enthusiasm/Passion");
                table1.addCell(getStatus(Integer.valueOf(a.getEnthusiasm())));
                table1.addCell("Self-Confidence");
                table1.addCell(getStatus(Integer.valueOf(a.getSelf_confidence())));
                table1.addCell("Initiative");
                table1.addCell(getStatus(Integer.valueOf(a.getInitiative())));
                table1.addCell("Farsightedness");
                table1.addCell(getStatus(Integer.valueOf(a.getFarsightedness())));
                table1.addCell("Team Spirit");
                table1.addCell(getStatus(Integer.valueOf(a.getTeam_spirit())));
                table1.addCell("Desire to learn");
                table1.addCell(getStatus(Integer.valueOf(a.getDesire_learn())));
                table1.addCell("Open Mindedness");
                table1.addCell(getStatus(Integer.valueOf(a.getOpen_mindedness())));
                table1.addCell("Adaptability");
                table1.addCell(getStatus(Integer.valueOf(a.getAdaptability())));
                table1.addCell("Empathy");
                table1.addCell(getStatus(Integer.valueOf(a.getEmpathy())));
                table1.addCell("Concern for others");
                table1.addCell(getStatus(Integer.valueOf(a.getConcern())));
                table1.addCell("Sense of Justice / Fairness");
                table1.addCell(getStatus(Integer.valueOf(a.getFairness())));
                table1.addCell("Modesty");
                table1.addCell(getStatus(Integer.valueOf(a.getModesty())));
                table1.addCell("Ethics");
                table1.addCell(getStatus(Integer.valueOf(a.getEthics())));
                table1.addCell("Cleanliness");
                table1.addCell(getStatus(Integer.valueOf(a.getCleanliness())));

                Paragraph p5 = new Paragraph("B. Knowlegde. ", tableHeader);

                PdfPTable table2 = new PdfPTable(2);
                table2.addCell(new Paragraph("Knowledge Parameters", tableHeader2));
                table2.addCell(new Paragraph("Grades", tableHeader2));
                table2.addCell("Understanding of Data Structures");
                table2.addCell(getStatus(Integer.valueOf(k.getData_structures())));
                table2.addCell("Desgin and evaluation of algorithms");
                table2.addCell(getStatus(Integer.valueOf(k.getAlgorithms())));
                table2.addCell("Logic building and programming paradigms");
                table2.addCell(getStatus(Integer.valueOf(k.getLogic_building())));
                table2.addCell("Computer Architecture and Organisation");
                table2.addCell(getStatus(Integer.valueOf(k.getCao())));
                table2.addCell("Database Management System");
                table2.addCell(getStatus(Integer.valueOf(k.getDbms())));
                table2.addCell("Operating System");
                table2.addCell(getStatus(Integer.valueOf(k.getOs())));
                table2.addCell("Networks");
                table2.addCell(getStatus(Integer.valueOf(k.getNetworks())));
                table2.addCell("Network and Information Security");
                table2.addCell(getStatus(Integer.valueOf(k.getInfo_security())));
                table2.addCell("Software Development Process");
                table2.addCell(getStatus(Integer.valueOf(k.getSoftware_dev())));
                table2.addCell("Software Project Management");
                table2.addCell(getStatus(Integer.valueOf(k.getSoftware_project())));
                table2.addCell("Mobilty/ Ubiquitous Computing");
                table2.addCell(getStatus(Integer.valueOf(k.getMobility())));
                table2.addCell("Data Mining & Knowledge Management");
                table2.addCell(getStatus(Integer.valueOf(k.getData_mining())));
                table2.addCell("Software Metrics");
                table2.addCell(getStatus(Integer.valueOf(k.getSoftware_metrics())));
                table2.addCell("Understanding of internet and its functioning");
                table2.addCell(getStatus(Integer.valueOf(k.getInternet())));
                table2.addCell("Application development using C/C++/PHP/JAVA/.Net etc.");
                table2.addCell(getStatus(Integer.valueOf(k.getApplication_dev())));
                table2.addCell("Understanding of Business and Financial terms.");
                table2.addCell(getStatus(Integer.valueOf(k.getBusiness_financial())));
                table2.addCell("Business Intelligence");
                table2.addCell(getStatus(Integer.valueOf(k.getBusi_intelligence())));
                table2.addCell("Human Rescource Management");
                table2.addCell(getStatus(Integer.valueOf(k.getHuman_resource())));
                table2.addCell("Modelling & Simulation");
                table2.addCell(getStatus(Integer.valueOf(k.getModeling_simulation())));
                table2.addCell("Enterprise Resource Planning");
                table2.addCell(getStatus(Integer.valueOf(k.getEnterprise_resource())));

                Paragraph p6 = new Paragraph("C. Skills. ", tableHeader);

                PdfPTable table3 = new PdfPTable(2);
                table3.addCell(new Paragraph("Skills Parameters", tableHeader2));
                table3.addCell(new Paragraph("Grades", tableHeader2));
                table3.addCell("IT Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getIT_skills())));
                table3.addCell("Listening Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getListening())));
                table3.addCell("Oral Communication");
                table3.addCell(getStatus(Integer.valueOf(s.getOral_comm())));
                table3.addCell("Written Communication");
                table3.addCell(getStatus(Integer.valueOf(s.getWritten_comm())));
                table3.addCell("Body Language");
                table3.addCell(getStatus(Integer.valueOf(s.getBody_lang())));
                table3.addCell("Conceptual Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getConceptual())));
                table3.addCell("Emotional Intelligence");
                table3.addCell(getStatus(Integer.valueOf(s.getEI())));
                table3.addCell("Conflict Resolution Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getConflict_reso())));
                table3.addCell("Problem Solving Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getProb_solving())));
                table3.addCell("Organisation Skills- Implemenation");
                table3.addCell(getStatus(Integer.valueOf(s.getOrganising())));
                table3.addCell("Inter-personal Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getInter_personal())));
                table3.addCell("Social Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getSocial())));
                table3.addCell("Innovative- Out of Box Thinking");
                table3.addCell(getStatus(Integer.valueOf(s.getInnovative())));
                table3.addCell("Pro-active-preventive approch");
                table3.addCell(getStatus(Integer.valueOf(s.getPro_active())));
                table3.addCell("Multi-tasking");
                table3.addCell(getStatus(Integer.valueOf(s.getMulti_tasking())));
                table3.addCell("Eye-for details & observational skills");
                table3.addCell(getStatus(Integer.valueOf(s.getObservational())));
                table3.addCell("Analytical Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getAnalytical())));
                table3.addCell("Negotiating Skills");
                table3.addCell(getStatus(Integer.valueOf(s.getNegotiating())));
                table3.addCell("Robust Common Sense");
                table3.addCell(getStatus(Integer.valueOf(s.getRobust_common())));
                table3.addCell("Time Management");
                table3.addCell(getStatus(Integer.valueOf(s.getTime_manage())));

                document.add(p4);
                document.add(new Paragraph("\n"));
                document.add(table1);
                document.add(new Paragraph("\n\n"));
                document.add(p5);
                document.add(new Paragraph("\n"));
                document.add(table2);
                document.add(rect);
                document.add(rect2);
                document.add(new Paragraph("\n\n"));
                document.add(p6);
                document.add(new Paragraph("\n"));
                document.add(table3);
                document.add(new Paragraph("\n\n"));
                Paragraph p8 = new Paragraph("\n\n\n        *****");
                p8.setAlignment(Element.ALIGN_CENTER);
                document.add(p8);

                document.close();

                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");
                response.setContentType("application/pdf");
                response.setContentLengthLong(bs.size());

                ServletOutputStream sos = response.getOutputStream();
                bs.writeTo(sos);
                sos.flush();

        } catch (NullPointerException | MalformedURLException e) {
            e.printStackTrace();
            response.sendRedirect("student/student.jsp");
        } catch (BadElementException ex) {
            System.out.println(ex);
            response.sendRedirect("student/student.jsp");
        } catch (DocumentException ex) {
            System.out.println(ex);
            response.sendRedirect("student/student.jsp");
        } finally {
            try {
                ConnectionFactory.close(connection);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
