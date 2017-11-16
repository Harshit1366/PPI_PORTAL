<%@page import="java.sql.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="com.ppi.impl.EmailService"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.UUID"%>
<%@page import="java.net.InetAddress"%>
<%@page import="org.apache.commons.net.ntp.TimeInfo"%>
<%@page import="org.apache.commons.net.ntp.NTPUDPClient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset New Password</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
   <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/login_css.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.container {
	max-width: 700px;
	position: relative;
	top: 50%;
}

body {
	background-color: #ffa830;
	background-image: url("../resources/images/background.jpg");
}

a {
	color: #000000;
}

input {
	border-style: none;
}
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
	<jsp:useBean id="dao" class="com.ppi.impl.LoginIMPL"></jsp:useBean>
	<%
		String token = request.getParameter("token");
		if (token.isEmpty()) {
			return;
		}
		
		Object[] data = dao.forgotResponse(token);
		if(null==data){
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Request was closed earlier..');");
			out.println("location='../index.jsp';");
			out.println("</script>");
			return;
		}
		
		Timestamp date =(Timestamp)data[1];
		System.out.println("DATA"+data[0].toString()+" "+data[1].toString());
		String TIME_SERVER = "time-a.nist.gov";
		NTPUDPClient timeClient = new NTPUDPClient();
		InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
		TimeInfo timeInfo = timeClient.getTime(inetAddress);
		long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
		long duration = returnTime - date.getTime();
		System.out.println(data.length);
		if (data.length != 0) {
			if (duration <= 86400000 && duration > 0) {
	%>
    <header>
		<div class="jumbotron" id="orange"></div>
		<div class="jumbotron text-center" id="maroon">
            <h2>The NorthCap University</h2>
		</div>
    </header>
       
  		<div class = "container-fluid box">
  			<div class="row">
  				<div class="col-sm-offset-4 col-sm-4">
  				<form action="../ResetPassword" method="POST">
                    <h1 class="text-center">Reset Password</h1>    
  					<div class="form-group login">
  						<br>
  						<label class="col-sm-6">Enter new password:</label>
  						<input type="password" required placeholder="" name="password" class="form-control">
  						<br>
                        <label class="col-sm-8">Enter new password again:</label>
  						<input type="password" required placeholder="" name="password" class="form-control">
  						<br>
  						<div class="text-center">
  						<button type="submit" class=" btn btn-info">Submit</button>	</div>
  					</div>
  					</form>
  				</div>
  			</div>
  		</div>
<% 
		} else {
				UUID uuid = UUID.randomUUID();
				String newID = uuid.toString();
				String url = "http://localhost:8080/internship/accounts/reset_password_response.jsp?token=" + newID;
				String message = "Forgot Password? \n Here is a link to reset your password:" + url
						+ "\nIf password is not requested by you, please contact administrator.";
				String subject = "Requested to change password!";
				String username = data[0].toString();
				String email = dao.getEmailByUsername(username);
				boolean ifRefreshed = dao.reForgotRequest(newID, username, new Timestamp(returnTime));
				System.out.println("FROM JSP");
				EmailService.sendEmail("NCU: Forgot Password", message, email);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('The link has now expired. We have sent you another reset link.');");
				out.println("location='../index.jsp';");
				out.println("</script>");
				return;
			}

		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Invalid Data Found!');");
			out.println("location='../index.jsp';");
			out.println("</script>");

		}
	%>

</body>
    <footer>
		<p class="text-right">&copy; The NorthCap University, Gurugram</p>		 
	</footer>
</html>