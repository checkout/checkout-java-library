package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import exception.CKOException;

public class AppSettings{
	
	public static Map<String,String> propertyMap=readProperty();
	
	public static String baseApiUrl = propertyMap.get("baseApi");
	public static String secretKey=propertyMap.get("secretKey");
	public static String debugMode=propertyMap.get("debugMode");
	public static String connectTimeout=propertyMap.get("connectTimeout");
	public static String readTimeout=propertyMap.get("readTimeout");
	public static double clientVersion=1.0;
	public static String clientUserAgentName=String.format("Checkout-JavaLibraryClient/%s", clientVersion);
		
	
	public static Map<String,String> readProperty(){
		Properties prop = new Properties();
		InputStream input = null;
		Map<String,String> property=new HashMap<String,String>();
		try {

			input = new FileInputStream("./config.properties");
			// load a properties file
			prop.load(input);
			
			property.put("baseApi",prop.getProperty("baseApi"));
			property.put("secretKey",prop.getProperty("secretKey"));
			property.put("debugMode",prop.getProperty("debugMode"));
			property.put("connectTimeout",prop.getProperty("connectTimeout"));
			property.put("readTimeout",prop.getProperty("readTimeout"));

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
		}
		return property;
		
	} 
	
	public String buildApiUrl(String apiPath) throws CKOException{
		
		if(apiPath==null){
			throw new CKOException("Api Path is invalid");
		}
		
		return String.format(baseApiUrl, apiPath);
	}

}
