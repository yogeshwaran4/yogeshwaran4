package com.yogesh.filter;

import jakarta.servlet.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.*;

/**
 * Servlet Filter implementation class FileUploadFilter
 */
public class FileUploadFilter extends HttpFilter implements Filter {
       

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");;
		
		Part profile = request.getPart("file");
		String pName = profile.getSubmittedFileName();
		

		
		String extension = "";
		int index = pName.lastIndexOf(".");
		if(index>0) {
			extension = pName.substring(index+1);
		}
		
		if(extension.equals("jpg") || extension.equals("jpeg")|| pName.isEmpty()) {
			chain.doFilter(request, response);
		}
		else {
			
			request.getRequestDispatcher("addAcc.html").include(request, response);
			out.print("Incompatible file. Retry");
		}
			
	}


}
