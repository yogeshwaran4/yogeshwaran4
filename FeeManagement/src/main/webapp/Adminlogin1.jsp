<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.yogesh.dao.AccountantDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String userName = request.getParameter("username");
	String password = request.getParameter("password");

	if (userName.equals("admin") && password.equals("admin123")) {
		session = request.getSession();
		session.setAttribute("role", userName);
		Cookie cookie = new Cookie("SessionId", session.getId());
		response.addCookie(cookie);
		String csrfToken=new AccountantDao().csrfGenerator();
		Cookie csrfCookie = new Cookie("csrfCookie",csrfToken);
		response.addCookie(csrfCookie);
		session.setAttribute("csrfValue", csrfToken);
		request.getRequestDispatcher("adminIndex.html").include(request, response);
	}
	
	else
	{
		out.print("Sorry wrong password");
		request.getRequestDispatcher("login.html").include(request, response);
	}
	%>


</body>
</html>