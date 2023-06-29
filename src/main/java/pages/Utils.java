package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	
	public static Properties getPropertyObj() throws IOException {
		
		FileInputStream FIS = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/data.properties");
		Properties prop = new Properties();
		prop.load(FIS);
		
		return prop;
	}
	
	public static String getURL() throws IOException {
		return getPropertyObj().getProperty("url");
	}
	
	public static String getUserName() throws IOException {
		return getPropertyObj().getProperty("username");
	}
	
	public static String getPassword() throws IOException {
		return getPropertyObj().getProperty("password");
	}

}
