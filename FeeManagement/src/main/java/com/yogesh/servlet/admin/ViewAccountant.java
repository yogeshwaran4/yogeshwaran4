package com.yogesh.servlet.admin;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import com.yogesh.database.DBconnection;

/**
 * Servlet implementation class ViewAccountant
 */
public class ViewAccountant extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection = DBconnection.getCon();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		request.getRequestDispatcher("adminIndex.html").include(request, response);
		out.print("<html><body>");
		out.print("<table>");
		out.print("<tr><th>Accountant Id</th><th>Username</th><th>Name</th><th>Contact</th><th>Email</th></tr>");
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("select * from accountant");

			if (resultset == null) {
				out.print("<tr><td>Null</td><td>Null</td><td>Null</td><td>Null</td><td>Null</td></tr>");
			} else {
				while (resultset.next()) {
					out.print("<tr><td>" + resultset.getInt(1) + "</td><td>" + resultset.getString(2) + "</td><td>"
							+ resultset.getString(3) + "</td><td>" + resultset.getLong(4) + "</td><td>"
							+ resultset.getString(5)
							+ "</td><td><a href = editAccountant.html>Edit</a></td><td><a href = deleteAccoutant.html> Delete</a></td></tr>");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
