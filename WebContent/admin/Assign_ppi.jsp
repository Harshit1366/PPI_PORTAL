<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ASSIGN PPI</title>
<link rel="shortcut icon" type="image/x-icon" href="../images/favicon.ico"/>
 <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/admin.css"> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> 
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
<body>
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
      <li class="active"><a href="admin_home.jsp">Home</a></li>
    </ul>
      
      <ul class="nav navbar-nav navbar-right">
     <li><a href="#"><%=session.getAttribute("user").toString().toUpperCase()%></a></li>
      <li><a href="../account/logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
    </ul>
    
  </div>
</nav>
      
      
         <!-- Sidebar -->
             
        <div id="sidebar" class="col-sm-3 col-lg-2 sidebar collapse in">
            <div style="margin-left: 0px">
                <h3 class="text-left" style="color: #555D50;">Dashboard</h3>
            </div>

            <ul class="nav menu" style="margin-top: 10px">
            <li id="upload"><a href="Upload.jsp"><span class="glyphicon glyphicon-book"></span> Upload Student List</a></li>
               <li id="upload"><a href="UploadExpert.jsp"><span class="glyphicon glyphicon-book"></span> Upload Expert List</a></li>
             <li id="addExpert"><a href="AddRoles.jsp"><span class="glyphicon glyphicon-book"></span> Add Expert/Student</a></li>
                 <li id="student"><a href="student_data.jsp"><span class="glyphicon glyphicon-book"></span> View Student Data</a></li>          
              <li id="expert"><a href="expert_data.jsp"><span class="glyphicon glyphicon-book"></span> View Expert Data</a></li>
              <li id="expert"><a href="Assign_ppi.jsp"><span class="glyphicon glyphicon-book"></span> Assign PPI</a></li>
          <li id="expert"><a href="assigned_students.jsp"><span class="glyphicon glyphicon-book"></span>View Assigned Students</a></li>
      <li id="expert"><a href="Analysis.jsp"><span class="glyphicon glyphicon-book"></span> Analysis of Students</a></li>
            </ul>
        </div>
        <!-- Sidebar ends --> 
 <div class = "row">
        <div class = " box col-sm-offset-2 col-sm-5">
            <form method="post" name="form1" enctype="multipart/form-data" action="../AssignService" > 
                &nbsp&nbsp&nbsp
                <br>
                <br>
                Number of students to be assigned PPI : <input type="text" name="no" maxlength='100' required>
                &nbsp&nbsp&nbsp
                <br>
                <br>
                Date of PPI : <input type="date" name="dop" required>
                &nbsp&nbsp&nbsp
                <br>
                <br>
                Upload list of experts : <input type="file" name="list" required>
                <br>
                <br>
                <input type="submit" value="Assign PPI" onclick="Check(document.form1.no)">
            </form>
        </div>
    </div>
    <script type="text/javascript">
		function Check(input) {
			var paswd = /[0-9]+/;
			if (input.value.match(paswd)) {
				return true;
			} else {
				alert('Number of students must be a number in digits.');
 				location="Assign_ppi.jsp";
				return false;
			}
		}
		</script>
</body>
    <footer>
		<p class="text-right">&copy; The NorthCap University, Gurugram</p>		 
	</footer>
</html>