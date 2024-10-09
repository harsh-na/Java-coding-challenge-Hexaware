package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	public static Properties getPropertyString() throws ClassNotFoundException {
		Properties props = new Properties();
		try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find db.properties");
				return null;
			}
			// Load the properties file from the classpath
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
