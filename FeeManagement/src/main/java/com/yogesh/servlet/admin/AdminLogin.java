package com.yogesh.servlet.admin;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import com.yogesh.dao.AccountantDao;

public class AdminLogin extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
	
		
		if(userName.equals("admin")&& password.equals("admin123")) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", userName);
			String csrfToken=new AccountantDao().csrfGenerator();
			Cookie cookie  = new Cookie("SessionId",session.getId());
			response.addCookie(cookie);

			
			request.getRequestDispatcher("adminIndex.html").include(request, response);
		}
		
		else {
				out.print("<html><body>");
				out.print("Sorry ! Wrong passsword/username");
				request.getRequestDispatcher("login.html").include(request, response);
				out.print("</html></body>");
		}
		
	
	}


}
