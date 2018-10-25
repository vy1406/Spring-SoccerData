package com.example.db;

import java.sql.*;

// --------------------------------------
// SQLITE connection
//--------------------------------------
public class SQLite_util {
	static Connection con = null;

	public static Connection getConnection() {
		String path = "C:\\Users\\USER\\MyDocs\\AfterStudy\\Java\\uDemyProjects\\FILES\\SoccerData";
		String dbName = "soccer.db";
		if (con != null)
			return con;
		else {
			try {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:" + path + "\\" + dbName);
				System.out.println("SQLite db connection opened");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	}

	public static void closeConnection() throws SQLException {
		System.out.println("SQLite tabase connection closed");
		con.close();
	}
}
