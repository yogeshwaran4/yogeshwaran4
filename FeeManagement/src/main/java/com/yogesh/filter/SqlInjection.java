package com.yogesh.filter;

import jakarta.servlet.Filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet Filter implementation class SqlInjection
 */
public class SqlInjection extends HttpFilter implements Filter {
       
 

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
	 
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		Pattern userPattern = Pattern.compile("^[a-zA-z0-9]{1,10}$");
		Matcher userMatcher = userPattern.matcher(userName);
		Pattern passPattern = Pattern.compile("[\\s]");
		Matcher passMatcher = passPattern.matcher(password);
		
		
		if(userMatcher.find() && passMatcher.find()==false) {
			chain.doFilter(request, response);

		}
		
		else 
			response.sendRedirect("login.html");
		
		
	}



}
