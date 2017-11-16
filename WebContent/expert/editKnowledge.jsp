<%@ page import="java.sql.* "%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*" %>
<%@ page import="com.ppi.database.ConnectionFactory" %>
<%@page import="com.ppi.model.ExpKnowledge"%>
<%@page import="com.ppi.impl.ExpRecordsIMPL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> 
  <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
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
      <li class="active"><a href="../expert/expertKnowledge.jsp">Home</a></li>

      
    </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><%=session.getAttribute("user").toString().toUpperCase()%></a></li>
      <li><a href="../account/logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
    </ul>
    
  </div>
</nav>

<%

           String id=session.getAttribute("user").toString();
           String roll=request.getParameter("roll");
           ExpKnowledge k = ExpRecordsIMPL.getRecordById(roll);      
     %>  
     
     
     <jsp:useBean id="ExpertDao" class="com.ppi.impl.ExpertIMPL"></jsp:useBean>
<%
		request.setAttribute("expert1", ExpertDao.getStudents(id, 0));
        request.setAttribute("expert2", ExpertDao.getStudents(id, 1));
	%>
     
             
<div style="width: 100%;">
   <div style="float:left; width: 20%">
            <div id="sidebar"  class="col-sm-4 col-lg-12 sidebar collapse in">
            <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">PPI remaining students</h3>
            </div>

            <ul class="nav menu" style="margin-top: 0px">
             <c:forEach items="${expert1}" var="studs">
             <li>
             <form method="post" action="../expert/expertKnowledge.jsp">
             <input type="hidden" value="<c:out value="${studs.roll}"/>" name="roll"/>
             <input type="hidden" value="<c:out value="${studs.name}"/>" name="name"/>
             <input type="submit" value="${studs.roll}">
             </form>
             </li>
            
		</c:forEach>   
             
    </ul>
    <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">PPI Completed students</h3>
            </div>

            <ul class="nav menu" style="margin-top: 10px">
            <c:forEach items="${expert2}" var="studs">
            <li>
            <form method="post" action="../expert/editKnowledge.jsp">
             <input type="hidden" value="<c:out value="${studs.roll}"/>" name="roll"/>
             <input type="hidden" value="<c:out value="${studs.name}"/>"  name="name"/>
             <input type="submit" value="${studs.roll}"> <span class="glyphicon glyphicon-edit"></span>
             </form>
             </li>
         
		</c:forEach> 

    </ul>
        </div>
   </div>
   
   <div style="float:right; width:80%">
   <div class="container">  
   <%if(request.getParameter("roll")!=null){%> 
  <form class="form-horizontal" role="form" action="../EditKnowledge" method="post">

                <div class="col-sm-4 col-sm-offset-0" style="background-color: rgba(255,255,255,0);border-radius: 10px;">
      
                        
                        <div class="form-group">
                            <label for="name" class="control-label">Name</label>
                            <input type="text" class="form-control" readonly value='<%=k.getName()%>' id="name" name="name" required placeholder="Enter Student Name">
                        </div>
                        
                        <div class="form-group">
                            <label for="rno" class="control-label">Roll No.</label>
                            <input type="text" class="form-control" readonly id="rno" value='<%=k.getRoll()%>' name="rno" required placeholder="Enter Student Roll. No.">
                        </div>
            
                           <table class = "table table-bordered">
		    							<thead>
		    								<tr>
		    									<th>Knowledge Parameters</th>
		    									<th>Previous Score</th>
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
                                                <td><input type = "radio" name="data" class = "form" id="" value="<%=k.getData()%>" checked> <%=k.getData()%></td>
                                                <td><input type = "radio" name="data" class = "form" id="" value="1"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="2"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="3"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="4"></td>
		    									<td><input type = "radio" name="data" class = "form" id="" value="5"></td>
		    									
		    									</tr>
                     
                        <tr>
                       <td>Logic Building</td>
                                                <td><input type = "radio" name="logic" class = "form" id="" value="<%=k.getLogic()%>" checked> <%=k.getLogic()%></td>
                                                <td><input type = "radio" class = "form" name="logic" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="logic" id="" value="5"></td>
                        </tr>
                        <tr>
                       
                         <td>Computer Architecture and Organization</td>
                                                <td><input type = "radio" name="cao" class = "form" id="" value="<%=k.getCao()%>" checked> <%=k.getCao()%></td>
                                                <td><input type = "radio" class = "form" name="cao" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="cao" id="" value="5"></td>
                        </tr>
                        <tr>
                         <td>Database Management Systems</td>
                                                <td><input type = "radio" name="dbms" class = "form" id="" value="<%=k.getDbms()%>" checked> <%=k.getDbms()%></td>
                                                <td><input type = "radio" class = "form" name="dbms" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="dbms" id="" value="5"></td>
                       </tr>
                     
                       <tr>
                         <td>Operating Systems</td>
                                                <td><input type = "radio" name="os" class = "form" id="" value="<%=k.getOs()%>" checked> <%=k.getOs()%></td>
                                                <td><input type = "radio" class = "form" name="os" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="os" id="" value="5"></td>
                        </tr>
                        <tr>
                         <td>Computer Networks</td>
                                                <td><input type = "radio" name="cn" class = "form" id="" value="<%=k.getCn()%>" checked> <%=k.getCn()%></td>
                                                <td><input type = "radio" class = "form" name="cn" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="cn" id="" value="5"></td>
                        </tr>
                        
                         <tr>
                         
                            <td>Application development using C/C++/PHP/Java</td>
                                                <td><input type = "radio" name="app" class = "form" id="" value="<%=k.getApp()%>" checked> <%=k.getApp()%></td>
                                                <td><input type = "radio" class = "form" name="app" id="" value="1"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="2"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="3"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="4"></td>
		    									<td><input type = "radio" class = "form" name="app" id="" value="5"></td>
                       
                       </tr>  
                   
		    							</tbody>
		    						</table>  
		    						<input type="hidden" value="<%=request.getParameter("roll")%>" name="roll"/>
                        <input type="hidden" value="<%=request.getParameter("name")%>" name="name"/>
                        <div class="form-group">
                        <input type="submit"  name="sub" class="btn btn-primary btn-block"  value="Next ->">    
</div>
</div>
   </form>
   <%} %>
</div>  
   </div>
</div>
<div style="clear:both"></div>



        </body>
</html>
 
