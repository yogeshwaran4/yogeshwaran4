package com.yogesh.dao;

import java.io.InputStream;
import java.sql.*;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.yogesh.database.DBconnection;

public class AccountantDao {

	Connection connection = DBconnection.getCon();

	public void addAccountant(int accId, String userName, String name, String email, long contact, String password,InputStream profile, String fileName) {

		try {
			PreparedStatement statement = connection.prepareStatement("insert into accountant(acc_id,username,name,contact,email,password,profile,file) values(?,?,?,?,?,?,?,?)");
			statement.setInt(1, accId);
			statement.setString(2, userName);
			statement.setString(3, name);
			statement.setLong(4,contact);
			statement.setString(5, email);
			statement.setString(6, password);
			statement.setBlob(7, profile);
			statement.setString(8, fileName);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(int accId, String name, String email, long contact) {

		try {
			PreparedStatement statement = connection.prepareStatement("update accountant set name=?,email=?,contact=? where acc_id = ?");
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setLong(3, contact);
			statement.setInt(4, accId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int accId) {
		try {
			PreparedStatement statement = connection.prepareStatement("delete from accountant where acc_id = ?");
			statement.setInt(1, accId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public boolean validate(String userName,String password) {
		try {
//			PreparedStatement statement = connection.prepareStatement("select * from accountant where username=? and password = ?");
//			statement.setString(1, userName);
//			statement.setString(2, password);
			Statement statement = connection.createStatement();
		
			ResultSet rs = statement.executeQuery("select * from accountant where username = '" + userName + "'"+" and password='"+password+"'");
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	public String getName(String userName) {
		PreparedStatement statement;
		String name = null;
		try {
			statement = connection.prepareStatement("select name from accountant where username=?");
			statement.setString(1, userName);
			ResultSet resultset = statement.executeQuery();
			resultset.next();
			
			name = resultset.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return name;
	}
	public String csrfGenerator() {
		
		
		String token = UUID.randomUUID().toString();
		token = token.replace("-", "");
		
		return token;
	}
	


}