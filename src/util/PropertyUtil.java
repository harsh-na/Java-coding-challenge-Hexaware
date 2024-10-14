package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	public static String getPropertyString() throws IOException {
		Properties properties = new Properties();

		// Use the class loader to access the db.properties file from the classpath
		try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find db.properties");
				return null;
			}

			// Load properties from the input stream
			properties.load(input);
		}

		// Retrieve properties
		String host = properties.getProperty("hostname");
		String dbName = properties.getProperty("dbname");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		String port = properties.getProperty("port");

		// Return the formatted database URL
		return "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username + "&password=" + password;
	}
}
