package com.yogesh.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import com.mysql.cj.jdbc.Blob;
import com.yogesh.dao.AccountantDao;

@MultipartConfig(location = "/home/local/ZOHOCORP/yogesh-pt6003/Downloads/Mov")
public class AddAccoutant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		HttpSession session = request.getSession(false);

		Part img = null;
		String fileUrl = null;
		OutputStream op = null;
		int read = 0;

		if (session == null) {
			out.print("<html><body>");
			out.print("Sorry ! You are not logged in.");
			request.getRequestDispatcher("login.html").include(request, response);
			out.print("</html></body>");
		} else {

			img = request.getPart("file");
			fileUrl = request.getParameter("fileUrl");
			try {
				int accId = Integer.parseInt(request.getParameter("id"));
				String userName = request.getParameter("username");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				long contact = Long.parseLong(request.getParameter("contact"));
				String password = request.getParameter("password");
				String Reenter_pass = request.getParameter("re_enter_pass");
				String fileName = "";
				InputStream inputStream = null;


				if (img.getSubmittedFileName().isEmpty() && !fileUrl.isEmpty()) {
					URL url = new URL(fileUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());
					inputStream = connection.getInputStream();
				} else if (fileUrl.isEmpty() && img != null) {
					fileName = img.getSubmittedFileName();
					inputStream = img.getInputStream();
				}

				op = new FileOutputStream(new File("/home/local/ZOHOCORP/yogesh-pt6003/Downloads/Mov" + File.separator + fileName));

				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					op.write(bytes, 0, read);
				}

				if (password.equals(Reenter_pass)) {
					new AccountantDao().addAccountant(accId, userName, name, email, contact, password, inputStream,
							fileName);
					response.sendRedirect("adminIndex.html");
				} else {
					out.print("password does not match");
					request.getRequestDispatcher("addAcc.html").include(request, response);
				}
			} catch (Exception e) {
				response.sendError(500);
				e.printStackTrace();
			}
			out.print("</html></body>");
		}

	}

}
