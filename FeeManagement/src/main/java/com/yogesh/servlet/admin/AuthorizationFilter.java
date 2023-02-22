package com.yogesh.servlet.admin;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
public class AuthorizationFilter extends HttpFilter implements Filter {


	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		
		if(session == null) {
			out.print("Login First");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
		else {
			if(session.getAttribute("role").equals("admin")) {
				chain.doFilter(request, response);

			}
			else {
				out.print("Unauthorized access");
				 request.getRequestDispatcher("andminIndex.html").include(request, response);

			}
		}
		
		
	}



}
