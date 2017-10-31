package com.ppi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ppi.impl.LoginIMPL;
import com.ppi.model.Login;

/**
 * Servlet implementation class ReadExcel
 */
@WebServlet("/ReadExcel")
public class ReadExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
String savefile = "C:/Users/Harshit/Desktop/new";
	
	private File checkExist(String fileName) {

		File f = new File(savefile + "/" + fileName);
		if (f.exists()) {
			StringBuffer sb = new StringBuffer(fileName);
			sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
			f = new File(savefile + "/" + sb.toString());
		}
		return f;

	}
	
	
    public ReadExcel() {
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
	
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
	         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppi","root","root");
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
				System.out.println(items);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {	
						
					} else  {
						
						String itemname = item.getName();
						System.out.println(item);
						if ((itemname == null) || itemname.equals("")) {
							continue;
						}
						String filename = FilenameUtils.getName(itemname);
						f = checkExist(filename);
						System.out.println("Path:"+f.getAbsolutePath());
						item.write(f);
						System.out.println("Location of file :"+f.getName());
						
					}
				}
				
          PreparedStatement pstm = null;

          String sql = "DELETE FROM RECORDS";
          pstm = con.prepareStatement(sql);
          pstm.execute();
          
   FileInputStream file = new FileInputStream(new File(f.getAbsolutePath()));
   
   XSSFWorkbook workbook = new XSSFWorkbook(file);

   XSSFSheet sheet = workbook.getSheetAt(0);
   
   for(int i=0; i<=sheet.getLastRowNum(); i++)
   {
   
       DataFormatter formatter = new DataFormatter(); 
       Cell cell = sheet.getRow(i).getCell(0);
       String rno = formatter.formatCellValue(cell);
       
        Cell cell1 = sheet.getRow(i).getCell(1);
       String name = formatter.formatCellValue(cell1);

       sql = "INSERT INTO records(name,rno) VALUES('"+name+"','"+rno+"')";
       pstm = con.prepareStatement(sql);
       pstm.execute();
       
       Login login=new Login();
		
	   login.setUsername(rno);
	   login.setPassword(rno);
	   login.setRole("ROLE_STUDENT");
	   login.setStatus("active");
		
	   LoginIMPL l=new LoginIMPL();
		l.saveNewLogin(login);
       
       System.out.println("Import rows "+i);
   }
   
   file.close();
   workbook.close();
con.commit();
pstm.close();
} 
}
catch (Exception e) 
{
   e.printStackTrace();
}
          
		response.sendRedirect("admin/admin_home.jsp");
}
}
