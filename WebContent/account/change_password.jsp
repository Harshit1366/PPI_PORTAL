<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="com.ppi.constants.Redirect"%>
<html>
<head>
<title>Home Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.container {
	max-width: 700px;
	position: relative;
	top: 50px;
}

td {
	text-align: center;
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 15px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

h3 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 25px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

h4 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 25px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

p {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 18px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

table {
	overflow: hidden;
	text-overflow: ellipsis;
	word-wrap: break-word;
}

a {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 17px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

.container {
	width: 100%;
}

li.borderless {
	border-bottom: 0 none;
	border-top: none;
}

ul {
	list-style: none;
}

.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none;
}

.content:before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: -1;
	display: block;
	background-image: url('../resources/images/DSCN7348.jpg');
	-webkit-filter: brightness(0.8);
	filter: brightness(0.8);
	background-size: cover;
	width: 100%;
	height: 100%;
	-webkit-filter: blur(5px);
	-moz-filter: blur(5px);
	-o-filter: blur(5px);
	-ms-filter: blur(5px);
	filter: blur(5px);
	filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius='3');
}

.content {
	overflow: visible;
	position: relative;
}

div.transbox {
	margin: 30px;
	background-color: rgba(255, 255, 255, 0.6);
	border: 0px solid;
	width: auto;
	border-radius: 5px;
	/* For IE8 and earlier */
}

a {
	color: #000000;
}

body {
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}
</style>

</head>

<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
	onload = function() {
		var e = document.getElementById("refreshed");
		if (e.value == "no")
			e.value = "yes";
		else {
			e.value = "no";
			location.reload();
		}
	}
</script>
<body class="content">
	<jsp:useBean id="lao" class="com.ppi.impl.LoginIMPL"></jsp:useBean>
	<%
		HttpSession sess = request.getSession(false);
		System.out.println(sess);
		System.out.println(request.getSession(false).getAttribute("sid"));

		if (sess != null) {
			String sid = (String) request.getSession(false).getAttribute("sid");
			if (sid == null) {
				response.sendRedirect("../");
				return;
			}

		}
	%>

	<svg version="1.1" xmlns="http://www.w3.org/2000/svg">
   <filter id="blur">
       <feGaussianBlur stdDeviation="3" />
   </filter>
</svg>
	<div class="container">
		<div class="row transbox">
			<div class="col-md-6" align="center">
				<br>
				<div>
					<c:if test="${not empty param.change}">

						<c:if test="${param.change == 'success'}">
							<p style="color: green;">
								<c:out value="Change Successfull."></c:out>
							</p>
						</c:if>
						<c:if test="${param.change == 'illegal'}">
							<p style="color: red;">
								<c:out
									value="Password must be alphanumeric with symbols and  7 to 15 chars long. New Passwords must match."></c:out>
							</p>
						</c:if>
						<c:if test="${param.change == 'dataloss'}">
							<p style="color: red;">
								<c:out value="Cannot Update Password"></c:out>
							</p>
						</c:if>
						<c:if test="${param.change == 'nosuchlogin'}">
							<p style="color: red;">
								<c:out value="No Such Account Found"></c:out>
							</p>
						</c:if>
						<c:if test="${param.change == 'error'}">
							<p style="color: red;">
								<c:out value="Error Occured"></c:out>
							</p>
						</c:if>


					</c:if>
				</div>
				<img src="../resources/images/ncu logo.png" class="img-responsive"
					width="300" height="200"> <br>
				<h4>
					<strong>Publication Module</strong>
				</h4>
			</div>
			<div class="col-md-6 form-group" align="center">

				<form method="POST" name="form1" action="../ChangePassword">
					<table class="table table-borderless">
						<tr>
							<td colspan="3">Logged in as:<c:out
									value="<%=lao.getUsernameBySessionID(request.getSession(false).getAttribute(\"sid\").toString())%>"></c:out>
							</td>
						</tr>

						<tr>
							<td><label for="password">Old Password</label></td>
							<td>&nbsp;</td>
							<td><input class="form-control" type="password" autocomplete="off" required="required"
								name="oldPassword" id="oldPassword"></td>
						</tr>
						<tr>
							<td><label for="password">New Password</label></td>
							<td>&nbsp;</td>
							<td><input class="form-control" type="password"  autocomplete="off" required="required"
								name="newPassword1" id="newPassword1" onkeyup='check();'></td>
						</tr>
						<tr>
							<td><label for="password">Again New Password</label></td>
							<td>&nbsp;</td>
							<td><input class="form-control" type="password"  autocomplete="off" required="required"
								name="newPassword2" id="newPassword2" onkeyup='check();'>
								<span id='message'></span></td>

						</tr>
						<tr>
							<td>
								<button class="form-control" type="reset">Reset</button>
							</td>
							<td>&nbsp;</td>
							<td>
								<button class="form-control" type="submit" id="submit_button"
									onclick="CheckPassword(document.form1.newPassword1)">Submit</button>
							</td>
						</tr>
					</table>

				</form>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		function CheckPassword(inputtxt) {
			var paswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
			if (inputtxt.value.match(paswd)) {
				return true;
			} else {
				alert('Password must be alphanumeric with symbols and  7 to 15 chars long.')
				return false;
			}
		}
		var check = function() {
			
			if ( document.getElementById('newPassword1').value !='' &&document.getElementById('newPassword1').value == document
					.getElementById('newPassword2').value ) {
				document.getElementById('message').style.color = 'green';
				document.getElementById('message').innerHTML = 'matching';
				document.getElementById('submit_button').disabled=false;
				
			} else {
				document.getElementById('message').style.color = 'red';
				document.getElementById('message').innerHTML = 'not matching';
				document.getElementById('submit_button').disabled=true;
			}
		}
	</script>
</body>
</html>