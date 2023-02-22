package com.yogesh.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;

/**
 * Servlet Filter implementation class SsrfChecker
 */
public class SsrfChecker extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		String fileUrl = request.getParameter("fileUrl");
		if (fileUrl.isEmpty()) {
			chain.doFilter(request, response);

		} else {
			URL url = new URL(fileUrl);
			String hostName = url.getHost();
			InetAddress ipAddress = InetAddress.getByName(hostName);

			if (fileUrl.contains("http") && !ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress()
					&& !ipAddress.isLinkLocalAddress()) {
				chain.doFilter(request, response);
			} else {

				request.getRequestDispatcher("addAcc.html").include(request, response);
				out.print("Enter valid Link");

			}

		}

	}

}
