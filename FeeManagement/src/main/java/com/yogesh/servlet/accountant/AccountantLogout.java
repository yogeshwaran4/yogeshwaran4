package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AccountantLogout
 */
public class AccountantLogout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
			response.sendRedirect("login.html");
		}
		else {
			out.print("Session Timeout. Please login");
			response.addHeader("X-Frame-Options", "DENY");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}

}
