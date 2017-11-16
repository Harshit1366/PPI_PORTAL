<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.commons.net.ntp.TimeInfo"%>
<%@page import="org.apache.commons.net.ntp.NTPUDPClient"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.InetAddress"%>
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="utf-8"> 
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico"/>
    	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    	<link rel="stylesheet" type="text/css" href="css/login_css.css">
    	<style>
			@import url('https://fonts.googleapis.com/css?family=Lora');
		</style>
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
  		<header>
	  		<div class="jumbotron" id="orange"></div>
	  		<div class="jumbotron text-center" id="maroon">
	  		<h2>The NorthCap University</h2>
	  		</div>
  		</header>
       
  		<div class = "container-fluid box">
  			<div class="row">
  				<div class="col-sm-offset-4 col-sm-4">
  				<h1 class="text-center">Login</h1>
  				<form action="LoginService" method="POST">
                        
  					<div class="form-group login">
	  					<select name="role" required class="form-control">
	                        <option value="">Please choose a category</option>
	                        <option value="ROLE_ADMIN">Administrator</option>
	                        <option value="ROLE_STUDENT">Student</option>
	                        <option value="ROLE_EXPERT">Expert</option>
	                    </select>
  						<br>
  						<label class="col-sm-2">Username:</label>
  						<input type="text" required placeholder="Enter your username" name="username" class="form-control">
  						<br>
  						<label class="col-sm-2">Password:</label>
  						<input type="password" required placeholder="Enter your password" name="password" class="form-control">
  						<br>
  						<div class="text-center">
  						<button type="submit" class=" btn btn-info">Submit</button>	</div>
  						<div class="forgot"><a href="account/forgot_password_request.jsp">Forgot your password?</a></div>
                  
  					</div>
  					</form>
  				</div>
  			</div>
  		</div>
  			
  	</body>
  	<footer>
		<p class="text-right">&copy; The NorthCap University, Gurugram</p>		 
	</footer>
</html>