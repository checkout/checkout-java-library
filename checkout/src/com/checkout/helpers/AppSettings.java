package com.checkout.helpers;

public class AppSettings{
	private static double clientVersion=1.0;
	private static String  liveUrl = "https://api2.checkout.com/v2/%s";
	private static String sandboxUrl = "https://sandbox.checkout.com/api2/v2/%s";
	
	public static String baseApiUrl="";
	public static String secretKey;//"sk_test_32b9cb39-1cd6-4f86-b750-7069a133667d"
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
