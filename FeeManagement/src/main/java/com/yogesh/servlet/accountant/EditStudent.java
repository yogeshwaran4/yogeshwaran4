package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.yogesh.dao.AccountantDao;
import com.yogesh.dao.StudentDao;


public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);

		if (session !=null) {
			int rollno = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String department = request.getParameter("department");
			long contact = Long.parseLong(request.getParameter("contact"));
			StudentDao dao = new StudentDao();
			dao.update(rollno,name,email,department,contact);
			out.print("<html><body>");
			out.print("Successfully updated");
			response.sendRedirect("ViewStudents"); 
			out.print("</html></body>");
			
		} else {
		
			out.print("<html><body>");
			out.print("Sorry ! You are not logged in.");
			request.getRequestDispatcher("login.html").include(request, response);
			out.print("</html></body>");
			
		}
	}

}
