package com.ppi.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.ppi.impl.ExpRecordsIMPL;
import com.ppi.model.ExpKnowledge;

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet("/ChartServlet")
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int data=0,logic=0,cao=0,dbms=0,os=0,cn=0,app=0,total=0;  
	String h,i,j,k,l,m,n;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("image/jpeg");

		OutputStream outputStream = response.getOutputStream();

		JFreeChart chart1 = getChart();	
		int width = 650;
		int height = 500;
		ChartUtilities.writeChartAsPNG(outputStream, chart1, width, height);
	}

	public JFreeChart getChart() {
		
		for(ExpKnowledge k : ExpRecordsIMPL.getKnowledge()){
			data=data+Integer.valueOf(k.getData());
			logic=logic+Integer.valueOf(k.getLogic());
			cao=cao+Integer.valueOf(k.getCao());
			dbms=dbms+Integer.valueOf(k.getDbms());
			os=os+Integer.valueOf(k.getOs());
			cn=cn+Integer.valueOf(k.getCn());
			app=app+Integer.valueOf(k.getApp());
		}
		System.out.println(data+"  "+logic+"  "+dbms+"   "+cao+"   "+os+"   "+cn+"   "+app);
		
		total=data+logic+cao+dbms+os+cn+app;
		
		float a = ((float)data/total*100);
		float b = ((float)logic/total*100);
		float c = ((float)cao/total*100);
		float d = ((float)dbms/total*100);
		float e = ((float)os/total*100);
		float f = ((float)cn/total*100);
		float g = ((float)app/total*100);
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		h=df.format(a);
		i=df.format(b);
		j=df.format(c);
		k=df.format(d);
		l=df.format(e);
		m=df.format(f);
		n=df.format(g);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("DATA STRUCTURE ("+h+" %)", data);
		dataset.setValue("LOGIC BUILDING ("+i+" %)", logic);
		dataset.setValue("COMPUTER ARCHITECTURE & ORGANISATION ("+j+" %)", cao);
		dataset.setValue("DATABASE MANAGEMENT SYSTEM ("+k+" %)", dbms);
		dataset.setValue("OPERATING SYSTEM ("+l+" %)", os);
		dataset.setValue("COMPUTER NETWORK ("+m+" %)", cn);
		dataset.setValue("APPLICATION DEVELOPMENT ("+n+" %)", app);

		boolean legend = true;
		boolean tooltips = false;
		boolean urls = false;

		JFreeChart chart = ChartFactory.createPieChart("Student Knowledge", dataset, legend, tooltips, urls);

		chart.setBorderPaint(Color.BLUE);
		chart.setBorderStroke(new BasicStroke(5.0f));
		chart.setBorderVisible(true);

		return chart;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
