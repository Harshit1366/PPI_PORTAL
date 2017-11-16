<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">  
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
request.logout();
if (request.getSession(false).getAttribute("sid") == null) {
    response.sendRedirect("../");
    return;
} else {
    lao.deleteSessionID(request.getSession(false).getAttribute("sid").toString());
    request.getSession(false).removeAttribute("sid");
    request.getSession(false).invalidate();
    response.sendRedirect("../login.jsp");
}

%>

</body>
</html>