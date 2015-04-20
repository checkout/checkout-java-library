package com.checkout.helpers;

public class AppSettings{
	private static double clientVersion=1.0;
	
	public static String baseApiUrl="https://api2.checkout.com/v2/%s";
	public static String secretKey="";
	public static boolean debugMode=false;
	public static int connectTimeout=60;
	public static int readTimeout=60;
	public static String clientUserAgentName=String.format("Checkout-JavaLibraryClient/%s", clientVersion);

}
