package com.ppi.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Types;
//import java.util.ArrayList;
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

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String savefile = System.getProperty("catalina.base") + "\\PPIUploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
		File f = null;
		File tosave = null;
		PrintWriter out = response.getWriter();
		try {
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
						//System.out.println(f.getAbsolutePath());
 	                   String ext=itemname.substring(itemname.lastIndexOf('.')+1);
 	           if ((ext.equalsIgnoreCase("txt"))||(ext.equalsIgnoreCase("doc"))||(ext.equalsIgnoreCase("docx"))|| (ext.equalsIgnoreCase("xlsx")) || (ext.equalsIgnoreCase("pdf"))){
 	                   tosave=new File(savefile,itemname);

 	             }
 	             
 	           item.write(tosave);
						item.write(f);
						out.print("Complete upload at "+f.getName()+" ");

					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		private File checkExist(String fileName) {

			File f = new File(savefile + "/" + fileName);
			if (f.exists()) {
				StringBuffer sb = new StringBuffer(fileName);
				sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
				f = new File(savefile + "/" + sb.toString());
			}
			return f;

		}

}
