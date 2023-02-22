package com.yogesh.servlet.admin; 	

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yogesh.dao.AccountantDao;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accId = Integer.parseInt(request.getParameter("id"));
		AccountantDao dao = new AccountantDao();
		dao.delete(accId);
		response.sendRedirect("ViewAccountant");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accId = Integer.parseInt(request.getParameter("id"));
		AccountantDao dao = new AccountantDao();
		dao.delete(accId);
		response.sendRedirect("ViewAccountant");
	}

}
