package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.yogesh.database.DBconnection;

public class ViewStudents extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = DBconnection.getCon();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("accountantIndex.html").include(request, response);

		out.print("<html><body>");
		out.print("<table>");
		out.print("<tr><td>RollNo</td><td>Name</td><td>Email</td><td>Department</td><td>Contact</td><td>Fee</td><td>Paid</td><td>Due</td><</tr>");

		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from student_det");

			if (resultset != null) {
				while (resultset.next()) {
					out.print("<tr><td>" + resultset.getInt(1) + "</td><td>" + resultset.getString(2) + "</td><td>"
							+ resultset.getString(3) + "</td><td>" + resultset.getString(4) + "</td><td>"
							+ resultset.getLong(5) + "</td><td>" + resultset.getInt(6) + "</td><td>"
							+ resultset.getInt(7) + "</td><td>" + resultset.getInt(8) + "</td>");
					out.print("<td><a href = editStudent.html> Edit</a></td><td><a href='DeleteStudent?rollno="
							+ resultset.getInt(1) + "'>Delete</a></td><tr>");

				}
			} else {
				out.print(
						"<tr><td>Null</td><td>Null</td><td>Null</td><td>Null</td><td>Null</td><td>Null</td><td>Null<td><td>Null</td></tr>");

			}
			out.print("</table></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
