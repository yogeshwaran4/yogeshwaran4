package com.yogesh.filter;

import jakarta.servlet.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet Filter implementation class Broken_Access_Control
 */
public class Broken_Access_Control extends HttpFilter implements Filter {
       
 
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		if(session == null  || !session.getAttribute("role").equals("admin")) {
			out.print("Unauthorize access");
			session.invalidate();
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		else {
			chain.doFilter(request, response);
		}
	}

	

}
