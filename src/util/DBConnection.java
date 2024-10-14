package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection;

	// Static block to load the driver class and establish the connection
	static {
		try {
			// Load the database driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Initialize connection using properties
			connection = getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		}

		try {
			// Retrieve connection properties using PropertyUtil
			String dbUrl = PropertyUtil.getPropertyString();

			if (dbUrl != null) {
				// Create connection using the URL from properties
				connection = DriverManager.getConnection(dbUrl);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
