package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.yogesh.dao.AccountantDao;

public class AccountantLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		AccountantDao dao = new AccountantDao();

		if (dao.validate(userName, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("accountant", userName);
			
			
			
			
			out.print("<html><body>");
			request.getRequestDispatcher("accountantIndex.html").include(request, response);
			out.print("<h3>Welcome "+dao.getName(userName)+"!</h3>");
			out.print("</html></body>");
		} else {
			out.print("<html><body>");
			out.print("Sorry ! Wrong passsword/username");
			request.getRequestDispatcher("login.html").include(request, response);
			out.print("</html></body>");

		}

	}

}
