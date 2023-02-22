package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogesh.database.DBconnection;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		Connection connection = DBconnection.getCon();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		int roll_No = Integer.parseInt(request.getParameter("rollNo"));
		
		request.getRequestDispatcher("accountantIndex.html").include(request, response);
		out.print("<html><body>");
		
		try {
			PreparedStatement statement = connection.prepareStatement("select * from student_det where roll_no = ?");
			statement.setInt(1, roll_No);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {
					
					out.print("<br>RollNo	:" + resultset.getInt(1) + "<br>Name	:" + resultset.getString(2) + "<br>Email	:"
							+ resultset.getString(3) + "<br>department	:" + resultset.getString(4) + "<br>contact	:"
							+ resultset.getLong(5) + "<br>Fee	:" + resultset.getInt(6) + "<br>Paid	:"
							+ resultset.getInt(7) + "<br>Due	:" + resultset.getInt(8));
					
			} else {
				out.print("<h3>Not Found</h3>");
				request.getRequestDispatcher("searchStudent.html").include(request, response);

			}
			out.print("</body></html>");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
