<%@ page import="java.sql.* "%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>
<%@ page import="com.ppi.database.ConnectionFactory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico"/>
          <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
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
     
    </head>
    <body background="images/e.jpg">
       <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">PPI PORTAL</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="">Home</a></li>

      
    </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><%=session.getAttribute("user").toString().toUpperCase()%></a></li>
      <li><a href="../logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
    </ul>
    
  </div>
</nav>

<%
           String id=session.getAttribute("user").toString();        
     %>  
     
     
     <jsp:useBean id="ExpertDao" class="com.ppi.impl.ExpertIMPL"></jsp:useBean>
<%
		request.setAttribute("expert1", ExpertDao.getStudents(id, 0));
        request.setAttribute("expert2", ExpertDao.getStudents(id, 1));
	%>
     
             
<div style="width: 100%;">
   <div style="float:left; width: 20%">
            <div id="sidebar_collapse" style="background-color: lightblue;margin-left: 0px"  class="col-sm-4 col-lg-12 sidebar collapse in">
            <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">PPI remaining students</h3>
            </div>

            <ul class="nav menu" style="margin-top: 0px">
             <c:forEach items="${expert1}" var="studs">
             <li><a href="expert.jsp?roll=${studs.roll}&name=${studs.name}">${studs.name}</a></li>
		</c:forEach>   
             
    </ul>
    <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">PPI Completed students</h3>
            </div>

            <ul class="nav menu" style="margin-top: 10px">
            <c:forEach items="${expert2}" var="studs">
             <li><a href="edit.jsp?roll=${studs.roll}">${studs.name} <span class="glyphicon glyphicon-edit"></span></a></li>
		</c:forEach> 

    </ul>
        </div>
   </div>
   <div style="float:right; width:80%">
   <div class="container">  
  
  <ul class="nav nav-tabs">  
    <li><a data-toggle="tab" href="#stud_knowledge">Student Knowledge</a></li>  
    <li><a data-toggle="tab" href="#stud_skills">Student Skills</a></li>  
    <li><a data-toggle="tab" href="#stud_remarks">Student Remarks</a></li>    
  </ul>  
  
  <form class="form-horizontal" role="form" action="../ExpDetails" method="post">
 
  <div class="tab-content">  
    <div id="stud_knowledge" class="tab-pane fade in active">  
 <div class="container">
 
       <div class="row" >
       

                <div class="col-sm-4 col-sm-offset-0" style="background-color: rgba(255,255,255,0);border-radius: 10px;">
                
                  
                     
                        
                        <div class="form-group">
                            <label for="name" class="control-label">Name</label>
                            <%if(request.getParameter("name")==null){%>
                            	<input type="text" class="form-control" readonly value=''  id="name" name="name" required placeholder="Enter Student Name">
                            	
                            <% }else{%>
                            <input type="text" class="form-control" readonly value='<%=request.getParameter("name")%>' id="name" name="name" required placeholder="Enter Student Name">
                        
                             <%} %>
                        </div>
                        
                        <div class="form-group">
                            <label for="rno" class="control-label">Roll No.</label>
                            <input type="text" class="form-control" readonly id="rno" value='<%= request.getParameter("roll")%>' name="rno" required placeholder="Enter Student Roll. No.">
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

                                                <td>Data Structures</td>
                                                <td><input type = "radio" name="data" class = "form" id="" value="1"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="2"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="3"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="4"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="5"></td>
		    									
		    									</tr>
                     
                        <tr>
                       <td>Logic Building</td>
                                                <td><input type = "radio" class = "form" name="logic" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="5"></td>
                        </tr>
                        <tr>
                       
                         <td>Computer arch. and Organization</td>
                                                <td><input type = "radio" class = "form" name="cao" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="5"></td>
                        </tr>
                        <tr>
                         <td>Database Management Systems</td>
                                                <td><input type = "radio" class = "form" name="dbms" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="5"></td>
                       </tr>
                     
                       <tr>
                         <td>Operating Systems</td>
                                                <td><input type = "radio" class = "form" name="os" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="5"></td>
                        </tr>
                        <tr>
                         <td>Computer Networks</td>
                                                <td><input type = "radio" class = "form" name="cn" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="5"></td>
                        </tr>
                        
                         <tr>
                         
                            <td>Application development using C/C++/PHP/Java</td>
                                                <td><input type = "radio" class = "form" name="app" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="5"></td>
                       
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
                            <input type="text" readonly value='<%=request.getParameter("name")%>' class="form-control"  id="name" name="name" required placeholder="Enter Student Name">
                        </div>
                        <div class="form-group">
                            <label for="rno" class="control-label">Roll. No.</label>
                            <input type="text" readonly value='<%=request.getParameter("roll") %>'  class="form-control" id="rno" name="rno" required placeholder="Enter Student Roll. No.">
                        </div>
                         <table class = "table table-bordered">
		    							<thead>
		    								<tr>
		    									<th>Skill Parameters</th>
		    									<th>Poor (1)</th>
		    									<th>Satisfactory (2)</th>
		    									<th>Good (3)</th>
		    									<th>VeryGood (4)</th>
		    									<th>Excellent (5)</th>
		    								</tr>
		    							</thead>
		    							<tbody>
		    								<tr>
                        
                           <td>Team Spirit</td>
                                                <td><input type = "radio" class = "form" name="team" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="team" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="team" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="team" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="team" id="" value="5"></td>
		    									</tr>
		    									<tr>
                           <td>Enthusiasm</td>
                                                <td><input type = "radio" class = "form" name="enth" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="enth" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="enth" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="enth" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="enth" id="" value="5"></td>
                        </tr>
                        <tr>
                         <td>Self Confidence</td>
                                                <td><input type = "radio" class = "form" name="conf" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="conf" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="conf" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="conf" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="conf" id="" value="5"></td>
                        </tr>
                        <tr>
                         <td>Cleanliness</td>
                                                <td><input type = "radio" class = "form" name="clean" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="clean" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="clean" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="clean" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="clean" id="" value="5"></td>
                        </tr>
                       <tr>
                        
                         <td>Oral Communication</td>
                                                <td><input type = "radio" class = "form" name="oral" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="oral" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="oral" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="oral" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="oral" id="" value="5"></td>
                        </tr>
                        <tr>
                        
                         <td>Body Language</td>
                                                <td><input type = "radio" class = "form" name="lang" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="lang" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="lang" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="lang" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="lang" id="" value="5"></td>
                        </tr>
                        <tr>
                        
                         <td>Problem Solving</td> 
                                                <td><input type = "radio" class = "form" name="prob" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="prob" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="prob" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="prob" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="prob" id="" value="5"></td>
                        </tr>
                        <tr>
                        <td>Analytical Skills</td>
                                                <td><input type = "radio" class = "form" name="skill" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="skill" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="skill" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="skill" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="skill" id="" value="5"></td>
                        </tr>
                        </tbody>
		    						</table>
                        
                        <div class="form-group">
                         
                        <a class ="btn btn-primary btn-block" data-toggle="tab" href="#stud_remarks">Next -></a>
   
                        </div>
                       
                   
                </div>

    </div>  
    <div id="stud_remarks" class="tab-pane fade">  
    
        <div class="container">   
     
       <div class="row" >
                <br><br>
               
                
                <div class="col-sm-4 col-sm-offset-0" style="background-color: rgba(255,255,255,0);border-radius: 10px;">
       
                        
                          <div class="form-group">
                            <label for="id" class="control-label">Student ID</label>
                            <input type="text" class="form-control" readonly value='<%=request.getParameter("roll")%>' id="id" name="id" required placeholder="Enter Student ID">
                        </div>
                        
                       <div class="form-group">
                            <label for="remarks">Student Remarks<font color="red">*</font></label>
                            <textarea class="form-control" rows="10" name="remarks" required maxlength="500" ></textarea>
                        </div> 
                      
                        
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
</div>
<div style="clear:both"></div>



        </body>
</html>
 
