package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogesh.dao.StudentDao;
import com.yogesh.database.DBconnection;

/**
 * Servlet implementation class FeePay
 */
public class FeePay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		if (session != null) {

			int rollNo = Integer.parseInt(request.getParameter("rollNo"));

			int amountPaid = Integer.parseInt(request.getParameter("amountpaid"));
			Connection connection = DBconnection.getCon();
			try {
				PreparedStatement statement = connection.prepareStatement("select fee,paid,due from student_det where roll_no=?");
				statement.setInt(1, rollNo);
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next()) {
					int fee = resultSet.getInt(1);
					int paid = resultSet.getInt(2);
					int due = resultSet.getInt(3);
					if(new StudentDao().feePay(rollNo,amountPaid,fee,paid,due)) {
						response.sendRedirect("feePay.html");
					}
					else
						out.print("Enter valid input");
						request.getRequestDispatcher("feePay.html").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	


		} else {
			out.print("<html><body>");
			out.print("<h3>Unauthorized User<h3>");
			out.print("</html></body>");
			response.sendRedirect("login.html");
		}
	}

}
