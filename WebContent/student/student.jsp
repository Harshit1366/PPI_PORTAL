<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ppi.impl.RecordsIMPL" %>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="css/bootstrap.css" rel="stylesheet">
          <link href="css/bootstrap.min.css" rel="stylesheet">
           <link href="css/bootstrap-theme.css" rel="stylesheet">
            <link href="css/bootstrap-theme.min.css" rel="stylesheet">
           <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </head>
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
      <li class="active"><a href="../account/change_password.jsp">Change Password</a></li>
    </ul>
      
      <ul class="nav navbar-nav navbar-right">
     <li><a href="#"><%=session.getAttribute("user").toString().toUpperCase()%></a></li>
      <li><a href="../account/logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
    </ul>
    
  </div>
</nav>

             <!-- Sidebar -->
             
        <div id="sidebar_collapse" style="background-color: lightblue;margin-top: 100px"  class="col-sm-3 col-lg-2 sidebar collapse in">
            <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">Dashboard</h3>
            </div>

            <ul class="nav menu" style="margin-top: 10px">
             
                <li id="score"><a href="../PPIScore"><span class="glyphicon glyphicon-book"></span> Your Score</a></li>
                <li id="cv"><a href="../DownloadCV"><span class="glyphicon glyphicon-file"></span> Download CV Format</a></li>
                <%if(RecordsIMPL.getAssess(session.getAttribute("user").toString())==1){ %>
                <li id="pdf"><a href="../PDF_SelfSheet"><span class="glyphicon glyphicon-comment"></span> Self-Assessment PDF</a></li>
                <%} else{
                %>
                <li id="self_assess"><a href="selfAssess.jsp"><span class="glyphicon glyphicon-comment"></span> Self-Assessment Form</a></li>
                <%} %><li id="feedback"><a href="feedback.jsp"><span class="glyphicon glyphicon-comment"></span> Feedback Form</a></li>
             
            </ul>
        </div>
        <!-- Sidebar ends -->
       
    </body>
</html>

