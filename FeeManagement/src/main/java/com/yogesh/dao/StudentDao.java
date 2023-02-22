package com.yogesh.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yogesh.database.DBconnection;

public class StudentDao {

	Connection connection = DBconnection.getCon();

	public void addStudent(int rollNo, String name, String email, String department, long contact, int fee, int paid,
			int due) {

		try {
			PreparedStatement statement = connection.prepareStatement("insert into student_det(roll_no,name,email,department,contact,fee,paid,due) values(?,?,?,?,?,?,?,?)");
			statement.setInt(1, rollNo);
			statement.setString(2, name);
			statement.setString(3, email);
			statement.setString(4, department);
			statement.setLong(5, contact);
			statement.setInt(6, fee);
			statement.setInt(7, paid);
			statement.setInt(8, due);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	public void update(int rollno, String name, String email, String department, long contact) {

		try {
			PreparedStatement statement = connection.prepareStatement("update student_det set name=?,email=?,department=?,contact=? where roll_no=?");
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, department);
			statement.setLong(4, contact);
			statement.setInt(5, rollno);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int rollNo) {

		try {
			PreparedStatement statement = connection.prepareStatement("delete from student_det where roll_no = ?");
			statement.setInt(1, rollNo);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean feePay(int rollNo, int amountPaid, int fee, int paid, int due) {

		if (fee >= paid && fee >= due && (due - amountPaid) >= 0) {
			paid += amountPaid;
			due -= amountPaid;

			try {
				PreparedStatement statement = connection.prepareStatement("update student_det set paid=?,due=? where roll_no=?");
				statement.setInt(1, paid);
				statement.setInt(2, due);
				statement.setInt(3, rollNo);
				statement.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;

	}

}
