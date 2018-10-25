package com.example.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.db.SQLite_util;


public interface SqliteReader {

	ArrayList<?> getAll();

	void showAll();

	void closeConnection();

	public default void showColumnNames(String argTable) {
			Connection connection =  SQLite_util.getConnection();
			try {

				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM " + argTable);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				// The column count starts from 1
				for (int i = 1; i <= columnCount; i++) {
					String name = rsmd.getColumnName(i);
					System.out.println("ColumnNumber : " + i + ", name: " + name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
