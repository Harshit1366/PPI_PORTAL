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
  	</head>
  	<body>
  		<header>
	  		<div class="jumbotron" id="orange"></div>
	  		<div class="jumbotron text-center" id="maroon">
	  		<h2>The NorthCap University</h2>
	  		</div>
  		</header>
  		
  	
  	<%
                    String TIME_SERVER = "time-a.nist.gov";
                    NTPUDPClient timeClient = new NTPUDPClient();
                    InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
                    TimeInfo timeInfo = timeClient.getTime(inetAddress);
                    long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                    Date time = new Date(returnTime);
                    System.out.println(time);
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                %>
                
                
  		<div class = "container-fluid box">
  			<div class="row">
  				<div class="col-sm-offset-4 col-sm-4">
  				<h1 class="text-center">Login</h1>
  				<form action="LoginService" method="POST">
                        <input type='hidden' value=<%=ipAddress%> name='client_add' >
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
  						<div class="forgot"><a href="accounts/forgot_password.jsp">Forgot your password?</a></div>
                  
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