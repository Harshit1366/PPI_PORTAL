package com.ppi.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ppi.impl.ExpRecordsIMPL;
import com.ppi.impl.ExpertIMPL;
import com.ppi.model.ExpKnowledge;
import com.ppi.model.ExpRemarks;
import com.ppi.model.ExpSkills;
import com.ppi.model.StudExpert;

/**
 * Servlet implementation class PDFGenerator
 */
@WebServlet("/PPIScore")
public class PPIScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PPIScore() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession(false);
		String id=sess.getAttribute("user").toString();
		
		StudExpert se = new StudExpert();
		
		se = ExpertIMPL.getDetailsById(id);
		
		ExpKnowledge k = new ExpKnowledge();
		
		k = ExpRecordsIMPL.getRecordById(id);
		
		ExpSkills s = new ExpSkills();
		
		s = ExpRecordsIMPL.getSkillsById(id);
		
		ExpRemarks r = new ExpRemarks();
		
		r = ExpRecordsIMPL.getRemarksById(id);
		
		Document document = new Document();
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
	      try
	      {
	         //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("PPI.pdf"));
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
             
             Image img = Image.getInstance(System.getProperty("catalina.base") + "\\images\\ncu.png");
             img.scaleToFit(100f, 100f);
             
             document.add(img);
             
        	 Font tableHeader
                     = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD);
         
	         Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);         
	         PdfPTable th = new PdfPTable(1);
	         PdfPCell in1= new PdfPCell();
	         Phrase firstLine = new Phrase("\t\t\t\t                                 The NorthCap University\n"
	         		+ "                                   PPI EVALUATION RESULT", boldFont );     
	         //in1.addElement(firstLine );
	         document.add(firstLine);
	       
	         in1.setBorder(2);

			 th.addCell(in1);

	         document.add(th);


	         document.add(new Paragraph("\n\n     Student & Expert Details :", tableHeader));
	         document.add(new Paragraph());

	         document.add( Chunk.NEWLINE );

	         
	         Font headerFont= FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
	         Font headerFont2= FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
	         
	         PdfPTable table1 = new PdfPTable(2); 
	            PdfPCell cell1 = new PdfPCell(new Paragraph("NAME OF EXPERT",headerFont));
				//cell1.setBorderColor(BaseColor.BLUE);
	            cell1.setPadding(5);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PdfPCell cell2 = new PdfPCell(new Paragraph(se.getExpert().toUpperCase(),headerFont2));
				//cell2.setBorderColor(BaseColor.BLUE);
	            cell2.setPadding(5);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PdfPCell cell3 = new PdfPCell(new Paragraph("NAME OF STUDENT",headerFont));
				//cell3.setBorderColor(BaseColor.BLUE);
	            cell3.setPadding(5);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PdfPCell cell4 = new PdfPCell(new Paragraph(se.getStudent().toUpperCase(),headerFont2));
				//cell4.setBorderColor(BaseColor.BLUE);
	            cell4.setPadding(5);
				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PdfPCell cell5 = new PdfPCell(new Paragraph("ROLL NO",headerFont));
				//cell5.setBorderColor(BaseColor.BLUE);
	            cell5.setPadding(5);
				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				PdfPCell cell6 = new PdfPCell(new Paragraph(se.getRoll().toUpperCase(),headerFont2));
				//cell6.setBorderColor(BaseColor.BLUE);
	            cell6.setPadding(5);
				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				cell1.setUseBorderPadding(true);
				cell2.setUseBorderPadding(true);
				cell3.setUseBorderPadding(true);
				cell4.setUseBorderPadding(true);
				cell5.setUseBorderPadding(true);
				cell6.setUseBorderPadding(true);
				
	         table1.addCell(cell1);
	         table1.addCell(cell2);
	         table1.addCell(cell3);
	         table1.addCell(cell4);
	         table1.addCell(cell5);
	         table1.addCell(cell6);
	         document.add(table1);

     Font tableHeader2
             = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);

     Paragraph p4 = new Paragraph("A.\t Feedback about Student Knowledge :", tableHeader);
     document.add( Chunk.NEWLINE );
     
     PdfPTable table2 = new PdfPTable(2);
     table2.addCell(new Paragraph("Knowledge Parameters", tableHeader2));
     table2.addCell(new Paragraph("Grades", tableHeader2));
     table2.addCell("Data Structures");
     table2.addCell(getStatus(Integer.valueOf(k.getData())));
     table2.addCell("Logic Building");
     table2.addCell(getStatus(Integer.valueOf(k.getLogic())));
     table2.addCell("Computer Architecture & Organisation");
     table2.addCell(getStatus(Integer.valueOf(k.getCao())));
     table2.addCell("Database Management Systems");
     table2.addCell(getStatus(Integer.valueOf(k.getDbms())));
     table2.addCell("Operating Systems");
     table2.addCell(getStatus(Integer.valueOf(k.getOs())));
     table2.addCell("Computer Networks");
     table2.addCell(getStatus(Integer.valueOf(k.getCn())));
     table2.addCell("Application development using C/C++/PHP/Java");
     table2.addCell(getStatus(Integer.valueOf(k.getApp())));
     table2.addCell("Overall Grade");
     int t=Integer.valueOf(k.getTotal())/7;
     System.out.println(t);
     table2.addCell(getStatus(t));	         
	         
	         

	         document.add( Chunk.NEWLINE );
	         
	         
	         Paragraph p5 = new Paragraph("B.\t Feedback about Student Skills :", tableHeader);
	         document.add( Chunk.NEWLINE );
	         
	         PdfPTable table3 = new PdfPTable(2);
	         table3.addCell(new Paragraph("Skill Parameters", tableHeader2));
	         table3.addCell(new Paragraph("Grades", tableHeader2));
	         table3.addCell("Team Spirit");
	         table3.addCell(getStatus(Integer.valueOf(s.getTeam())));
	         table3.addCell("Enthusiasm");
	         table3.addCell(getStatus(Integer.valueOf(s.getEnth())));
	         table3.addCell("Self Confidence");
	         table3.addCell(getStatus(Integer.valueOf(s.getConf())));
	         table3.addCell("Cleanliness");
	         table3.addCell(getStatus(Integer.valueOf(s.getClean())));
	         table3.addCell("Oral Communication");
	         table3.addCell(getStatus(Integer.valueOf(s.getOral())));
	         table3.addCell("Body Language");
	         table3.addCell(getStatus(Integer.valueOf(s.getLang())));
	         table3.addCell("Problem Solving");
	         table3.addCell(getStatus(Integer.valueOf(s.getProb())));
	         table3.addCell("Analytical Skills");
	         table3.addCell(getStatus(Integer.valueOf(s.getSkill())));	         
	         table3.addCell("Overall Grade");
	         int to=Integer.valueOf(s.getTotal())/8;
	         System.out.println(to);
	         table3.addCell(getStatus(to));	  
	       

	         
	         document.add(p4);
             document.add(new Paragraph("\n"));
             document.add(table2);
             document.add(new Paragraph("\n\n"));
             document.add(p5);
             document.add(new Paragraph("\n"));
             document.add(table3);
//             document.add(rect);
//             document.add(rect2);
             document.add(new Paragraph(""));
	         
             document.add(rect);
             document.add(rect2);
             img.scaleToFit(100f, 100f);
             document.add(img);
             document.add(firstLine);
             //th.addCell(in1);
             document.add(th);
	 

	         DateFormat df = new SimpleDateFormat("dd/MM/yy");
	         Date dateobj = new Date();
	         
	         document.add( Chunk.NEWLINE );
	         document.add( Chunk.NEWLINE );
	         
	         document.add(new Paragraph("C.\t Student Remarks : ",tableHeader));
	         
	         document.add(new Paragraph("\t   "+r.getRemarks()));
	         
	         document.add(Chunk.NEWLINE);
	         document.add(new Paragraph("\n\t\t\tDate : " + df.format(dateobj)));
	         
	         Paragraph p8 = new Paragraph("\n\n\n     *****");
             p8.setAlignment(Element.ALIGN_CENTER);
             document.add(p8);


	         document.close();
	         response.setHeader("Expires", "0");
             response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
             response.setHeader("Pragma", "public");
             response.setContentType("application/pdf");
             response.setContentLengthLong(bs.size());

             javax.servlet.ServletOutputStream sos = response.getOutputStream();
             bs.writeTo(sos);
             sos.flush();
	         //writer.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } catch (FileNotFoundException e)
	      {
	         e.printStackTrace();
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
