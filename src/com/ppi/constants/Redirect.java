package com.ppi.constants;

public class Redirect {
	
	public static String redirect(String role, boolean success){
		String url = "login.jsp";
		if(!success){
			return url;
		}
		
		switch(role){
		case "ROLE_STUDENT": url =  "student/student.jsp";
		break;
		case "ROLE_ADMIN": url =  "admin/admin_home.jsp";
		break;
		case "ROLE_EXPERT": url =  "expert/expert.jsp";
		break;
		default:
		}
		return url;
	}

}