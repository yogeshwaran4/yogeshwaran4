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

/**
 * Servlet Filter implementation class CsrfTokenChecker
 */
public class CsrfTokenChecker extends HttpFilter implements Filter {

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String receivedToken = request.getParameter("csrf");
		HttpSession session = request.getSession(false);
		String getToken = (String) session.getAttribute("csrfValue");

		if (receivedToken == null || getToken == null) {
			session.invalidate();
			response.sendRedirect("login.html");

		} else if (receivedToken.equals(getToken)) {

			chain.doFilter(request, response);
		}
	}

}
