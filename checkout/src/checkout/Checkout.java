package checkout;

import java.io.IOException;

import checkout.exception.CKOException;
import checkout.helpers.ApiHttpClient;
import checkout.helpers.AppSettings;
import apiServices.charges.ChargeService;
import apiServices.tokens.TokenService;

public class Checkout {
	
	public TokenService tokenService;
	public ChargeService chargeService;
	
	public Checkout(String secretKey, boolean debugMode,int connectTimeout,int readTimeout) throws IOException{			
		 this.tokenService=new TokenService();
		 this.chargeService=new ChargeService();
		AppSettings.secretKey=secretKey;
		AppSettings.debugMode=debugMode;
		AppSettings.connectTimeout=connectTimeout;
		AppSettings.readTimeout=readTimeout;
		ApiHttpClient.logFile();		
	}	
	
	public Checkout(String secretKey){			
		 this.tokenService=new TokenService();
		 this.chargeService=new ChargeService();
		 AppSettings.secretKey=secretKey;
	}
	
	public static void main(String[] args) throws CKOException
	{
		
	}
}
