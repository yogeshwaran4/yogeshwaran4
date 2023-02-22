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


public class AddStudent extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		
		StudentDao dao = new StudentDao();

		if (session != null ) {
			int rollNo = Integer.parseInt(request.getParameter("rollno"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String department = request.getParameter("department");
			long contact = Long.parseLong(request.getParameter("contact"));
			int fee = Integer.parseInt(request.getParameter("fee"));
			int paid = Integer.parseInt(request.getParameter("paid"));         // change paid and due fee  
			int dueFee = Integer.parseInt(request.getParameter("duefee"));  
			

			dao.addStudent(rollNo, name, email, department, contact, fee, paid, dueFee);
			out.print("<html><body>");
			out.print("Successfully student added.");
			request.getRequestDispatcher("accountantIndex.html").forward(request, response);
			out.print("</html></body>");
		} else {
			out.print("<html><body>");
			out.print("Sorry ! You are not logged in.");
			request.getRequestDispatcher("login.html").include(request, response);
			out.print("</html></body>");
		}
	}

}
