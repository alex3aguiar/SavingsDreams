package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyBDUtil {
	private Properties prop = new Properties();
	private InputStream input = null;
	private static PropertyBDUtil propertyBDUtil = new PropertyBDUtil();
	
	private PropertyBDUtil() {
		
	}
	
	public static PropertyBDUtil getInstance() {
		return propertyBDUtil;
	}
	
	public Map<String, String> getBDProperty() {
		Map<String, String> properties = new HashMap<>();
		try {
			
			input = new FileInputStream("bdConfig.properties");
			
			prop.load(input);
			
			properties.put("database", prop.getProperty("database"));
			properties.put("dbuser", prop.getProperty("dbuser"));
			properties.put("dbpassword", prop.getProperty("dbpassword"));
			properties.put("dbport", prop.getProperty("dbport"));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return properties;
		}
		
	}

}
