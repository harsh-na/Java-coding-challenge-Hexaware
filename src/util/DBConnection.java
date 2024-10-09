package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection == null) {
			// Load the MySQL JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver class loaded");

			// Load properties file
			Properties props = PropertyUtil.getPropertyString(); // No argument needed now

			String url = props.getProperty("url");
			String user = props.getProperty("username");
			String password = props.getProperty("password");

			// Establish the connection
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}
}
