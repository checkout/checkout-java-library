package com.checkout.helpers;

public class AppSettings{
	private static double clientVersion=1.0;
	private static String  liveUrl = "https://api2.checkout.com/v2/%s";
	private static String sandboxUrl = "http://sandbox.checkout.com/api2/v2/%s";
		
	public static String baseApiUrl="";
	public static String secretKey;//"sk_CC937715-4F68-4306-BCBE-640B249A4D50"
	public static boolean debugMode=false;
	public static int connectTimeout=60;
	public static int readTimeout=60;
	public static String clientUserAgentName=String.format("Checkout-JavaLibraryClient/%s", clientVersion);
	
	public static void SetEnvironment(Environment env) {
	
		switch(env){
		case LIVE:	baseApiUrl = liveUrl;
			break;
		case SANDBOX:	baseApiUrl = sandboxUrl;
			break;
		}
	}

}
