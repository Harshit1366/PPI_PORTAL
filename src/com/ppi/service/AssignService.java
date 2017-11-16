package com.ppi.service;

import java.io.File;
import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.ppi.database.ConnectionFactory;
import com.ppi.impl.ReaderIMPL;
import com.ppi.model.Ppi;



/**
 * Servlet implementation class AssignService
 */
@WebServlet("/AssignService")
public class AssignService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String savefile = System.getProperty("catalina.base") + "\\PPIUploads";
	//String savefile = "C:\\Users\\Harshit\\Desktop\\PPIUploads";
	
	private File checkExist(String fileName) {

		File f = new File(savefile + "/" + fileName);
		if (f.exists()) {
			StringBuffer sb = new StringBuffer(fileName);
			sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
			f = new File(savefile + "/" + sb.toString());
		}
		return f;

	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
		Connection con=null;
		File f = null;
		
		HttpSession sess=request.getSession();  

		//PrintWriter out = response.getWriter();
		List<String[]> formdata = new ArrayList<>();
		try {
			
	         con = ConnectionFactory.getConnection();
	         con.setAutoCommit(false);
			boolean ismultipart = ServletFileUpload.isMultipartContent(request);
			if (!ismultipart) {

			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<?> items = null;

				try {
					items = upload.parseRequest(request);
				} catch (Exception e) {

				}
				//System.out.println(items);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						
						String form_value_name = item.getFieldName();
						String form_value_value = item.getString();
						//System.out.println("FORM DATA:" + form_value_name + " " + form_value_value);
						formdata.add(new String[]{form_value_name, form_value_value});
						
						int i=1;
						
						//System.out.println(formdata.size());
						for(String[] s:formdata){
							if(i==1){
								sess.setAttribute("no", s[1]);
								//System.out.println("no:"+s[1]);
							}
							else if(i==2){
								sess.setAttribute("date", s[1]);
								//System.out.println("date:"+s[1]);
							}
							else{
								break;
							}
							i++;
						}
						    
							
							
						
					} else  {
						
						String itemname = item.getName();
						//System.out.println(item);
						if ((itemname == null) || itemname.equals("")) {
							continue;
						}
						String filename = FilenameUtils.getName(itemname);
						f = checkExist(filename);
						//System.out.println("Path:"+f.getAbsolutePath());
						item.write(f);
//						sess.setAttribute("file", f.getAbsolutePath());
						//System.out.println("Location of file :"+f.getName());
						
					}
				}
				
          PreparedStatement pstm = null;

//          FileInputStream file = new FileInputStream(new File(f.getAbsolutePath()));
//
//          XSSFWorkbook workbook = new XSSFWorkbook(file);
//
//          XSSFSheet sheet = workbook.getSheetAt(0);
//          
//          List<String> expert=new ArrayList<String>();
//
//          for(int i=0; i<=sheet.getLastRowNum(); i++)
//          {
//              
//              DataFormatter formatter = new DataFormatter();
//              Cell cell = sheet.getRow(i).getCell(0);
//              String name = formatter.formatCellValue(cell);
//              

//              
//              //System.out.println("Name : "+name+" \tId : "+id+"\t Password : "+pass);
//              
//              expert.add(name);
//              

//              //System.out.println("Import rows "+i);
//          }
//          sess.setAttribute("experts",expert);
//          file.close();
//          workbook.close();
          
          
          List<String> expert=new ArrayList<String>();
          
          List<List<String>> data = ReaderIMPL.readData(f.getAbsolutePath());
			
		for(List<String> row:data ){
          expert.add(row.get(2));
			}
		//System.out.println(expert);
          sess.setAttribute("experts",expert);

          String sql = "Select count(*) from records where ppi_assigned=?";
          
          pstm=con.prepareStatement(sql);
          pstm.setInt(1, 0);
          ResultSet rs= pstm.executeQuery();
          int a=0;
          while(rs.next()){
       	      a=rs.getInt(1);
          }
          
          String sql5 = "Select rno,name from records where ppi_assigned=?";
          pstm=con.prepareStatement(sql5);
          pstm.setInt(1, 0);
          ResultSet rs2= pstm.executeQuery();
          
          
          List<Ppi> stud=new ArrayList<Ppi>();
          int b=Integer.parseInt((String) sess.getAttribute("no"));
          int j=0;
          
           while(j<a && rs2.next()){   

        	  Ppi p=new Ppi();
				p.setExpert(rs2.getString(1));
				p.setRoll(rs2.getString(2));
				if((j)<b){
				    p.setAssign(1);
				}
				else{
					p.setAssign(0);
				}
				stud.add(p);        	   
           	  j++;
              }
			
           sess.setAttribute("students",stud);
			        
          
      con.commit();
      
      pstm.close();
      response.sendRedirect("admin/Students_ppi.jsp"); 
			}
		
}catch (Exception e) 
        {
            e.printStackTrace();
        }
		
            
	}

}
