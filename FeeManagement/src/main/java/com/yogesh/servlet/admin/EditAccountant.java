package com.yogesh.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.yogesh.dao.AccountantDao;

public class EditAccountant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);

		if (session == null) {
			out.print("<html><body>");
			out.print("Sorry ! You are not logged in.");
			request.getRequestDispatcher("adminlogin.html").include(request, response);
			out.print("</html></body>");
		} else {
			int accId = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			long contact = Long.parseLong(request.getParameter("contact"));
			AccountantDao dao = new AccountantDao();
			dao.update(accId,  name, email, contact);
			out.print("<html><body>");
			out.print("Successfully updated");
			response.sendRedirect("adminIndex.html");
			out.print("</html></body>");
			
			
		}

	}
}
