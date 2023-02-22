package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yogesh.dao.StudentDao;

/**
 * Servlet implementation class DeleteStudent
 */
public class DeleteStudent extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rollNo = Integer.parseInt(request.getParameter("rollno"));
		StudentDao dao = new StudentDao();
		dao.delete(rollNo);
		response.sendRedirect("ViewStudents");  
	}

}
