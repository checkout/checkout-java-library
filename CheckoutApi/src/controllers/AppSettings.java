package controllers;

public class AppSettings {
	
	public static String baseApiUrl="http://dev.checkout.com/api.gw3/v1/%s";
	public static String secretKey="";
	public static String publicKey="";
	public static boolean debugMode=true;
	public static int connectTimeout=30;
	public static int readTimeout=30;
	public static double clientVersion=1.0;
	public static String clientUserAgentName=String.format("Checkout-JavaLibraryClient/%s", clientVersion);
		
	
}
