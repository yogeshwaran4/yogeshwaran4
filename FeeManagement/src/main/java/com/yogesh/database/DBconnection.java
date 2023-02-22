package com.yogesh.database;

import java.sql.Connection;
import java.sql.DriverManager;

//Add database in mysql before get the connection

public class DBconnection {
	static String url = "jdbc:mysql://localhost:3306/feemanagement";
	static String usname = "root";
	static String password = "";
	
	public static Connection getCon() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, usname, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
