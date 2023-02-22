package com.yogesh.servlet.accountant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogesh.database.DBconnection;

/**
 * Servlet implementation class ViewProfile
 */
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpg");
		Connection connection = DBconnection.getCon();
		HttpSession session = request.getSession(false);

		try {
			PreparedStatement statement = connection.prepareStatement("select file from accountant where username=?");
			statement.setString(1, (String) session.getAttribute("accountant"));
			ResultSet set = statement.executeQuery();
			set.next();

			String fileName = set.getString(1);

			FileInputStream fis = new FileInputStream(
					"/home/local/ZOHOCORP/yogesh-pt6003/Downloads/Mov" + File.separator + fileName);
			byte[] bytes = new byte[1024];
			OutputStream os = response.getOutputStream();
			int read = 0;
			while ((read = fis.read(bytes)) != -1) {
				os.write(bytes, 0, read);

			}

			os.flush();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
