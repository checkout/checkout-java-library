package com.checkout;

import java.io.IOException;

import com.checkout.apiServices.charges.ChargeService;
import com.checkout.apiServices.tokens.TokenService;
import com.checkout.exception.CKOException;
import com.checkout.helpers.ApiHttpClient;
import com.checkout.helpers.AppSettings;

public class APIClient {
	
	public TokenService tokenService;
	public ChargeService chargeService;
	
	public APIClient(String secretKey, boolean debugMode,int connectTimeout,int readTimeout) throws IOException{			
		 this.tokenService=new TokenService();
		 this.chargeService=new ChargeService();
		AppSettings.secretKey=secretKey;
		AppSettings.debugMode=debugMode;
		AppSettings.connectTimeout=connectTimeout;
		AppSettings.readTimeout=readTimeout;
		ApiHttpClient.logFile();		
	}	
	
	public APIClient(String secretKey){			
		 this.tokenService=new TokenService();
		 this.chargeService=new ChargeService();
		 AppSettings.secretKey=secretKey;
	}
	
	public static void main(String[] args) throws CKOException
	{
		
	}
}
