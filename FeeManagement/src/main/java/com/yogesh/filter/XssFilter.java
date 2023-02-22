package com.yogesh.filter;

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
 * Servlet Filter implementation class XssFilter
 */
public class XssFilter extends HttpFilter implements Filter {

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("username");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		if (userName.contains("<script>") || name.contains("<script>") || email.contains("<script>")) {
			request.getRequestDispatcher("addAcc.html").include(request, response);
			out.print("Enter valid input");
		} else {
			chain.doFilter(request, response);
		}
	}

}
