package com.ppi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class ExpertExcel
 */
@WebServlet("/ExpertExcel")
public class ExpertExcel extends HttpServlet {
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
	
	
    public ExpertExcel() {
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
				//System.out.println(items);
				Iterator<?> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {	
						
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
						//System.out.println("Location of file :"+f.getName());
						
					}
				}

   FileInputStream file = new FileInputStream(new File(f.getAbsolutePath()));
   
   XSSFWorkbook workbook = new XSSFWorkbook(file);

   XSSFSheet sheet = workbook.getSheetAt(0);
   
   for(int i=0; i<=sheet.getLastRowNum(); i++)
   {
   
       DataFormatter formatter = new DataFormatter(); 
       Cell cell = sheet.getRow(i).getCell(0);
       String name = formatter.formatCellValue(cell);
       
       Cell cell1 = sheet.getRow(i).getCell(1);
       String id = formatter.formatCellValue(cell1);
       
       Cell cell2 = sheet.getRow(i).getCell(2);
       String pass = formatter.formatCellValue(cell2);
       
       //System.out.println("Name : "+name+" \tId : "+id+"\t Password : "+pass);

       Login login=new Login();
		
	   login.setUsername(id);
	   login.setPassword(pass);
	   login.setRole("ROLE_EXPERT");
	   login.setStatus("active");
	   login.setName(name);
		
	   LoginIMPL l=new LoginIMPL();
		l.saveNewExpert(login);
       
       //System.out.println("Import rows "+i);
   }
   
   file.close();
   workbook.close();
con.commit();

} 
}
catch (Exception e) 
{
   e.printStackTrace();
}
          
		response.sendRedirect("admin/admin_home.jsp");
	}

}
