package com.mk.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleJDBCExample
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:username/password@//host:port/serviceid";

	// Database credentials
	static final String USER = "username";
	static final String PASS = "password";

	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
		try
		{
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT user_id, row_id, first_name, last_name FROM wc_data where rownum < 3";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next())
			{
				// Retrieve by column name
				long id = rs.getLong("user_id");
				long age = rs.getLong("row_id");
				String first = rs.getString("first_name");
				String last = rs.getString("last_name");

				// Display values
				System.out.print("UID: " + id);
				System.out.print(", RowId: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se)
		{
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e)
		{
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally
		{
			// finally block used to close resources
			try
			{
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2)
			{
			}// nothing we can do
			try
			{
				if (conn != null)
					conn.close();
			} catch (SQLException se)
			{
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main
}// end FirstExample