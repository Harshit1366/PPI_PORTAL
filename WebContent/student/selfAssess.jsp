<%@ page import="java.sql.* "%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>
<%@ page import="com.ppi.impl.AssignIMPL" %>
<%@ page import="com.ppi.impl.RecordsIMPL" %>
<%@ page import="com.ppi.database.ConnectionFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> 
           <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/admin.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> 

 <script src="js/bootstrap.min.js"></script>

<script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.js"></script>
<style>
.nav-tabs > li.active > a:focus, .nav-tabs > li.active > a
{
    margin-top: 1px;
}
.nav-tabs > li {
    margin-bottom: 0px; 
}
.nav-tabs > li.active {
    margin-bottom: -1px;    
}</style>
     
  <script src="jquery.min.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function () {
    //Disable full page
    $("body").on("contextmenu",function(e){
        return false;
    });
    
    //Disable part of page
    $("#id").on("contextmenu",function(e){
        return false;
    });
});
</script>
<script type="text/javascript"> 
document.onkeydown = function(e) {
        if (e.ctrlKey && 
            (e.keyCode === 67 || 
             e.keyCode === 86 || 
             e.keyCode === 85 || 
             e.keyCode === 117)) {
            alert('not allowed');
            return false;
        } else {
            return true;
        }
};
</script>
<input type="hidden" id="refreshed" value="no">
<script type="text/javascript"> 
onload = function() 
{ 
	var e = document.getElementById("refreshed"); 
    if (e.value == "no") 
	e.value = "yes"; 
	else
	{
    e.value = "no"; 
	location.reload(); 
	} 
	} 
	</script>
    <body background="images/e.jpg">
    <%  if(request.getSession().getAttribute("sid")==null){
            response.sendRedirect("../login.jsp");
            return;
        }%>
       <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">PPI PORTAL</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="student.jsp">Home</a></li>

      
    </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><%=RecordsIMPL.getName((String)session.getAttribute("user"))%></a></li>
      <li><a href="../account/logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
    </ul>
    
  </div>
</nav>
<div id="sidebar"  class="col-sm-3 col-lg-2 sidebar collapse in">
            <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">Dashboard</h3>
            </div>

            <ul class="nav menu" style="margin-top: 10px">
             
                 <%if(AssignIMPL.getPPI(session.getAttribute("user").toString())==1){ %>
                <li id="score"><a href="../PPIScore"><span class="glyphicon glyphicon-book"></span> Your Score</a></li>
                <%} %>
                <li id="cv"><a href="../DownloadCV"><span class="glyphicon glyphicon-download-alt"></span> Download CV Format</a></li>
                <%if(RecordsIMPL.getAssess(session.getAttribute("user").toString())==1){ %>              
                <li id="pdf"><a href="../PDF_SelfSheet"><span class="glyphicon glyphicon-file"></span> Self-Assessment PDF</a></li>
                <%} else{
                %>
                <li id="self_assess"><a href="selfAssess.jsp"><span class="glyphicon glyphicon-comment"></span> Self-Assessment Form</a></li>
                <%} %>
                <%if(RecordsIMPL.getFeedback(session.getAttribute("user").toString())==0){ %>
                <li id="feedback"><a href="feedback.jsp"><span class="glyphicon glyphicon-comment"></span> Feedback Form</a></li>
                <%} %>
             
            </ul>
        </div>
        <!-- Sidebar ends -->
        
   <div style="float:right; width:80%">
   <div class="container">  
  
  <ul class="nav nav-tabs">  
    <li><a data-toggle="tab" href="#stud_knowledge">Knowledge</a></li>  
    <li><a data-toggle="tab" href="#stud_skills">Skills</a></li>  
    <li><a data-toggle="tab" href="#stud_attitude">Attitude</a></li>    
  </ul>  
  
  <form class="form-horizontal" role="form" action="../Stud_details" method="post">
 
  <div class="tab-content">  
    <div id="stud_knowledge" class="tab-pane fade in active">  
 <div class="container">
 
       <div class="row" >
       

                <div class="col-sm-4 col-sm-offset-0" style="background-color: rgba(255,255,255,0);border-radius: 10px;">
                
  <%
  HttpSession sess=request.getSession(false);
  %>                
                     
                        
                        <div class="form-group">
                            <label for="name" class="control-label">Name</label>
                           
                            <input type="text" class="form-control" readonly value='<%=RecordsIMPL.getName((String)sess.getAttribute("user"))%>' id="name" name="name" required placeholder="Enter Student Name">
                        
                        </div>
                        
                        <div class="form-group">
                            <label for="rno" class="control-label">Roll No.</label>
                            <input type="text" class="form-control" readonly id="rno" value='<%=sess.getAttribute("user")%>' name="rno" required placeholder="Enter Student Roll. No.">
                        </div>
                        
                        
                        
                        <table class = "table table-bordered">
		    							<thead>
		    								<tr>
		    									<th>Knowledge Parameters</th>
		    									<th>Poor (1)</th>
		    									<th>Satisfactory (2)</th>
		    									<th>Good (3)</th>
		    									<th>VeryGood (4)</th>
		    									<th>Excellent (5)</th>
		    								</tr>
		    							</thead>
		    							<tbody>
		    								<tr>
		    								   
		    									<td>Understanding of data structures</td>
		    									
		    									<td><input type = "radio" name="data_structures" class = "form" id="" value="1"></td>
		    									<td><input type = "radio" name="data_structures" class = "form" id="" value="2"></td>
		    									<td><input type = "radio" name="data_structures" class = "form" id="" value="3"></td>
		    									<td><input type = "radio" name="data_structures" class = "form" id="" value="4"></td>
		    									<td><input type = "radio" name="data_structures" class = "form" id="" value="5"></td>

		    								</tr>
		    								<tr>
		    									<td>Design and evaluation of algorithms</td>
		    									
		    									<td><input type = "radio" class = "form" name="algorithms"  id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="algorithms"  id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="algorithms"  id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="algorithms"  id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="algorithms"  id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Logic building and programming paradigms</td>
		    		
		    									<td><input type = "radio" class = "form" name="logic_building" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="logic_building" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="logic_building" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="logic_building" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="logic_building" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Computer Architecture and Organization</td>
		    							
		    									<td><input type = "radio" class = "form" name="cao" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Database Management System</td>
		    								
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Operating System</td>
		    							
		    									<td><input type = "radio" class = "form" name="os" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Networks</td>
		    								
		    									<td><input type = "radio" class = "form" name="networks" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="networks" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="networks" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="networks" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="networks" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Network and Information Security</td>
		    					
		    									<td><input type = "radio" class = "form" name="info_security" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="info_security" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="info_security" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="info_security" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="info_security" id="" value="5"></td>
		    			
		    								</tr>
		    								<tr>
		    									<td>Software Development Process</td>
		    				
		    									<td><input type = "radio" class = "form" name="software_dev" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="software_dev" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="software_dev" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="software_dev" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="software_dev" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Software Project Management</td>
		    					
		    									<td><input type = "radio" class = "form" name="software_project" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="software_project" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="software_project" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="software_project" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="software_project" id="" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Mobility / Ubiquitous computing</td>
		    				
		    									<td><input type = "radio" class = "form" name="mobility" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="mobility" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="mobility" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="mobility" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="mobility" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Data Mining &amp; Knowledge management</td>
		    					
		    									<td><input type = "radio" class = "form" name="data_mining" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="data_mining" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="data_mining" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="data_mining" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="data_mining" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Software metrics</td>
		    			
		    									<td><input type = "radio" class = "form" name="software_metrics" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="software_metrics" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="software_metrics" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="software_metrics" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="software_metrics" id="" value="5"></td>
		    				
		    								</tr>
		    								<tr>
		    									<td>Understanding of internet and its functioning</td>
		    
		    									<td><input type = "radio" class = "form" name="internet" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="internet" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="internet" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="internet" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="internet" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Application development using C/C++/PHP/Java/.Net etc.</td>
		    					
		    									<td><input type = "radio" class = "form" name="application_dev" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="application_dev" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="application_dev" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="application_dev" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="application_dev" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Understanding of Business and Financial terms</td>
		    		
		    									<td><input type = "radio" class = "form" name="business_financial" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="business_financial" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="business_financial" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="business_financial" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="business_financial" id="" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Business Intelligence</td>
		    						
		    									<td><input type = "radio" class = "form" name="busi_intelligence" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="busi_intelligence" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="busi_intelligence" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="busi_intelligence" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="busi_intelligence" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Human Resource Management / Organization Behavior</td>
		    				
		    									<td><input type = "radio" class = "form" name="human_resource" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="human_resource" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="human_resource" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="human_resource" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="human_resource" id="" value="5"></td>
		    				
		    								</tr>
		    								<tr>
		    									<td>Modeling &amp; Simulation</td>
		    			
		    									<td><input type = "radio" class = "form" name="modeling_simulation" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="modeling_simulation" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="modeling_simulation" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="modeling_simulation" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="modeling_simulation" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Enterprise Resource Planning</td>
		    
		    									<td><input type = "radio" class = "form" name="enterprise_resource" value="1"></td>
		    									<td><input type = "radio" class = "form" name="enterprise_resource" value="2"></td>
		    									<td><input type = "radio" class = "form" name="enterprise_resource" value="3"></td>
		    									<td><input type = "radio" class = "form" name="enterprise_resource" value="4"></td>
		    									<td><input type = "radio" class = "form" name="enterprise_resource" value="5"></td>
		    			
		    								</tr>
		    							</tbody>
		    						</table>            
                        
                        <div class="form-group">
                        
                       
                        <a class ="btn btn-primary btn-block" data-toggle="tab" type="submit" href="#stud_skills">Next -></a>
                         
                        </div>
                   
                </div>
            </div>
        </div>
    </div>  

    <div id="stud_skills" class="tab-pane fade">  
          <div class="col-sm-4 col-sm-offset-0" style="background-color: rgba(255,255,255,0);border-radius: 10px;">
                        
                        <div class="form-group">
                            <label for="name" class="control-label">Name</label>
                            <input type="text" readonly value='<%=RecordsIMPL.getName((String)sess.getAttribute("user"))%>' class="form-control"  id="name" name="name" required placeholder="Enter Student Name">
                        </div>
                        <div class="form-group">
                            <label for="rno" class="control-label">Roll. No.</label>
                            <input type="text" readonly value='<%=sess.getAttribute("user")%>'  class="form-control" id="rno" name="rno" required placeholder="Enter Student Roll. No.">
                        </div>
                        
                         <table class = "table table-bordered">
		    							<thead>
		    								<tr>
		    									<th>Knowledge Parameters</th>
		    									<th>Poor (1)</th>
		    									<th>Satisfactory (2)</th>
		    									<th>Good (3)</th>
		    									<th>VeryGood (4)</th>
		    									<th>Excellent (5)</th>
		    								</tr>
		    							</thead>
		    							<tbody>
		    								<tr>
                                                <td>IT Skills</td>
		    									
		    									<td><input type = "radio" name="IT-skills" class = "form" id="" value="1"></td>
		    									<td><input type = "radio" name="IT-skills" class = "form" id=""  value="2"></td>
		    									<td><input type = "radio" name="IT-skills" class = "form" id=""  value="3"></td>
		    									<td><input type = "radio" name="IT-skills" class = "form" id=""  value="4"></td>
		    									<td><input type = "radio" name="IT-skills" class = "form" id="" value="5"></td>
                                                
		    								</tr>
		    								<tr>
		    									<td>Listening Skills</td>
		    					
		    									<td><input type = "radio" class = "form" name="listening"  id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="listening" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="listening"  id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="listening"  id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="listening"  id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Oral Communication</td>
		    	
		    									<td><input type = "radio" class = "form" name="oral_comm" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="oral_comm" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="oral_comm" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="oral_comm" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="oral_comm" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Body Language</td>
		    							
		    									<td><input type = "radio" class = "form" name="body_lang" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="body_lang" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="body_lang" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="body_lang" id=""value="4"></td>
		    									<td><input type = "radio" class = "form" name="body_lang" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Written Communication</td>
		    						
		    									<td><input type = "radio" class = "form" name="written_comm" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="written_comm" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="written_comm" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="written_comm" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="written_comm" id="" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Conceptual Skills</td>
		    						
		    									<td><input type = "radio" class = "form" name="conceptual" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="conceptual" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="conceptual" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="conceptual" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="conceptual" id="" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Emotional Intelligence</td>
		    			
		    									<td><input type = "radio" class = "form" name="EI" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="EI" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="EI" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="EI" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="EI" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Conflict Resolution Skills</td>
		    					
		    									<td><input type = "radio" class = "form" name="conflict_reso" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="conflict_reso" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="conflict_reso" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="conflict_reso" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="conflict_reso" id="" value="5"></td>
		    				
		    								</tr>
		    								<tr>
		    									<td>Problem Solving Skills</td>
		    							
		    									<td><input type = "radio" class = "form" name="prob_solving" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="prob_solving" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="prob_solving" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="prob_solving" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="prob_solving" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Organizing skills - Implementation</td>
		    					
		    									<td><input type = "radio" class = "form" name="organising" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="organising" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="organising" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="organising" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="organising" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Interpersonal skills</td>
		    			
		    									<td><input type = "radio" class = "form" name="inter-personal" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="inter-personal" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="inter-personal" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="inter-personal" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="inter-personal" id="" value="5"></td>
		    		
		    								</tr>
		    								<tr>
		    									<td>Social Skills</td>
		    							
		    									<td><input type = "radio" class = "form" name="social" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="social" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="social" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="social" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="social" id="" value="5"></td>
		    				
		    								</tr>
		    								<tr>
		    									<td>Innovative - Out of Box Thinking</td>
		    					
		    									<td><input type = "radio" class = "form" name="innovative" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="innovative" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="innovative" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="innovative" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="innovative" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Proactive Preventive Approach</td>
		    					
		    									<td><input type = "radio" class = "form" name="pro-active" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="pro-active" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="pro-active" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="pro-active" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="pro-active" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Multi-tasking</td>
		    					
		    									<td><input type = "radio" class = "form" name="multi_tasking" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="multi_tasking" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="multi_tasking" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="multi_tasking" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="multi_tasking" id="" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Eye for details &amp; Observational Skills</td>
		    			
		    									<td><input type = "radio" class = "form" name="observational"  id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="observational" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="observational"  id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="observational"  id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="observational" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Analytical Skills</td>
		    									<td><input type = "radio" class = "form" name="analytical" value="1"></td>
		    									<td><input type = "radio" class = "form" name="analytical" value="2"></td>
		    									<td><input type = "radio" class = "form" name="analytical" value="3"></td>
		    									<td><input type = "radio" class = "form" name="analytical" value="4"></td>
		    									<td><input type = "radio" class = "form" name="analytical" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Negotiating Skills</td>
		    			
		    									<td><input type = "radio" class = "form" name="negotiating" value="1"></td>
		    									<td><input type = "radio" class = "form" name="negotiating" value="2"></td>
		    									<td><input type = "radio" class = "form" name="negotiating" value="3"></td>
		    									<td><input type = "radio" class = "form" name="negotiating" value="4"></td>
		    									<td><input type = "radio" class = "form" name="negotiating" value="5"></td>
		    					
		    								</tr>
		    								<tr>
		    									<td>Robust common sense</td>
		    					
		    									<td><input type = "radio" class = "form" name="robust_common" value="1"></td>
		    									<td><input type = "radio" class = "form" name="robust_common" value="2"></td>
		    									<td><input type = "radio" class = "form" name="robust_common" value="3"></td>
		    									<td><input type = "radio" class = "form" name="robust_common" value="4"></td>
		    									<td><input type = "radio" class = "form" name="robust_common" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Time Management</td>
		    									
		    									<td><input type = "radio" class = "form" name="time_manage"  value="1"></td>
		    									<td><input type = "radio" class = "form" name="time_manage" value="2"></td>
		    									<td><input type = "radio" class = "form" name="time_manage"  value="3"></td>
		    									<td><input type = "radio" class = "form" name="time_manage" value="4"></td>
		    									<td><input type = "radio" class = "form" name="time_manage"  value="5"></td>
		    									
		    								</tr>
		    							
		    							</tbody>
		    						</table>  
                        
                        <div class="form-group">
                         
                        <a class ="btn btn-primary btn-block" data-toggle="tab" href="#stud_attitude">Next -></a>
   
                        </div>
                       
                   
                </div>

    </div>  
    <div id="stud_attitude" class="tab-pane fade">  
    
        <div class="container">   
     
       <div class="row" >
             
               
                
                <div class="col-sm-4 col-sm-offset-0" style="background-color: rgba(255,255,255,0);border-radius: 10px;">
       
                        
                       <div class="form-group">
                            <label for="name" class="control-label">Name</label>
                            <input type="text" readonly value='<%=RecordsIMPL.getName((String)sess.getAttribute("user"))%>' class="form-control"  id="name" name="name" required placeholder="Enter Student Name">
                        </div>
                        <div class="form-group">
                            <label for="rno" class="control-label">Roll. No.</label>
                            <input type="text" readonly value='<%=sess.getAttribute("user")%>'  class="form-control" id="rno" name="rno" required placeholder="Enter Student Roll. No.">
                        </div>
                          <table class = "table table-bordered">
		    							<thead>
		    								<tr>
		    									<th>Knowledge Parameters</th>
		    									<th>Poor (1)</th>
		    									<th>Satisfactory (2)</th>
		    									<th>Good (3)</th>
		    									<th>VeryGood (4)</th>
		    									<th>Excellent (5)</th>
		    								</tr>
		    							</thead>
		    							<tbody>
		    								<tr>
		    								   
		    									<td>Positive thinking</td>
		    									
		    									<td><input type = "radio" name="positive_thinking" class = "form" id="" value="1"></td>
		    									<td><input type = "radio" name="positive_thinking" class = "form" id=""  value="2"></td>
		    									<td><input type = "radio" name="positive_thinking" class = "form" id=""  value="3"></td>
		    									<td><input type = "radio" name="positive_thinking" class = "form" id=""  value="4"></td>
		    									<td><input type = "radio" name="positive_thinking" class = "form" id="" value="5"></td>

		    								</tr>
		    								<tr>
		    									<td>Integrity</td>
		    									
		    									<td><input type = "radio" class = "form" name="integrity"  id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="integrity" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="integrity"  id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="integrity"  id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="integrity"  id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Dependability</td>
		    								
		    									<td><input type = "radio" class = "form" name="dependability" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="dependability" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="dependability" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="dependability" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="dependability" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Discipline</td>
		    									
		    									<td><input type = "radio" class = "form" name="discipline" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="discipline" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="discipline" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="discipline" id=""value="4"></td>
		    									<td><input type = "radio" class = "form" name="discipline" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Commitment to excellence</td>
		    									
		    									<td><input type = "radio" class = "form" name="excellence" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="excellence" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="excellence" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="excellence" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="excellence" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Perseverance</td>
		    									
		    									<td><input type = "radio" class = "form" name="perseverance" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="perseverance" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="perseverance" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="perseverance" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="perseverance" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Enthusiasm/ Passion</td>
		    									
		    									<td><input type = "radio" class = "form" name="enthusiasm" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="enthusiasm" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="enthusiasm" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="enthusiasm" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="enthusiasm" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Self-confidence</td>
		    									
		    									<td><input type = "radio" class = "form" name="self-confidence" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="self-confidence" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="self-confidence" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="self-confidence" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="self-confidence" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Initiative</td>
		    									
		    									<td><input type = "radio" class = "form" name="initiative" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="initiative" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="initiative" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="initiative" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="initiative" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Farsightedness</td>
		    									
		    									<td><input type = "radio" class = "form" name="farsightedness" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="farsightedness" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="farsightedness" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="farsightedness" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="farsightedness" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Team Spirit</td>
		    									
		    									<td><input type = "radio" class = "form" name="team_spirit" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="team_spirit" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="team_spirit" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="team_spirit" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="team_spirit" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Desire to learn</td>
		    									
		    									<td><input type = "radio" class = "form" name="desire_learn" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="desire_learn" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="desire_learn" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="desire_learn" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="desire_learn" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Open Mindedness</td>
		    								
		    									<td><input type = "radio" class = "form" name="open_mindedness" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="open_mindedness" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="open_mindedness" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="open_mindedness" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="open_mindedness" id="" value="5"></td>
		    									
		    								</tr>
		    								<tr>
		    									<td>Adaptability</td>
		    									
		    									<td><input type = "radio" class = "form" name="adaptability" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="adaptability" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="adaptability" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="adaptability" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="adaptability" id="" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Empathy</td>
		    									
		    									<td><input type = "radio" class = "form" name="empathy" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="empathy" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="empathy" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="empathy" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="empathy" id="" value="5"></td>
		    						
		    								</tr>
		    								<tr>
		    									<td>Concern for others</td>
		    									
		    									<td><input type = "radio" class = "form" name="concern"  id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="concern" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="concern"  id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="concern"  id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="concern" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Sense of Justice / Fairness</td>
		    									
		    									<td><input type = "radio" class = "form" name="fairness" value="1"></td>
		    									<td><input type = "radio" class = "form" name="fairness" value="2"></td>
		    									<td><input type = "radio" class = "form" name="fairness" value="3"></td>
		    									<td><input type = "radio" class = "form" name="fairness" value="4"></td>
		    									<td><input type = "radio" class = "form" name="fairness" value="5"></td>
		    							
		    								</tr>
		    								<tr>
		    									<td>Modesty</td>
		    									
		    									<td><input type = "radio" class = "form" name="modesty" value="1"></td>
		    									<td><input type = "radio" class = "form" name="modesty" value="2"></td>
		    									<td><input type = "radio" class = "form" name="modesty" value="3"></td>
		    									<td><input type = "radio" class = "form" name="modesty" value="4"></td>
		    									<td><input type = "radio" class = "form" name="modesty" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Ethics</td>
		    									
		    									<td><input type = "radio" class = "form" name="ethics" value="1"></td>
		    									<td><input type = "radio" class = "form" name="ethics" value="2"></td>
		    									<td><input type = "radio" class = "form" name="ethics" value="3"></td>
		    									<td><input type = "radio" class = "form" name="ethics" value="4"></td>
		    									<td><input type = "radio" class = "form" name="ethics" value="5"></td>
		    								
		    								</tr>
		    								<tr>
		    									<td>Cleanliness</td>
		    									
		    									<td><input type = "radio" class = "form" name="cleanliness"  value="1"></td>
		    									<td><input type = "radio" class = "form" name="cleanliness" value="2"></td>
		    									<td><input type = "radio" class = "form" name="cleanliness"  value="3"></td>
		    									<td><input type = "radio" class = "form" name="cleanliness" value="4"></td>
		    									<td><input type = "radio" class = "form" name="cleanliness"  value="5"></td>
		    							
		    								</tr>
		    							</tbody>
		    						</table>    
                         
                        <div class="form-group">
                            <input type="submit" name="sub" class="btn btn-primary btn-block" value="Submit">
                        </div>
                       
                    
                </div>
            </div>
        </div>            
    </div>  
  
  </div>  
   </form>
</div>  
</div>
<div style="clear:both"></div>



 <script>
$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});
</script>

        </body>
</html>
 
