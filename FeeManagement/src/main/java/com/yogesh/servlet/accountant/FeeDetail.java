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
import java.sql.Statement;

import com.yogesh.database.DBconnection;

/**
 * Servlet implementation class FeeDetail
 */
public class FeeDetail extends HttpServlet { //try to make type roll number onetime

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = DBconnection.getCon();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("accountantIndex.html");

		int id = Integer.parseInt(request.getParameter("rollNo"));
		try {
			PreparedStatement statement = connection.prepareStatement("select * from student_det where roll_no=?");
			statement.setInt(1, id);
			ResultSet resultset = statement.executeQuery();

			out.print("<html><body>");
			if (resultset.next()) {
				out.print("RollNo:" + resultset.getInt(1) + "<br>Name:" + resultset.getString(2) + "<br>Fee"
						+ resultset.getInt(6) + "<br>Paid:" + resultset.getInt(7) + "<br>Due:" + resultset.getInt(8));
				
				//request.getRequestDispatcher("DuePay.html").include(request, response);
			} else
				out.print("<h3>No Record found<br>");
			out.print("	<form action='FeePay' method='post'>"
					+ "		Paid :<input type='text' name='amountpaid' />"
					+ "<input='hidden' name='rollNo' value ='"+id +"' />"
					+ "		<input type='submit' value='Pay'/>"
					+ "	</form>");
			out.print("</body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
