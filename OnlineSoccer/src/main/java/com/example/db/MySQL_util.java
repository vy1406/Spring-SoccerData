package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_util {


	static Connection con = null;
// worked in main. 
//	String jdbc = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
//	String user = "hbstudent";
//	String pass = "hbstudent";
//	
//	try {
//		System.out.println("connecting to db: " + jdbc);
//		Connection myConn = DriverManager.getConnection(jdbc, user, pass);
//		System.out.println("connection succesfull");
//	}
//	
//	catch ( Exception exc) {
//		exc.printStackTrace();
//	}
	public static Connection getConnection() {
		String path = "localhost:";
		String port = "3306/";
		String dbName = "hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String password = "hbstudent";
		if (con != null)
			return con;
		else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + path + port + dbName, user, password);
				System.out.println("MySQL db connection opened");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	}

	public static void closeConnection() throws SQLException {
		System.out.println("MySQL database connection closed");
		con.close();

	}

}
