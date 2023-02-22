package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yogesh.database.DBconnection;

public class FeeDefaulters extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = DBconnection.getCon();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("accountantIndex.html").include(request, response);

		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from student_det where due > 0");

			if (resultset.next()) {
				out.print("<html><body>");
				out.print("<table>");
				out.print("<tr><td>RollNo</td><td>Name</td><td>Email</td><td>Department</td><td>Contact</td><td>Fee</td><td>Paid</td><td>Due</td><</tr>");
				out.print("<h3>Fee Defaulters</h3>");
				out.print("<tr><td>" + resultset.getInt(1) + "</td><td>" + resultset.getString(2) + "</td><td>"
						+ resultset.getString(3) + "</td><td>" + resultset.getString(4) + "</td><td>"
						+ resultset.getLong(5) + "</td><td>" + resultset.getInt(6) + "</td><td>" + resultset.getInt(7)
						+ "</td><td>" + resultset.getInt(8) + "</td></tr>");
				out.print("</table></body></html>");
			} else {
				out.print("<html><body>");
				out.print("<h3>No Fee Defaulters</h3>");
				out.print("</body></html>");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
