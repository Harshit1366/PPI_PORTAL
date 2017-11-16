<%@page import="com.ppi.constants.Redirect"%>
<html>
<head>
<title>Home Page</title>
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
	transform: translateY(-50%);
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
	<jsp:useBean id="lao" class="com.ppi.impl.LoginIMPL"></jsp:useBean>
	<%
		HttpSession sess = request.getSession(false);
		System.out.println(sess);
		System.out.println(request.getSession(false).getAttribute("sid"));

		if (sess != null) {
			String sid = (String) request.getSession(false).getAttribute("sid");
			if (sid != null) {
				System.out.println(lao.getUsernameBySessionID(sid));
				response.sendRedirect(Redirect.redirect(lao.getRoleBySessionID(sid), true));
				return;
			}

		}
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
  				<h1 class="text-center">Forgot Password</h1>
  				<form action="../ForgotPassword" method="POST">
                        
  					<div class="form-group login">
  						<br>
  						<label class="col-sm-2">Username:</label>
  						<input type="text" required placeholder="Enter your username" name="username" class="form-control">
  						<br>
  						<br>
  						<div class="text-center">
  						<button type="submit" class=" btn btn-info">Submit</button>	</div>
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