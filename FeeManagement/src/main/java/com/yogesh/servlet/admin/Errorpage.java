 package com.yogesh.servlet.admin; 

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Errorpage
 */
public class Errorpage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int statusCode =(int)request.getAttribute("jakarta.servlet.error.status_code");
		String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");
		
		out.print("<html> <body>");
		if(statusCode !=500) {
			out.print("<h2>Error</h2>");
			out.print("status code"+statusCode+"<br>");
			out.print("Requested uri "+requestUri+" not found<br>");
			out.print("Please go to <a href=adminIndex.html> Home</a>");
		}
		

		
	}

}
