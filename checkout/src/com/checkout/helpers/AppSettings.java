package com.checkout.helpers;

public class AppSettings{
	private static double clientVersion=1.0;
	
	public static String baseApiUrl="https://preprod.checkout.com/api2/v2/%s";
	public static String secretKey;//"sk_CC937715-4F68-4306-BCBE-640B249A4D50"
	public static boolean debugMode=false;
	public static int connectTimeout=60;
	public static int readTimeout=60;
	public static String clientUserAgentName=String.format("Checkout-JavaLibraryClient/%s", clientVersion);

}
