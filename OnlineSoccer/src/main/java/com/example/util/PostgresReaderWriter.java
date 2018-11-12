package com.example.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.db.PostgreSQL_util;

public interface PostgresReaderWriter {

	default String dropTable(String argTableName) {
		Connection con = PostgreSQL_util.getConnection();
		String message = "";
		try {
			String sql = " DROP TABLE " + argTableName;
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			message += argTableName + " table was dropped ";
			return message;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "table " + argTableName + " wasnt dropped, see stacktrace";
	};

	default void getDatabaseMetaData() {
		try {
			Connection con = PostgreSQL_util.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			String[] types = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			while (rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	default void createTableIfNotExist(String tableName) {
		Connection con = PostgreSQL_util.getConnection();
		String message = "";
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet tables = dbmd.getTables(null, null, tableName, null);

			if (tables.next()) {
				message += "Table [" + tableName + "] already exist.";
			} else {
				// -----------------------------------
				// input genericCreateTable
				// -----------------------------------
				createDaoTable();

				message = "Table [" + tableName + "] table created.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(message);
	}
	
	void writeObjectsFromSQLiteToPostgreSQL();

	String dropDaoTable();

	String createDaoTable();

	void writeSingleObjectToPostgres(Object argObject, String argTable) throws SQLException;
}
